package cn.com.goodlan.its.core.pojo.entity.primary.user;

import cn.com.goodlan.its.core.pojo.entity.primary.AbstractEntity;
import cn.com.goodlan.its.core.pojo.entity.primary.College;
import cn.com.goodlan.its.core.pojo.entity.primary.Role;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户实体
 *
 * @author liukai
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "sys_user")
public class User extends AbstractEntity {

    private final static String ADMIN_ID = "1b3c1438-beb2-4bab-af86-b6b8dfb91114";

    private String id;

    private Username username;

    private String name;

    private Character sex;

    private String email;

    private Password password;

    private PhoneNumber phoneNumber;

    private String remark;

    private List<Role> roleList = new ArrayList<>();

    private College college;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    public User(String id) {
        this.id = id;
    }

    public User() {

    }

    @Transient
    public void updateEmail(String email) {
        this.email = email;
    }

    @Transient
    public void updateSex(Character sex) {
        this.sex = sex;
    }

    @Transient
    public void updateName(String name) {
        this.setName(name);
    }

    @Transient
    public void updateUsername(Username username) {
        this.setUsername(username);
    }

    @Transient
    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        this.setPhoneNumber(phoneNumber);
    }

    @Transient
    public void updateRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    public void updatePassword(Password password) {
        this.password = password;
    }

    @Transient
    public void removeAllRole() {
        this.roleList = new ArrayList<>();
    }

    @Transient
    public void addRole(Role role) {
        roleList.add(role);
    }

    @Transient
    public void addCollege(String collegeId) {
        this.college = new College(collegeId);
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

    @Embedded
    public Username getUsername() {
        return username;
    }

    protected void setUsername(Username username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    @Embedded
    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
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

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id")
    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    @Embedded
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    protected void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
