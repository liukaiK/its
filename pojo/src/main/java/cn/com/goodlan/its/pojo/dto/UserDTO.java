package cn.com.goodlan.its.pojo.dto;

import cn.com.goodlan.its.common.annotations.MobileNumber;
import cn.com.goodlan.its.common.annotations.Password;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserDTO {

    private String id;

    private String name;

    private String username;

    @Password
    private String password;

    @Email
    private String email;

    @MobileNumber
    private String phoneNumber;

    private String sex;

    private String collegeId;

    private String roleIds;

    private String remark;


}
