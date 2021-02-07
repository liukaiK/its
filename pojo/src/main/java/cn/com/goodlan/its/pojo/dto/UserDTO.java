package cn.com.goodlan.its.pojo.dto;

import cn.com.goodlan.its.common.annotations.Create;
import cn.com.goodlan.its.common.annotations.MobileNumber;
import cn.com.goodlan.its.common.annotations.Password;
import cn.com.goodlan.its.common.annotations.Sex;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserDTO {

    private String id;

    private String name;

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

    private String collegeId;

    private String roleIds;

    private String remark;


}
