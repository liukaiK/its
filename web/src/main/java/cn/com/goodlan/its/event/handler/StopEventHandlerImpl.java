package cn.com.goodlan.its.event.handler;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.dao.primary.sms.SmsHistoryRepository;
import cn.com.goodlan.its.core.dao.primary.system.score.ScoreRepository;
import cn.com.goodlan.its.core.pojo.MessageParam;
import cn.com.goodlan.its.core.pojo.entity.primary.Score;
import cn.com.goodlan.its.core.pojo.entity.primary.SmsHistory;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.service.event.CountService;
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
    public String handler(Event event) {
        Long count = countService.queryCountThisYear(event.getLicensePlateNumber(), STOP);
        Score score = scoreRepository.getById("0f647018-2c28-4bfe-ae10-e9586cfb66b0");
        if (count == 1) {
            event = warn(event, score);
            sendSmsAndWeLink(event);
            return "第一次违停,已警告";
        }
        if (count == 2) {
            event = calculateScore(event, score, count);
            sendSmsAndWeLink(event);
            return "第二次违停,已扣分";
        }
        if (count == 3) {
            event = calculateScore(event, score, count);
            sendSmsAndWeLink(event);
            return "第三次违停,已扣分";
        }
        if (count >= 4) {
            event = calculateScore(event, score, count);
            sendSmsAndWeLink(event);
            return "违停已经超过4次,已扣分";
        }
        return "";
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
        if (StringUtils.isNotEmpty(phone)) {
            String smsMessageContent = buildSmsMessageContent(event);
            String smsSuccessResult = smsService.sendSms(phone, smsMessageContent);
            saveSmsHistory(phone, smsMessageContent, smsSuccessResult, event.getId());
        }

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
        String studstaffno = event.getStudstaffno();

        if (StringUtils.isNotEmpty(studstaffno)) {
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

            MessageParam messageParam = new MessageParam(studstaffno, event.getPlace(), DateUtil.format(event.getTime(), DateUtils.YYYY_MM_DD_HH_MM_SS), event.getLicensePlateNumber());
            messageParam.setContent(String.format("您的车辆%s于%s在%s，被交通技术监控设备记录了%s的违法行为。给予%s处罚，请知悉。点击查看详情。", event.getLicensePlateNumber(), DateUtil.format(event.getTime(), DateUtils.YYYY_MM_DD_HH_MM_SS), event.getPlace(), violationType, punish));
            log.info("WeLink:{}", messageParam.getContent());
            smsService.sendWeLink(messageParam);
        }
    }

    private Event warn(Event event, Score score) {
        event.updateScore(score);
        event.updateCount(1L);
        event.updateScore(0);
        event.updateViolation(score.getViolation());
        return eventRepository.save(event);
    }

    protected Event calculateScore(Event event, Score score, Long count) {
        event.updateCount(count);
        event.updateScore(score);
        event.updateViolation(score.getViolation());
        return eventRepository.save(event);
    }


    @Override
    public boolean support(String violationName) {
        return STOP.equals(violationName);
    }

}
