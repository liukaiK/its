package cn.com.goodlan.its.core.pojo.dto;

import cn.com.goodlan.its.common.annotations.IP;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CameraDTO {

    private String id;

    @NotBlank(message = "摄像头名称不能为空")
    private String name;

    @IP
    @NotBlank(message = "IP不能为空")
    private String ip;

    @NotBlank(message = "请选择区域")
    private String regionId;

    @NotBlank(message = "位置不能为空")
    private String position;

}
