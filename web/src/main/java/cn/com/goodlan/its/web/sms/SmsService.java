package cn.com.goodlan.its.web.sms;

import cn.com.goodlan.its.core.pojo.MessageParam;

public interface SmsService {

    /**
     * 发送短信
     *
     * @param mobilePhone 手机号
     * @param content     短信内容
     */
    String sendSms(String mobilePhone, String content);

    /**
     * weLink推送
     *
     * @param messageParam
     */
    void sendWeLink(MessageParam messageParam);
}
