package cn.com.goodlan.its.event;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.dao.primary.system.record.RecordRepository;
import cn.com.goodlan.its.core.pojo.MessageParam;
import cn.com.goodlan.its.core.pojo.entity.primary.Record;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.util.DateUtils;
import cn.com.goodlan.its.web.sms.SmsService;
import cn.com.goodlan.its.web.sms.template.SmsMessageTemplate;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private SmsMessageTemplate smsMessageTemplate;

    @Autowired
    private SmsService smsService;

    @Override
    public void approval(String id) {
        Event event = eventRepository.getOne(id);
        event.approval();
        recordRepository.save(new Record(event));
        sendSmsAndWeLink(event);
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
        log.debug("手机号码:{},短信内容:{}", phone, smsMessageContent);
        smsService.sendSms(phone, smsMessageContent);

    }

    /**
     * 构建短信内容
     */
    private String buildSmsMessageContent(Event event) {
        String smsTemplate = smsMessageTemplate.getSmsTemplate();

        String violationType = event.getScore().getViolation().getName();
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
        String violationType = event.getScore().getViolation().getName();
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
        messageParam.setContent(String.format("您的车辆%s于%s在%s，被交通技术监控设备记录了%s的违法行为。给予%s处罚，请知悉。点击查看详情。",
                event.getLicensePlateNumber(),
                DateUtil.format(event.getTime(), DateUtils.YYYY_MM_DD_HH_MM_SS),
                event.getPlace(), violationType, punish
        ));
        log.debug("WeLink:{}", messageParam.getContent());
        smsService.sendWelink(messageParam);
    }


    @Override
    public void cancel(String id) {
        Event event = eventRepository.getOne(id);
        event.cancel();
    }
}
