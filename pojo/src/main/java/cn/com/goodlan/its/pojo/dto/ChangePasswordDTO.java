package cn.com.goodlan.its.pojo.dto;


import cn.com.goodlan.its.common.annotations.Password;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordDTO {

    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @Password
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @NotBlank(message = "确认密码不能为空")
    private String configPassword;


}
