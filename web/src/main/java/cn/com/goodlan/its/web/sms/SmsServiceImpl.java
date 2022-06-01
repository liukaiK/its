package cn.com.goodlan.its.web.sms;

import cn.com.goodlan.its.core.pojo.MessageParam;
import cn.com.goodlan.its.web.properties.SmsProperties;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private SmsProperties smsProperties;


    @Override
    public String sendSms(String mobilePhone, String content) {
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
    public void sendWelink(MessageParam messageParam) {
        try {
            HttpUtil.post(smsProperties.getWelinkUrl(), objectMapper.writeValueAsString(messageParam), 2000);
        } catch (Exception e) {
            log.error("welink推送失败", e);
        }

    }
}
