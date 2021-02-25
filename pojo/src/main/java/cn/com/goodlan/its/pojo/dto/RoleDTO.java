package cn.com.goodlan.its.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleDTO {

    private String id;

    @NotBlank(message = "请输入角色名称")
    private String roleName;

    private String remark;

    private String menuIds;

}
