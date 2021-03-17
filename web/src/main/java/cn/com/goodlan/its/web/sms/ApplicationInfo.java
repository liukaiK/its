package cn.com.goodlan.its.web.sms;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationInfo {

    private String applicationId;

    private String applicationPassword;

    public ApplicationInfo(String applicationId, String applicationPassword) {
        this.applicationId = applicationId;
        this.applicationPassword = applicationPassword;
    }

}
