package cn.com.goodlan.its.core.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MenuDTO {

    private String id;

    @NotBlank(message = "菜单名称不能为空")
    private String name;

    private String url;

    private String parentId;

    /**
     * 菜单类型
     */
    @NotBlank(message = "请选择菜单类型")
    private String menuType;

    /**
     * 打开方式
     */
    private String target;


    private String authority;

    private String icon;

    private Integer sort;


}
