package cn.com.goodlan.its.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleVO {

    private String id;

    private String number;

    private Integer type;

    /**
     * 车主姓名
     */
    private String driverName;

    /**
     * 车主电话
     */
    private String driverPhone;

    private String collegeId;

    private String collegeName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
