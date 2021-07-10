package cn.com.goodlan.its.core.pojo.dto;

import cn.com.goodlan.its.core.annotations.Password;
import lombok.Data;

@Data
public class ResetPasswordDTO {

    private String id;

    @Password
    private String password;

}
