package cn.com.goodlan.its.core.pojo.dto;

import cn.com.goodlan.its.core.annotations.MobileNumber;
import cn.com.goodlan.its.core.annotations.Sex;
import lombok.Data;

import javax.validation.constraints.Email;

/**
 * 修改个人信息DTO
 */
@Data
public class UpdateProfileDTO {

    @MobileNumber
    private String phoneNumber;

    @Email
    private String email;

    @Sex
    private Character sex;


}
