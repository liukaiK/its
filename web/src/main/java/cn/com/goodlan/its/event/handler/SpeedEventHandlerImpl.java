package cn.com.goodlan.its.event.handler;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.dao.primary.sms.SmsHistoryRepository;
import cn.com.goodlan.its.core.pojo.MessageParam;
import cn.com.goodlan.its.core.pojo.entity.primary.Score;
import cn.com.goodlan.its.core.pojo.entity.primary.SmsHistory;
import cn.com.goodlan.its.core.pojo.entity.primary.ViolationType;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.service.event.CountService;
import cn.com.goodlan.its.core.service.system.score.ScoreService;
import cn.com.goodlan.its.core.util.DateUtils;
import cn.com.goodlan.its.core.util.StringUtils;
import cn.com.goodlan.its.web.properties.SmsProperties;
import cn.com.goodlan.its.web.sms.SmsService;
import cn.com.goodlan.its.web.sms.template.SmsMessageTemplate;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

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
    private EventRepository eventRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private SmsMessageTemplate smsMessageTemplate;

    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    @Autowired
    private SmsProperties smsProperties;

    @Autowired
    private ScoreService scoreService;

    @Override
    public void handler(Event event) {

        int speed = event.getSpeed();

        // 与数据库中设置的速度 判断是否超速了
        Score score = scoreService.getScore(event.getRegion().getScoreList(), speed);

        if (score == null) {
            log.info("-------车辆{}的速度{}不存在与系统范围中 没有超速---------", event.getLicensePlateNumber(), speed);
            return;
        }


        Long count = countService.queryCountThisYear(event.getLicensePlateNumber(), SPEED);
        if (count == 1) {
            if (score.isSpeed1()) {
                event = warn(event, score.getViolation(), score);
                sendSmsAndWeLink(event);
                return;
            }
            if (score.isSpeed2()) {
                event = warn(event, score.getViolation(), score);
                sendSmsAndWeLink(event);
                return;
            }
            if (score.isSpeed3()) {
                event = warnAndCalculateScoreAndHitBack(event, score, count);
                sendSmsAndWeLink(event);
            }
            return;
        }

        if (count == 2) {
            if (score.isSpeed1()) {
                event = calculateScore(event, score, count);
                sendSmsAndWeLink(event);
                return;
            }
            if (score.isSpeed2()) {
                event = calculateScoreAndHitBack(event, score, count);
                sendSmsAndWeLink(event);
                return;
            }
            if (score.isSpeed3()) {
                event = calculateScoreAndHitBack(event, score, count);
                sendSmsAndWeLink(event);
            }
            return;
        }

        if (count >= 3) {
            if (score.isSpeed1()) {
                event = calculateScoreAndHitBack(event, score, count);
                sendSmsAndWeLink(event);
                return;
            }
            if (score.isSpeed2()) {
                event = calculateScoreAndHitBack(event, score, count);
                sendSmsAndWeLink(event);
                return;
            }
            if (score.isSpeed3()) {
                event = calculateScoreAndHitBack(event, score, count);
                sendSmsAndWeLink(event);
            }
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

        if (count % 3 == 0L) {
            String backSmsTemplate = smsMessageTemplate.getBlackTemplate();
            return MessageFormat.format(backSmsTemplate, event.getLicensePlateNumber());
        }

        if (count == 1L) {
            punish = "警告";
            String smsTemplate = smsMessageTemplate.getSmsTemplate();
            return MessageFormat.format(smsTemplate, event.getLicensePlateNumber(), DateUtil.format(event.getTime(), DateUtils.YYYY_MM_DD_HH_MM_SS), event.getPlace(), SPEED, punish);
        }

        punish = "扣校内安全考核分";
        String smsTemplate = smsMessageTemplate.getSmsTemplate();
        return MessageFormat.format(smsTemplate, event.getLicensePlateNumber(), DateUtil.format(event.getTime(), DateUtils.YYYY_MM_DD_HH_MM_SS), event.getPlace(), SPEED, punish);

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
    private Event calculateScoreAndHitBack(Event event, Score score, Long count) {
        event.updateCount(count);
        event.updateScore(score);
        event.updateViolation(score.getViolation());
        return eventRepository.save(event);
    }

    /**
     * 警告+扣分+拉黑6个月
     */
    private Event warnAndCalculateScoreAndHitBack(Event event, Score score, Long count) {
        event.updateCount(count);
        event.updateScore(score);
        event.updateViolation(score.getViolation());
        return eventRepository.save(event);
    }


    /**
     * 警告
     */
    private Event warn(Event event, ViolationType violationType, Score score) {
        event.updateCount(1L);
        // 先设置分数 防止null
        event.updateScore(score);
        // 然后在刷新为0分
        event.updateScore(0);
        event.updateViolation(violationType);
        return eventRepository.save(event);
    }




    @Override
    public boolean support(String violationName) {
        return StringUtils.startsWith(violationName, SPEED);
    }

    /**
     * 扣分
     */
    protected Event calculateScore(Event event, Score score, Long count) {
        event.updateCount(count);
        event.updateScore(score);
        event.updateViolation(score.getViolation());
        return eventRepository.save(event);
    }

}
