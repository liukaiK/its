package cn.com.goodlan.its.core.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegionDTO {

    private String id;

    @NotBlank(message = "区域名称不能为空")
    private String name;


}
