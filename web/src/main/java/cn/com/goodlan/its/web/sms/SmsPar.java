package cn.com.goodlan.its.web.sms;

import cn.com.goodlan.its.web.properties.SmsProperties;

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

    public SmsPar(String mobilePhone, String content, SmsProperties smsProperties) {
        this.aTTime = "";
        this.messageFormat = 8;
        this.priority = 1;
        this.requireReport = false;
        this.extendCode = "";
        this.destAddresses = mobilePhone;
        this.messageContent = content;
        this.applicationInfo = new ApplicationInfo(smsProperties.getApplicationId(), smsProperties.getApplicationPassword());

    }

    public ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }

    public String getDestAddresses() {
        return destAddresses;
    }

    public void setDestAddresses(String destAddresses) {
        this.destAddresses = destAddresses;
    }

    public String getaTTime() {
        return aTTime;
    }

    public void setaTTime(String aTTime) {
        this.aTTime = aTTime;
    }

    public String getExtendCode() {
        return extendCode;
    }

    public void setExtendCode(String extendCode) {
        this.extendCode = extendCode;
    }

    public int getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(int messageFormat) {
        this.messageFormat = messageFormat;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public boolean isRequireReport() {
        return requireReport;
    }

    public void setRequireReport(boolean requireReport) {
        this.requireReport = requireReport;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
