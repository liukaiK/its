package cn.com.goodlan.its.core.pojo.entity.primary;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 登录日志
 *
 * @author liukai
 */
@Entity
@Table(name = "sys_login_log")
public class LoginLog {


    private String id;

    private String username;

    private String ip;

    private String browser;

    private String os;

    private Integer message;

    private LocalDateTime loginTime;


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Column(columnDefinition = "tinyint")
    public Integer getMessage() {
        return message;
    }


    public void setMessage(Integer message) {
        this.message = message;
    }

    @Column(nullable = false)
    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }


}
