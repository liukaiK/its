package cn.com.goodlan.its.core.pojo.dto;

import cn.com.goodlan.its.core.annotations.Password;

public class ResetPasswordDTO {

    private String id;

    @Password
    private String password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
