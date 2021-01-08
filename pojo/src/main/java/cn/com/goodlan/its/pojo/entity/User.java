package cn.com.goodlan.its.pojo.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户实体
 *
 * @author liukai
 */
@Entity
@Table(name = "sys_user")
public class User extends BaseEntity {

    public final static String ADMIN_ID = "1b3c1438-beb2-4bab-af86-b6b8dfb91114";

    private String id;

    private String username;

    private String name;

    private String password;

    private List<Role> roleList;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;


    @Transient
    public void addRole(Role role) {
        roleList.add(role);
    }


    /**
     * 判断是否为超级管理员
     */
    @Transient
    public boolean isAdmin() {
        return ADMIN_ID.equals(this.id);
    }


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, columnDefinition = "char(60)")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


}
