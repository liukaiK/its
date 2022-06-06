package cn.com.goodlan.its.web.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    /**
     * 短信发送开关
     */
    private boolean enable;

    /**
     * 应用id
     */
    private String applicationId;

    /**
     * 应用密码
     */
    private String applicationPassword;

    /**
     * 发送短信的url
     */
    private String sendSmsUrl;

    private String welinkUrl;

    private String template;

    /**
     * 拉黑模版
     */
    private String blackTemplate;


}
