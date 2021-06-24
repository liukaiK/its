package cn.com.goodlan.its.core.pojo.vo;

import cn.com.goodlan.its.common.annotations.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getViolationName() {
        return violationName;
    }

    public void setViolationName(String violationName) {
        this.violationName = violationName;
    }

    public String getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(String laneNumber) {
        this.laneNumber = laneNumber;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }
}
