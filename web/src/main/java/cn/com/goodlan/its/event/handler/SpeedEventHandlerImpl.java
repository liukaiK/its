package cn.com.goodlan.its.event.handler;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.dao.primary.system.camera.CameraRepository;
import cn.com.goodlan.its.core.dao.primary.system.vehicle.VehicleRepository;
import cn.com.goodlan.its.core.file.FileUpload;
import cn.com.goodlan.its.core.pojo.MessageParam;
import cn.com.goodlan.its.core.pojo.TrafficEvent;
import cn.com.goodlan.its.core.pojo.entity.primary.*;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.service.event.CountService;
import cn.com.goodlan.its.core.util.DateUtils;
import cn.com.goodlan.its.web.sms.SmsService;
import cn.com.goodlan.its.web.sms.template.SmsMessageTemplate;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

/**
 * 处理超速事件
 *
 * @author liukai
 */
@Slf4j
@Component
public class SpeedEventHandlerImpl implements EventHandler {

    private final static String SPEED = "超速";

    @Autowired
    private CountService countService;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private FileUpload fileUpload;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private SmsMessageTemplate smsMessageTemplate;


    private String status = "";

    @Override
    public void handler(TrafficEvent trafficEvent) {
        // 判断是否和上一条数据相同 相同的话直接跳过不记录
//        if (isSameWithPrevious(trafficEvent)) {
//            return;
//        }

        Camera camera = cameraRepository.getByIp(trafficEvent.getIp());

        if (camera == null) {
            log.warn("没有ip为{}的摄像头", trafficEvent.getIp());
            return;
        }

        Region region = camera.getRegion();

        if (region == null) {
            log.warn("此摄像头{}没有配置区域", camera.getIp());
            return;
        }


        String imageUrl = fileUpload.uploadImage(trafficEvent.getBigImage());

        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(trafficEvent.getM_PlateNumber());

        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();

            List<Score> scoreList = region.getScoreList();

            int speed = trafficEvent.getNSpeed();

            // 与数据库中设置的速度 判断是否超速了
            Score score = null;
            for (Score tempScore : scoreList) {
                Integer min = tempScore.getMinRange();
                Integer max = tempScore.getMaxRange();

                if (min == null) {
                    min = Integer.MIN_VALUE;
                }

                if (max == null) {
                    max = Integer.MAX_VALUE;
                }

                if (min <= speed && speed <= max) {
                    score = tempScore;
                    break;
                }
            }

            if (score == null) {
                log.trace("-------车辆的速度{}不存在与系统范围中 没有超速---------", speed);
                return;
            }


            Long count = countService.queryCount(vehicle.getLicensePlateNumber(), SPEED);
            Event event;
            if (count == 1) {
                if (score.isScore1()) {
                    event = warn(camera, trafficEvent, imageUrl, vehicle, score.getViolation());
                    sendSmsAndWeLink(event);
                    return;
                }
                if (score.isScore2()) {
                    event = warn(camera, trafficEvent, imageUrl, vehicle, score.getViolation());
                    sendSmsAndWeLink(event);
                    return;
                }
                if (score.isScore3()) {
                    event = warnAndCalculateScoreAndHitBack(trafficEvent, score, vehicle, camera, count, imageUrl);
                    sendSmsAndWeLink(event);
                }
                return;
            }

            if (count == 2) {
                if (score.isScore1()) {
                    event = calculateScore(trafficEvent, score, vehicle, camera, count, imageUrl);
                    sendSmsAndWeLink(event);
                    return;
                }
                if (score.isScore2()) {
                    event = calculateScoreAndHitBack(trafficEvent, score, vehicle, camera, count, imageUrl);
                    sendSmsAndWeLink(event);
                    return;
                }
                if (score.isScore3()) {
                    event = calculateScoreAndHitBack(trafficEvent, score, vehicle, camera, count, imageUrl);
                    sendSmsAndWeLink(event);
                }
                return;
            }

            if (count >= 3) {
                if (score.isScore1()) {
                    event = calculateScoreAndHitBack(trafficEvent, score, vehicle, camera, count, imageUrl);
                    sendSmsAndWeLink(event);
                    return;
                }
                if (score.isScore2()) {
                    event = calculateScoreAndHitBack(trafficEvent, score, vehicle, camera, count, imageUrl);
                    sendSmsAndWeLink(event);
                    return;
                }
                if (score.isScore3()) {
                    event = calculateScoreAndHitBack(trafficEvent, score, vehicle, camera, count, imageUrl);
                    sendSmsAndWeLink(event);
                }
            }


        } else {
            log.warn("此车牌号系统中不存在:{}", trafficEvent.getM_PlateNumber());
        }


    }

    private void sendSmsAndWeLink(Event event) {
        sendSmsMessage(event);
        sendWeLink(event);
    }

    /**
     * 发送短信
     */
    private void sendSmsMessage(Event event) {
        String phone = event.getDriverPhone();
        String smsMessageContent = buildSmsMessageContent(event);
        smsService.sendSms(phone, smsMessageContent);
    }


    /**
     * 构建短信内容
     */
    private String buildSmsMessageContent(Event event) {
        String smsTemplate = smsMessageTemplate.getSmsTemplate();

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
        return MessageFormat.format(smsTemplate, event.getLicensePlateNumber(), DateUtil.format(event.getTime(), DateUtils.YYYY_MM_DD_HH_MM_SS), event.getPlace(), violationType, punish);
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

    /**
     * 扣分+拉黑3个月
     */
    private Event calculateScoreAndHitBack(TrafficEvent trafficEvent, Score score, Vehicle vehicle, Camera camera, Long count, String imageUrl) {
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

    /**
     * 警告+扣分+拉黑6个月
     */
    private Event warnAndCalculateScoreAndHitBack(TrafficEvent trafficEvent, Score score, Vehicle vehicle, Camera camera, Long count, String imageUrl) {
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


    /**
     * 警告
     */
    private Event warn(Camera camera, TrafficEvent trafficEvent, String imageUrl, Vehicle vehicle, ViolationType violationType) {
        Event event = new Event();

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
        event.updateViolation(violationType);
        return eventRepository.save(event);


    }

    /**
     * 会收到多条一样的消息 所以要过滤
     */
    private boolean isSameWithPrevious(TrafficEvent trafficEvent) {
        String tempStatus = trafficEvent.getM_PlateNumber() + trafficEvent.getIp();
        if (status.equals(tempStatus)) {
            return true;
        } else {
            status = tempStatus;
            return false;
        }
    }


    @Override
    public boolean support(String violationName) {
        return SPEED.equals(violationName);
    }

    /**
     * 扣分
     */
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

}
