package cn.com.goodlan.its.core.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CameraDTO {

    private String id;

    @NotBlank(message = "摄像头名称不能为空")
    private String name;

    private String ip;

    private String factory;

    @NotBlank(message = "请选择区域")
    private String regionId;

    @NotBlank(message = "位置不能为空")
    private String position;

}
