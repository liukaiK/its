package cn.com.goodlan.its.pojo.vo;

import cn.com.goodlan.its.common.annotations.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventVO {

    private String id;

    /**
     * 车牌号码
     */
    @Excel(name = "车牌号", sort = 1)
    private String vehicleNumber;

    private String regionName;

    /**
     * 违规分类
     */
    private String violationName;

    private String laneNumber;

    private String vehicleColor;

    private String vehicleSize;

    /**
     * 扣了多少分
     */
    @Excel(name = "扣分", cellType = Excel.ColumnType.NUMERIC, sort = 9)
    private Integer score;

    /**
     * 违规地点
     */
    @Excel(name = "违规地点", sort = 4)
    private String place;

    private Integer status;

    @Excel(name = "车速", suffix = "km/h", sort = 8)
    private Integer speed;


    @Excel(name = "违规时间", dateFormat = "yyyy-MM-dd HH:mm:ss", sort = 5)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private String imageUrl;

    private Long num;

    /**
     * 车主姓名
     */
    @Excel(name = "车主姓名", sort = 2)
    private String driverName;

    /**
     * 车主电话
     */
    @Excel(name = "车主电话", sort = 10)
    private String driverPhone;

    /**
     * 部门名称
     */
    @Excel(name = "所属单位", defaultValue = "未知", sort = 3)
    private String bmmc;


}
