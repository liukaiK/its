package cn.com.goodlan.its.event.handler;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.dao.primary.sms.SmsHistoryRepository;
import cn.com.goodlan.its.core.dao.primary.system.camera.CameraRepository;
import cn.com.goodlan.its.core.dao.primary.system.score.ScoreRepository;
import cn.com.goodlan.its.core.dao.primary.system.vehicle.VehicleRepository;
import cn.com.goodlan.its.core.file.FileUpload;
import cn.com.goodlan.its.core.pojo.MessageParam;
import cn.com.goodlan.its.core.pojo.TrafficEvent;
import cn.com.goodlan.its.core.pojo.entity.primary.Camera;
import cn.com.goodlan.its.core.pojo.entity.primary.Score;
import cn.com.goodlan.its.core.pojo.entity.primary.SmsHistory;
import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.service.event.CountService;
import cn.com.goodlan.its.core.util.DateUtils;
import cn.com.goodlan.its.web.properties.SmsProperties;
import cn.com.goodlan.its.web.sms.SmsService;
import cn.com.goodlan.its.web.sms.template.SmsMessageTemplate;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.ZoneId;
import java.util.Optional;

/**
 * 处理违章停车事件
 *
 * @author liukai
 */
@Slf4j
@Component
public class StopEventHandlerImpl implements EventHandler {

    private final static String STOP = "违章停车";

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private FileUpload fileUpload;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CountService countService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private SmsMessageTemplate smsMessageTemplate;

    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    @Autowired
    private SmsProperties smsProperties;

    @Override
    public void handler(TrafficEvent trafficEvent) {

        Camera camera = cameraRepository.getByIp(trafficEvent.getIp());
        if (camera == null) {
            log.warn("没有ip为{}的摄像头", trafficEvent.getIp());
            return;
        }

        String imageUrl = fileUpload.uploadImage(trafficEvent.getBigImage());

        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(trafficEvent.getM_PlateNumber());
        Event event;
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            Long count = countService.queryCountThisYear(vehicle.getLicensePlateNumber(), STOP);
            Score score = scoreRepository.getById("0f647018-2c28-4bfe-ae10-e9586cfb66b0");
            if (count == 1) {
                event = warn(camera, trafficEvent, imageUrl, vehicle, score);
                sendSmsAndWeLink(event);
                return;
            }
            if (count == 2) {
                event = calculateScore(trafficEvent, score, vehicle, camera, count, imageUrl);
                sendSmsAndWeLink(event);
                return;
            }
            if (count == 3) {
                event = calculateScore(trafficEvent, score, vehicle, camera, count, imageUrl);
                sendSmsAndWeLink(event);
                return;
            }
            if (count >= 4) {
                event = calculateScore(trafficEvent, score, vehicle, camera, count, imageUrl);
                sendSmsAndWeLink(event);
            }
        } else {
            log.warn("此车牌号系统中不存在:{}", trafficEvent.getM_PlateNumber());
        }


    }

    private void sendSmsAndWeLink(Event event) {
        if (smsProperties.isEnable()) {
            sendSmsMessage(event);
        }
        sendWeLink(event);
    }


    /**
     * 发送短信
     */
    private void sendSmsMessage(Event event) {
        String phone = event.getDriverPhone();
        String smsMessageContent = buildSmsMessageContent(event);
        String smsSuccessResult = smsService.sendSms(phone, smsMessageContent);
        saveSmsHistory(phone, smsMessageContent, smsSuccessResult, event.getId());

    }

    private void saveSmsHistory(String phone, String smsMessageContent, String smsSuccessResult, String eventId) {
        try {
            SmsHistory smsHistory = new SmsHistory(phone, smsMessageContent, smsSuccessResult, eventId);
            smsHistoryRepository.save(smsHistory);
        } catch (Exception e) {
            log.error("save sms history error ", e);
        }
    }


    /**
     * 构建短信内容
     */
    private String buildSmsMessageContent(Event event) {
        String punish;
        Long count = event.getNum();

        if (count == 1L) {
            punish = "警告";
            String smsTemplate = smsMessageTemplate.getSmsTemplate();
            return MessageFormat.format(smsTemplate, event.getLicensePlateNumber(), DateUtil.format(event.getTime(), DateUtils.YYYY_MM_DD_HH_MM_SS), event.getPlace(), STOP, punish);
        }

        if (count % 4 == 0L) {
            String backSmsTemplate = smsMessageTemplate.getBlackTemplate();
            return MessageFormat.format(backSmsTemplate, event.getLicensePlateNumber());
        }

        punish = "扣校内安全考核分";
        String smsTemplate = smsMessageTemplate.getSmsTemplate();
        return MessageFormat.format(smsTemplate, event.getLicensePlateNumber(), DateUtil.format(event.getTime(), DateUtils.YYYY_MM_DD_HH_MM_SS), event.getPlace(), STOP, punish);

    }

    private void sendWeLink(Event event) {
        String violationType = event.getViolationName();
        if ("违章停车".equals(violationType)) {
            violationType = "违章停车";
        } else {
            violationType = "超速";
        }
        String punish;
        if (event.getNum() > 1) {
            punish = "扣校内安全考核分";
        } else {
            punish = "警告";
        }

        MessageParam messageParam = new MessageParam(event.getStudstaffno(), event.getPlace(), DateUtil.format(event.getTime(), DateUtils.YYYY_MM_DD_HH_MM_SS), event.getLicensePlateNumber());
        messageParam.setContent(String.format("您的车辆%s于%s在%s，被交通技术监控设备记录了%s的违法行为。给予%s处罚，请知悉。点击查看详情。", event.getLicensePlateNumber(), DateUtil.format(event.getTime(), DateUtils.YYYY_MM_DD_HH_MM_SS), event.getPlace(), violationType, punish));
        log.info("WeLink:{}", messageParam.getContent());
        smsService.sendWelink(messageParam);
    }

    private Event warn(Camera camera, TrafficEvent trafficEvent, String imageUrl, Vehicle vehicle, Score score) {
        Event event = new Event();
        event.updateScore(score);
        event.setNum(1L);
        event.setCamera(camera);
        event.setPlace(camera.getPosition());
        event.setTime(trafficEvent.getM_Utc().toInstant().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime());
        event.setLaneNumber(trafficEvent.getM_LaneNumber());
        event.setVehicleColor(trafficEvent.getM_VehicleColor());
        event.setImageUrl(imageUrl);
        event.setVehicleSize(trafficEvent.getM_VehicleSize());
        event.setSpeed(trafficEvent.getNSpeed());
        event.updateVehicle(vehicle);
        event.updateScore(0);
        event.updateViolation(score.getViolation());
        return eventRepository.save(event);


    }

    protected Event calculateScore(TrafficEvent trafficEvent, Score score, Vehicle vehicle, Camera camera, Long count, String imageUrl) {
        Event event = new Event();
        event.setNum(count);
        event.setCamera(camera);
        event.setPlace(camera.getPosition());
        event.setTime(trafficEvent.getM_Utc().toInstant().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime());
        event.setLaneNumber(trafficEvent.getM_LaneNumber());
        event.setVehicleColor(trafficEvent.getM_VehicleColor());
        event.setImageUrl(imageUrl);
        event.setVehicleSize(trafficEvent.getM_VehicleSize());
        event.setSpeed(trafficEvent.getNSpeed());
        event.updateVehicle(vehicle);
        event.updateScore(score);
        event.updateViolation(score.getViolation());
        return eventRepository.save(event);
    }


    @Override
    public boolean support(String violationName) {
        return STOP.equals(violationName);
    }

}
