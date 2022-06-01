package cn.com.goodlan.its.web.sms.template;

import cn.com.goodlan.its.web.properties.SmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InMemorySmsMessageTemplate implements SmsMessageTemplate {

    @Autowired
    private SmsProperties smsProperties;

    @Override
    public String getSmsTemplate() {
        return smsProperties.getTemplate();
    }

    @Override
    public String getBlackTemplate() {
        return smsProperties.getBlackTemplate();
    }

}
