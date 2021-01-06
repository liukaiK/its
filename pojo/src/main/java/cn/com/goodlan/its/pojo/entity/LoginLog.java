package cn.com.goodlan.its.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 登录日志
 *
 * @author liukai
 */
@Setter
@Getter
@Entity
@Table(name = "sys_login_log")
public class LoginLog {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String ip;

    private String browser;

    private String os;

    @Column(columnDefinition = "tinyint")
    private Integer message;

    @Column(nullable = false)
    private LocalDateTime loginTime;


}
