package cn.com.goodlan.its.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MenuVO {

    private String id;

    private String name;

    private String authority;

    private String url;

    private String icon;

    private String menuType;

    private String isRefresh;

    private String parentId;

    private String parentName;

    private Integer sort;

    /**
     * 打开方式 页签 或是 新窗口
     */
    private String target;

    private String visible;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
