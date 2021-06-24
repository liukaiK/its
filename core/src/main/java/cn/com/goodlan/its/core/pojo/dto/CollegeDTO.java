package cn.com.goodlan.its.core.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CollegeDTO {

    private String id;

    @NotBlank(message = "学院名称不能为空")
    private String name;

    private String parentId;

    private Integer sort;


}
