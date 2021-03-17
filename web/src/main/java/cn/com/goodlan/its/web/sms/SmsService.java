package cn.com.goodlan.its.web.sms;

public interface SmsService {

    /**
     * 发送短信
     *
     * @param mobilePhone 手机号
     * @param content     短信内容
     */
    void sendSms(String mobilePhone, String content);

}
