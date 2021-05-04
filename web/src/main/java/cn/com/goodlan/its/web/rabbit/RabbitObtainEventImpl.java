package cn.com.goodlan.its.web.rabbit;

import cn.com.goodlan.its.dao.primary.event.EventRepository;
import cn.com.goodlan.its.dao.primary.system.camera.CameraRepository;
import cn.com.goodlan.its.dao.primary.system.vehicle.VehicleRepository;
import cn.com.goodlan.its.dao.secondary.HitBackRepository;
import cn.com.goodlan.its.pojo.TrafficEvent;
import cn.com.goodlan.its.pojo.entity.primary.*;
import cn.com.goodlan.its.pojo.entity.secondary.HitBack;
import cn.com.goodlan.its.service.event.CountService;
import cn.com.goodlan.its.web.sms.SmsService;
import cn.hutool.core.codec.Base64Decoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

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
    private SmsService smsService;

    @Autowired
    private CountService countService;

    @Autowired
    private HitBackRepository hitBackRepository;

    @Autowired
    protected FastFileStorageClient storageClient;

    @Autowired
    private ObjectMapper objectMapper;

    private String status = "";

    private String message2 = "您好，您的车辆 XXXX（车牌号）于2021年3月13日11:25:39（违规时间）在XXX（违规地点）超速/违停（违规分类），扣分XX（分）。您已经尾骨超过4次被系统拉黑。";

    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    @RabbitListener(queuesToDeclare = @Queue(name = "its.traffic.event", durable = "true"))
    public synchronized void obtainEvent(String message) throws JsonProcessingException {
        String content = StringEscapeUtils.unescapeJava(message);
        if (StringUtils.startsWithIgnoreCase(content, "\"")) {
            content = content.substring(1, content.length() - 1);
        }
        TrafficEvent trafficEvent = objectMapper.readValue(content, TrafficEvent.class);

        logTrafficEvent(trafficEvent);

        if (StringUtils.isEmpty(trafficEvent.getM_PlateNumber())) {
            return;
        }

        if ("超速".equals(trafficEvent.getM_EventName())) {
            exceedSpeedHandle(trafficEvent);
            return;
        }

        if ("违章停车".equals(trafficEvent.getM_EventName())) {
            // 违规车辆的车牌号
            String licensePlateNumber = trafficEvent.getM_PlateNumber();
            // 查询此车辆在系统里存不存在
            Optional<Vehicle> optionalVehicle = vehicleRepository.findById(licensePlateNumber);
            if (optionalVehicle.isPresent()) {
                Camera camera = cameraRepository.getByIp(trafficEvent.getIp());


                if (camera == null) {
                    return;
                }


                Vehicle vehicle = optionalVehicle.get();
                Long count = countService.queryCountAndSave(trafficEvent.getM_PlateNumber());
                Score score = new Score("0f647018-2c28-4bfe-ae10-e9586cfb66b0");
                saveEvent(trafficEvent, score, vehicle, camera, count);

                // 首次违规
//            if (count == 1) {
//                sendMessage(vehicle.getDriverPhone(), String.format("您好，您的车辆%s于%s在%s超速，系统对您于警告处理。",
//                        vehicle.getLicensePlateNumber(),
//                        DateUtil.format(trafficEvent.getM_Utc(), DateUtils.YYYY_MM_DD_HH_MM_SS),
//                        trafficEvent.getM_IllegalPlace()
//                        )
//                );
//            }

                // 第二次和第三次违规
//            if (count == 2 || count == 3) {
//                sendMessage(vehicle.getDriverPhone(), String.format("您好，您的车辆%s于%s在%s超速，扣分%s分。违规超过4次将被进行拉黑处理。",
//                        vehicle.getLicensePlateNumber(),
//                        DateUtil.format(trafficEvent.getM_Utc(), DateUtils.YYYY_MM_DD_HH_MM_SS),
//                        trafficEvent.getM_IllegalPlace(),
//                        score.getNumber()
//                        )
//                );
//            }

//                if (count > 3) {
//                    // TODO 发送短信
//                    // 拉黑
//                    try {
//                        hitBack(licensePlateNumber);
//                    } catch (Exception e) {
//                        log.error("拉黑失败", e);
//                    }
//                }


            }


            return;
        }


    }

    /**
     * 超速处理
     */
    private void exceedSpeedHandle(TrafficEvent trafficEvent) {
        // 判断是否和上一条数据相同 相同的话直接跳过不记录
        if (isSameWithPrevious(trafficEvent)) {
            return;
        }

        Camera camera = cameraRepository.getByIp(trafficEvent.getIp());


        if (camera == null) {
            return;
        }

        Region region = camera.getRegion();

        if (region == null) {
            return;
        }

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
            return;
        }

        // 违规车辆的车牌号
        String licensePlateNumber = trafficEvent.getM_PlateNumber();


        // 查询此车辆在系统里存不存在
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(licensePlateNumber);

        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            Long count = countService.queryCountAndSave(trafficEvent.getM_PlateNumber());

            // 首次违规
            if (count == 1) {
//                sendMessage(vehicle.getDriverPhone(), String.format("您好，您的车辆%s于%s在%s超速，系统对您于警告处理。", vehicle.getLicensePlateNumber(), DateUtil.format(trafficEvent.getM_Utc(), DateUtils.YYYY_MM_DD_HH_MM_SS), trafficEvent.getM_IllegalPlace()));


                if (score.isScore2() || score.isScore3()) {
                    saveEvent(trafficEvent, score, vehicle, camera, count);
                }

                return;
            }

            // 第二次和第三次违规
            if (count == 2 || count == 3) {
//                sendMessage(vehicle.getDriverPhone(), String.format("您好，您的车辆%s于%s在%s超速，扣分%s分。违规超过4次将被进行拉黑处理。", vehicle.getLicensePlateNumber(), DateUtil.format(trafficEvent.getM_Utc(), DateUtils.YYYY_MM_DD_HH_MM_SS), trafficEvent.getM_IllegalPlace(), score.getNumber()));
                saveEvent(trafficEvent, score, vehicle, camera, count);
                return;
            }

            if (count > 3) {
                // TODO 发送短信
                // 拉黑
                try {
                    hitBack(licensePlateNumber);
                } catch (Exception e) {
                    log.error("拉黑失败", e);
                }
            }


        }
    }

    private void hitBack(String licensePlateNumber) {
        HitBack hitBack = new HitBack();
        hitBack.setVehplate(licensePlateNumber);
        hitBack.setBackTime(LocalDateTime.now());
        hitBack.setRemark("拉黑");
        hitBackRepository.save(hitBack);
    }

    private void saveEvent(TrafficEvent trafficEvent, Score score, Vehicle vehicle, Camera camera, Long count) {
        Event event = new Event();

        event.setNum(count);
        event.setCamera(camera);
        event.setVehicle(vehicle);
        event.setPlace(trafficEvent.getM_IllegalPlace());
        event.setLicensePlateNumber(trafficEvent.getM_PlateNumber());
        event.setTime(trafficEvent.getM_Utc().toInstant().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime());
        event.setLaneNumber(trafficEvent.getM_LaneNumber());
        event.setVehicleColor(trafficEvent.getM_VehicleColor());
        event.setImageUrl(getImageUrl(trafficEvent.getBigImage()));
        event.setVehicleSize(trafficEvent.getM_VehicleSize());
        event.setSpeed(trafficEvent.getNSpeed());
        event.setScore(score);
        eventRepository.save(event);
    }

    /**
     * 发送短信
     */
    private void sendMessage(String phone, String message) {
        smsService.sendSms(phone, message);
    }

    /**
     * 打印
     */
    private void logTrafficEvent(TrafficEvent trafficEvent) {
        log.info(trafficEvent.toString());
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

    /**
     * 获取事件图片URL
     */
    private String getImageUrl(String base64ImageStr) {
        if (StringUtils.isEmpty(base64ImageStr)) {
            return null;
        }
        byte[] bytes = Base64Decoder.decode(base64ImageStr);
        StorePath storePath = uploadFile(bytes);
        return "/" + storePath.getFullPath();
    }

    /**
     * 上传文件
     */
    private StorePath uploadFile(byte[] b) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(b);
        FastImageFile fastImageFile = new FastImageFile.Builder()
                .withFile(inputStream, inputStream.available(), "png")
                .build();
        return storageClient.uploadImage(fastImageFile);
    }

}
