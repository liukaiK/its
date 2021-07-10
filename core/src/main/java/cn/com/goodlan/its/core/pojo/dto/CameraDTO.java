package cn.com.goodlan.its.core.pojo.dto;

import cn.com.goodlan.its.core.annotations.IP;

import javax.validation.constraints.NotBlank;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
