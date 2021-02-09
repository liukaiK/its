package cn.com.goodlan.its.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventVO {

    private String id;

    private String name;

    /**
     * 车牌号码
     */
    private String vehicleNumber;

    /**
     * 学院名称
     */
    private String collegeName;


    private String regionName;

    /**
     * 违规分类
     */
    private String violationName;

    private String laneNumber;

    private String vehicleColor;

    private String vehicleSize;

    /**
     * 违规地点
     */
    private String place;

    private Integer status;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private String imageUrl;

}
