package cn.com.goodlan.its.api.model;

public class UserResp {

    private String code;
    private String message;
    private String userId;
    private String tenantId;

    public UserResp() { }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTenantId() { return tenantId; }
    public  void setTenantId(String tenantId) { this.tenantId = tenantId;}

    @Override
    public String toString() {
        return "UserResp {" +
                "code='" + code + '\'' +
                ", message=" + message +
                ", userId=" + userId +
                ", tenantId=" + tenantId +
                '}';
    }
}

