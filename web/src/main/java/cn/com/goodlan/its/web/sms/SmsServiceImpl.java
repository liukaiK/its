package cn.com.goodlan.its.web.sms;

import cn.com.goodlan.its.core.exception.BusinessException;
import cn.com.goodlan.its.core.pojo.MessageParam;
import cn.com.goodlan.its.core.util.StringUtils;
import cn.com.goodlan.its.web.properties.SmsProperties;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private SmsProperties smsProperties;


    @Override
    public String sendSms(String mobilePhone, String content) {
        if (StringUtils.isEmpty(mobilePhone)) {
            log.error("发送短信失败,手机号码不能为空");
            throw new BusinessException("手机号码不能为空");
        }
        SmsPar smsPar = new SmsPar(mobilePhone, content, smsProperties);
        String result;
        try {
            result = HttpUtil.post(smsProperties.getSendSmsUrl(), objectMapper.writeValueAsString(smsPar), 2000);
            log.info("手机号码:{},短信内容:{},短信状态:{}", mobilePhone, content, result);
        } catch (RuntimeException e) {
            result = e.getMessage();
            log.error("发送短信出现异常", e);
        } catch (JsonProcessingException e) {
            result = e.getMessage();
            log.error("序列化JSON失败", e);
        }
        return result;
    }

    /**
     * welink推送
     *
     * @param messageParam
     */
    @Override
    public void sendWeLink(MessageParam messageParam) {
        if (StringUtils.isEmpty(messageParam.getStudstaffno())) {
            log.error("weLink推送失败,工号不能为空");
            return;
        }
        try {
            HttpUtil.post(smsProperties.getWelinkUrl(), objectMapper.writeValueAsString(messageParam), 2000);
        } catch (Exception e) {
            log.error("weLink推送失败", e);
        }

    }
}
