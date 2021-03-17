package cn.com.goodlan.its.web.sms;

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
    public void sendSms(String mobilePhone, String content) {
        SmsPar smsPar = new SmsPar(mobilePhone, content, smsProperties);
        try {
            String post = HttpUtil.post(smsProperties.getSendSmsUrl(), objectMapper.writeValueAsString(smsPar), 2000);
        } catch (RuntimeException e) {
            log.error("发送短信出现异常", e);
        } catch (JsonProcessingException e) {
            log.error("序列化JSON失败", e);
        }
    }

}
