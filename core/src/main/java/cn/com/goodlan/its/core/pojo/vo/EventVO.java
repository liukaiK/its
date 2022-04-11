package cn.com.goodlan.its.core.pojo.vo;

import cn.com.goodlan.its.core.annotations.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EventVO {

    private String id;

    /**
     * 车牌号码
     */
    @Excel(name = "车牌号", sort = 1)
    private String vehicleNumber;

    /**
     * 车主姓名
     */
    @Excel(name = "车主姓名", sort = 2)
    private String driverName;

    /**
     * 车主电话
     */
    @Excel(name = "车主电话", cellType = Excel.ColumnType.STRING, sort = 3)
    private String driverPhone;

    /**
     * 部门名称
     */
    @Excel(name = "所属单位", defaultValue = "未知", sort = 4)
    private String bmmc;

    @Excel(name = "次序", cellType = Excel.ColumnType.STRING, sort = 5)
    private String num;

    /**
     * 违规分类
     */
    @Excel(name = "违规分类", cellType = Excel.ColumnType.STRING, sort = 6)
    private String violationName;

    /**
     * 扣了多少分
     */
    @Excel(name = "扣分", cellType = Excel.ColumnType.STRING, sort = 7)
    private String score;

    @Excel(name = "违规时间", dateFormat = "yyyy-MM-dd HH:mm:ss", sort = 8)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    /**
     * 违规地点
     */
    @Excel(name = "违规地点", sort = 9)
    private String place;

    private String regionName;

    private String laneNumber;

    private String vehicleColor;

    private String vehicleSize;

    private Integer speed;

    private String imageUrl;

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
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
