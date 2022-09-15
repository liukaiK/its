package cn.com.goodlan.its.core.pojo.dto;

import javax.validation.constraints.NotBlank;

public class RoleDTO {

    private String id;

    @NotBlank(message = "请输入角色名称")
    private String roleName;

    private String remark;

    private String menuIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

}
