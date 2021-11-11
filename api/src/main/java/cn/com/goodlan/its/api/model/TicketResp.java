package cn.com.goodlan.its.api.model;

public class TicketResp {

    private String code;
    private String message;
    private  String access_token;
    private  Integer expire_in;

    public TicketResp() { }

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

    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpire_in() {
        return expire_in;
    }
    public void setExpire_in(Integer expire_in) {
        this.expire_in = expire_in;
    }

    @Override
    public String toString() {

        return "TicketResp {" +
                "code='" + code + '\'' +
                ", message=" + message +
                ", access_token=" + access_token +
                ", expire_in='" + expire_in + '\'' +
                '}';
    }
}