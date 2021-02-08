package cn.com.goodlan.its.service.event.approval;

import cn.com.goodlan.its.dao.event.EventRepository;
import cn.com.goodlan.its.dao.system.camera.CameraRepository;
import cn.com.goodlan.its.dao.system.vehicle.VehicleRepository;
import cn.com.goodlan.its.pojo.TrafficEvent;
import cn.com.goodlan.its.pojo.entity.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class RabbitObtainEventImpl {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(name = "its.traffic.event", durable = "true"))
    public void obtainEvent(String message) throws JsonProcessingException {
        String content = StringEscapeUtils.unescapeJava(message);
        System.out.println(content);
        if (StringUtils.startsWithIgnoreCase(content, "\"")) {
            content = content.substring(1, content.length() - 1);
        }
        TrafficEvent trafficEvent = objectMapper.readValue(content, TrafficEvent.class);
//        log.debug(trafficEvent.toString());
        Event event = new Event();
//        event.setCamera(cameraRepository.getByIp(trafficEvent.getIp()));
        event.setVehicle(vehicleRepository.getByLicensePlateNumber(trafficEvent.getM_PlateNumber()));
        event.setPlace(trafficEvent.getM_IllegalPlace());
        event.setLicensePlateNumber(trafficEvent.getM_PlateNumber());
//        event.setTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(trafficEvent.getM_Utc()), ZoneId.of("Asia/Shanghai")));
        event.setLaneNumber(trafficEvent.getM_LaneNumber());
        event.setVehicleColor(trafficEvent.getM_VehicleColor());
        event.setImage(trafficEvent.getBigImage());
        eventRepository.save(event);
    }

}
