package cn.com.goodlan.its.pojo.vo;

import cn.com.goodlan.its.common.annotations.Excel;
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
    @Excel(name = "车牌号")
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

    @Excel(name = "车辆颜色")
    private String vehicleColor;

    private String vehicleSize;

    /**
     * 扣了多少分
     */
    @Excel(name = "扣分", cellType = Excel.ColumnType.NUMERIC)
    private Integer score;

    /**
     * 违规地点
     */
    @Excel(name = "违规地点")
    private String place;

    private Integer status;

    @Excel(name = "车速", suffix = "km/h")
    private Integer speed;


    @Excel(name = "违规时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private String imageUrl;

    private Long num;


}
