package cn.com.goodlan.its.pojo.dto;

import cn.com.goodlan.its.common.annotations.Password;
import lombok.Data;

@Data
public class ResetPasswordDTO {

    private String id;

    @Password
    private String password;

}
