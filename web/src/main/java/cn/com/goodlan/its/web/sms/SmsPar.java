package cn.com.goodlan.its.web.sms;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SmsPar {

    /**
     * 应用信息
     */
    private ApplicationInfo applicationInfo;

    /**
     * 手机号码不能超过1000个手机号
     */
    private String destAddresses;

    /**
     * 发送时间，不填写默认立即发送
     */
    private String aTTime;

    /**
     * 扩展码
     */
    private String extendCode;

    /**
     * 发送消息格式0-ASCII码发送，4-二进制码发送，8-USC2码发送，15-GB2312码发送，建议使用8，手机支持的最好
     */
    private int messageFormat;

    /**
     * 发送内容，字符不能超过100字符
     */
    private String messageContent;

    /**
     * 布尔类型，是否要状态报告
     */
    private boolean requireReport;


    /**
     * 优优先级 0 为最低 1最高
     */
    private int priority;

    /**
     * 请求系统用户ID
     */
    private String userId;

    /**
     * 请求系统用户名称，如果用户ID填写用户名称必须填写
     */
    private String username;

    public SmsPar(String mobilePhone, String content) {
        this.aTTime = "";
        this.messageFormat = 8;
        this.priority = 1;
        this.requireReport = false;
        this.extendCode = "343";


        this.destAddresses = mobilePhone;
        this.messageContent = content;

    }


}
