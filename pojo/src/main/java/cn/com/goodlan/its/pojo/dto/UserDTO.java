package cn.com.goodlan.its.pojo.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserDTO {

    private String id;

    private String name;

    private String username;

    private String password;

    @Email
    private String email;

    private String phoneNumber;

    private String sex;

    private String roleIds;

}
