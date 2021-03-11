package cn.com.goodlan.its.pojo;

import cn.com.goodlan.its.pojo.entity.Role;
import lombok.Data;

@Data
public class SecurityRoleBean {

    private String id;

    private String name;

    public SecurityRoleBean() {

    }

    public SecurityRoleBean(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

}
