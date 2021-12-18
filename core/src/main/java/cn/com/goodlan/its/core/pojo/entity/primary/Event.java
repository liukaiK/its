package cn.com.goodlan.its.core.pojo.entity.primary;

import cn.com.goodlan.its.core.exception.BusinessException;
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
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "eve_event")
public class Event {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /**
     * 违章车辆 系统中存在的车辆
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_id")
    private Score score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "violation_id")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camera_id")
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

    @Convert(converter = StatusConverter.class)
    private Status status = Status.NORMAL;

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
        if (Status.APPROVAL.equals(this.status)) {
            throw new BusinessException("该违规事件已经审批 请刷新页面");
        }
        this.setStatus(Status.APPROVAL);
        this.setApprovalTime(LocalDateTime.now());
    }

    @Transient
    public void cancel() {
        this.setStatus(Status.CANCEL);
        this.setApprovalTime(LocalDateTime.now());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

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

    /**
     * 一定要实现BaseEnum
     *
     * @author liukai
     */
    public enum Status implements BaseEnum {

        NORMAL(0, "未处理"),
        APPROVAL(1, "审批"),
        CANCEL(2, "作废");

        Status(Integer value, String description) {
            this.value = value;
            this.description = description;
        }

        private Integer value;
        private String description;

        @Override
        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


    public static class StatusConverter implements AttributeConverter<Status, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Status attribute) {
            if (attribute == null) {
                throw new BusinessException("Unknown status text  ");
            }
            return attribute.getValue();

        }

        @Override
        public Status convertToEntityAttribute(Integer dbData) {
            for (Status status : Status.values()) {
                if (status.getValue().equals(dbData)) {
                    return status;
                }
            }
            throw new BusinessException("Unknown status text : " + dbData);
        }
    }


}
