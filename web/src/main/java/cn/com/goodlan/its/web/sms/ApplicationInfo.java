package cn.com.goodlan.its.web.sms;

public class ApplicationInfo {

    private String applicationId;

    private String applicationPassword;

    public ApplicationInfo(String applicationId, String applicationPassword) {
        this.applicationId = applicationId;
        this.applicationPassword = applicationPassword;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationPassword() {
        return applicationPassword;
    }

    public void setApplicationPassword(String applicationPassword) {
        this.applicationPassword = applicationPassword;
    }
}
