package cn.com.goodlan.its.pojo.entity;

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
     * 事件名称
     */
    private String name;

    /**
     * 违章车辆 系统中存在的车辆
     */
    private Vehicle vehicle;

    /**
     * 车牌号
     */
    private String licensePlateNumber;

    private Violation violation;

    /**
     * 摄像头
     */
    private Camera camera;

    /**
     * 违规地点
     */
    private String place;

    /**
     * 违规时间
     */
    private LocalDateTime violationTime;

    private Integer status = 0;

    public Event() {
    }

    public Event(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Violation getViolation() {
        return violation;
    }

    public void setViolation(Violation violation) {
        this.violation = violation;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(LocalDateTime violationTime) {
        this.violationTime = violationTime;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }
}
