package cn.com.goodlan.its.pojo.dto;

import cn.com.goodlan.its.common.annotations.LicensePlateNumber;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VehicleDTO {

    private String id;

    @LicensePlateNumber
    private String number;

    private Integer type;

    @NotBlank(message = "请选择学院")
    private String collegeId;


}
