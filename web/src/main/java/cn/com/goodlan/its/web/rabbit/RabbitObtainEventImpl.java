package cn.com.goodlan.its.web.rabbit;

import cn.com.goodlan.its.core.dao.primary.event.EventHistoryRepository;
import cn.com.goodlan.its.core.dao.primary.system.camera.CameraRepository;
import cn.com.goodlan.its.core.dao.primary.system.vehicle.VehicleRepository;
import cn.com.goodlan.its.core.dao.primary.whitelist.WhitelistRepository;
import cn.com.goodlan.its.core.file.FileUpload;
import cn.com.goodlan.its.core.pojo.TrafficEvent;
import cn.com.goodlan.its.core.pojo.entity.primary.Camera;
import cn.com.goodlan.its.core.pojo.entity.primary.Region;
import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Driver;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.pojo.entity.primary.eventhistory.EventHistory;
import cn.com.goodlan.its.core.pojo.entity.primary.whitelist.Whitelist;
import cn.com.goodlan.its.event.HandlerManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.ZoneId;
import java.util.Optional;

@Component
public class RabbitObtainEventImpl {

    private static final Logger log = LoggerFactory.getLogger(RabbitObtainEventImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WhitelistRepository whitelistRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EventHistoryRepository eventHistoryRepository;

    @Autowired
    private FileUpload fileUpload;

    @Autowired
    private HandlerManager handlerManager;

    @Autowired
    private VehicleCache vehicleCache;

    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    @RabbitListener(queuesToDeclare = @Queue(name = "its.traffic.event", durable = "true"))
    public synchronized void obtainEvent(String message) {

        TrafficEvent trafficEvent = convert(message);

        String imageUrl = fileUpload.uploadImage(trafficEvent.getBigImage());

        EventHistory eventHistory = new EventHistory();
        eventHistory.setEventName(trafficEvent.getM_EventName());
        eventHistory.setIp(trafficEvent.getIp());
        eventHistory.setImageUrl(imageUrl);
        eventHistory.setLicensePlateNumber(trafficEvent.getM_PlateNumber());
        eventHistory.setHappenTime(trafficEvent.getM_Utc().toInstant().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime());
        eventHistory.setSpeed(trafficEvent.getNSpeed());
        eventHistory.setPlace(trafficEvent.getM_IllegalPlace());
        eventHistory.setSize(trafficEvent.getM_VehicleSize());


        Camera camera = cameraRepository.getByIp(trafficEvent.getIp());
        if (camera == null) {
            log.info("??????ip???{}????????????", trafficEvent.getIp());
            eventHistory.setResult("??????????????????????????????");
            eventHistoryRepository.save(eventHistory);
            return;
        }


        eventHistory.setPlace(camera.getPosition());
        if (StringUtils.isEmpty(trafficEvent.getM_PlateNumber())) {
            eventHistory.setResult("??????????????????????????????");
            eventHistoryRepository.save(eventHistory);
            return;
        }
        Driver driver;
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(trafficEvent.getM_PlateNumber());
        if (optionalVehicle.isPresent()) {
            eventHistory.setDriverName(optionalVehicle.get().getDriverName());
            eventHistory.setDriverPhone(optionalVehicle.get().getDriverPhone());
            eventHistory.setCollegeName(optionalVehicle.get().getCollegeName());
            driver = new Driver(optionalVehicle.get().getDriverName(), optionalVehicle.get().getDriverPhone(), optionalVehicle.get().getCollegeName(), optionalVehicle.get().getStudstaffno());
        } else {
            log.info("??????????????????????????????:{}", trafficEvent.getM_PlateNumber());
            eventHistory.setResult("??????????????????????????????");
            eventHistoryRepository.save(eventHistory);
            return;
        }


        // ???????????????????????????????????? ?????????????????????????????????
        if (existsCache(trafficEvent)) {
            eventHistory.setResult("????????????,?????????");
            eventHistoryRepository.save(eventHistory);
            return;
        } else {
            vehicleCache.put(trafficEvent.getM_PlateNumber());
        }


        if (inWhiteList(trafficEvent)) {
            eventHistory.setResult("??????????????????????????????,?????????");
            eventHistoryRepository.save(eventHistory);
            log.info("??????????????????????????? ?????????:{}", trafficEvent.getM_PlateNumber());
            return;
        }


        Region region = camera.getRegion();

        if (region == null) {
            log.info("???{}????????????????????????", camera.getIp());
            eventHistory.setResult("???????????????????????????");
            eventHistoryRepository.save(eventHistory);
            return;
        }


        Event event = new Event(Event.Source.AUTO);

        event.updateRegion(region);
        event.updatePlace(camera.getPosition());
        event.updateDriver(driver);
        event.updateLicensePlateNumber(trafficEvent.getM_PlateNumber());
        event.updateSpeed(trafficEvent.getNSpeed());
        event.updateHappenTime(trafficEvent.getM_Utc().toInstant().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime());
        event.updateVehicleSize(trafficEvent.getM_VehicleSize());
        event.updateImageUrl(imageUrl);


        String result = handlerManager.handler(event, trafficEvent.getM_EventName());
        eventHistory.setResult(result);

        eventHistoryRepository.save(eventHistory);

    }

    private TrafficEvent convert(String message) {
        String content = StringEscapeUtils.unescapeJava(message);
        if (StringUtils.startsWithIgnoreCase(content, "\"")) {
            content = content.substring(1, content.length() - 1);
        }
        TrafficEvent trafficEvent;
        try {
            trafficEvent = objectMapper.readValue(content, TrafficEvent.class);
            log.info("trafficEvent: {}", trafficEvent.toString());
        } catch (JsonProcessingException e) {
            log.error("??????mq????????????:", e);
            return null;
        }
        return trafficEvent;
    }

    /**
     * ????????????????????????
     */
    private boolean inWhiteList(TrafficEvent trafficEvent) {
        Whitelist whitelist = whitelistRepository.findByLicensePlateNumber(trafficEvent.getM_PlateNumber().trim());
        return whitelist != null;
    }


    /**
     * ?????????????????????????????? ???????????????
     */
    private boolean existsCache(TrafficEvent trafficEvent) {
        return vehicleCache.exists(trafficEvent.getM_PlateNumber());
    }

}

