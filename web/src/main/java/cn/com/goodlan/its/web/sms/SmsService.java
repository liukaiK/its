package cn.com.goodlan.its.web.sms;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface SmsService {

    /**
     * 发送短信
     *
     * @param mobilePhone 手机号
     * @param content     短信内容
     */
    void sendSms(String mobilePhone, String content) throws JsonProcessingException;

}
