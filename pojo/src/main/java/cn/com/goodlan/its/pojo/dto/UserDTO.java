package cn.com.goodlan.its.pojo.dto;

import cn.com.goodlan.its.common.annotations.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    private String id;

    private String name;

    /**
     * 只有新增用户的时候  才需要校验账号
     */
    @Username(groups = Create.class)
    private String username;

    /**
     * 只有新增用户的时候  才需要校验密码
     */
    @Password(groups = Create.class)
    private String password;

    @Email
    private String email;

    @MobileNumber
    private String phoneNumber;

    @Sex
    private String sex;

    @NotBlank(message = "请选择学院")
    private String collegeId;

    private String roleIds;

    private String remark;


}
