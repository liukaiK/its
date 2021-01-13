package cn.com.goodlan.its.pojo.dto;

import lombok.Data;

@Data
public class MenuDTO {

    private String id;

    private String name;

    private String url;

    private String parentId;

    /**
     * 菜单类型
     */
    private String menuType;

    /**
     * 打开方式
     */
    private String target;


    private String authority;

    private String icon;


}
