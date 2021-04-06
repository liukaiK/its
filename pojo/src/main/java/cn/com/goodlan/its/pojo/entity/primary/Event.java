package cn.com.goodlan.its.pojo.entity.primary;

import cn.com.goodlan.its.common.exception.BusinessException;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 事件实体
 *
 * @author liukai
 */
@ToString
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "eve_event")
public class Event {

    /**
     * 作废
     */
    public final static Integer CANCEL = 2;

    /**
     * 审批
     */
    public final static Integer APPROVAL = 1;


    private String id;

    /**
     * 违章车辆 系统中存在的车辆
     */
    private Vehicle vehicle;

    /**
     * 车牌号
     */
    private String licensePlateNumber;

    /**
     * 车道号
     */
    private String laneNumber;

    /**
     * 分值
     */
    private Score score;

    private ViolationType violation;

    /**
     * 违规车辆的颜色
     */
    private String vehicleColor;

    /**
     * 车辆尺寸 例 小型车
     */
    private String vehicleSize;

    /**
     * 摄像头
     */
    private Camera camera;

    /**
     * 违规地点
     */
    private String place;

    /**
     * 违规发生的时间
     */
    private LocalDateTime time;

    /**
     * 处理时间
     */
    private LocalDateTime approvalTime;

    /**
     * 事件图片url
     */
    private String imageUrl;

    /**
     * 车速
     */
    private Integer speed;

    /**
     * 第几次违规
     */
    private Long num;

    private Integer status = 0;

    public Event() {
    }

    public Event(String id) {
        this.id = id;
    }


    /**
     * 获取当前违规事件发生在哪个区域
     */
    @Transient
    public Region getRegion() {
        Camera camera = getCamera();
        if (camera == null) {
            return null;
        }
        return camera.getRegion();
    }

    /**
     * 审批
     */
    @Transient
    public void approval() {
        if (APPROVAL.equals(this.status)) {
            throw new BusinessException("该违规事件已经审批 请刷新页面");
        }
        this.setStatus(APPROVAL);
        this.setApprovalTime(LocalDateTime.now());
    }

    @Transient
    public void cancel() {
        this.setStatus(CANCEL);
        this.setApprovalTime(LocalDateTime.now());
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camera_id")
    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Column(columnDefinition = "tinyint")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "violation_id")
    public ViolationType getViolation() {
        return violation;
    }

    public void setViolation(ViolationType violation) {
        this.violation = violation;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Column
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(LocalDateTime approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(columnDefinition = "tinyint")
    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_id")
    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }


    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
