package cn.com.goodlan.its.core.pojo.entity.primary.event;

import cn.com.goodlan.its.core.exception.BusinessException;
import cn.com.goodlan.its.core.pojo.entity.primary.*;
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
@DynamicUpdate
@DynamicInsert
@Entity(name = "event")
@Table(name = "eve_event")
public class Event {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /**
     * 车主姓名
     */
    private String driverName;

    /**
     * 车牌号
     */
    private String licensePlateNumber;

    /**
     * 车主电话
     */
    private String driverPhone;

    /**
     * 部门名称
     */
    private String collegeName;

    /**
     * 工号
     */
    private String studstaffno;


    /**
     * 车道号
     */
    private String laneNumber;

    /**
     * 分值
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_id")
    private Score scoreId;

    /**
     * 扣了多少分 冗余字段
     */
    private Integer score;

    /**
     * 违规ID
     */
    private String violationId;

    /**
     * 违规名称 冗余字段
     */
    private String violationName;

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

    @Convert(converter = DeletedConverter.class)
    private Deleted deleted = Deleted.NORMAL;

    public Event() {
    }

    public Event(String id) {
        this.id = id;
    }


    /**
     * 获取当前违规事件发生在哪个区域
     */
    public Region getRegion() {
        Camera camera = getCamera();
        if (camera == null) {
            return null;
        }
        return camera.getRegion();
    }

    public boolean isSpeed1() {
        return this.getScoreId().isSpeed1();
    }

    public boolean isSpeed2() {
        return this.getScoreId().isSpeed2();
    }

    public boolean isSpeed3() {
        return this.getScoreId().isSpeed3();
    }

    public boolean isStop() {
        return this.getScoreId().isStop();
    }

    public void updateVehicle(Vehicle vehicle) {
        this.setLicensePlateNumber(vehicle.getLicensePlateNumber());
        this.setDriverName(vehicle.getDriverName());
        this.setDriverPhone(vehicle.getDriverPhone());
        this.setCollegeName(vehicle.getCollegeName());
        this.setStudstaffno(vehicle.getStudstaffno());
    }

    /**
     * 更新扣了多少分
     */
    public void updateScore(Score score) {
        this.setScoreId(score);
        this.setScore(score.getNumber());
    }

    /**
     * 更新扣了多少分
     */
    public void updateScore(int score) {
        this.setScore(score);
    }

    /**
     * 更新违规类型
     */
    public void updateViolation(ViolationType violationType) {
        if (violationType == null) {
            throw new BusinessException("违规类型不能为空");
        }
        this.setViolationId(violationType.getId());
        this.setViolationName(violationType.getName());
    }

    public void remove() {
        this.setDeleted(Deleted.DELETED);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Deleted getDeleted() {
        return deleted;
    }

    public void setDeleted(Deleted deleted) {
        this.deleted = deleted;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getTime() {
        return time;
    }

    public void updateHappenTime(LocalDateTime time) {
        this.time = time;
    }

    protected void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    protected void setLicensePlateNumber(String licensePlateNumber) {
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

    public Integer getScore() {
        return score;
    }

    protected void setScore(Integer score) {
        this.score = score;
    }

    public Score getScoreId() {
        return scoreId;
    }

    protected void setScoreId(Score scoreId) {
        this.scoreId = scoreId;
    }

    public String getViolationId() {
        return violationId;
    }

    protected void setViolationId(String violationId) {
        this.violationId = violationId;
    }

    public String getViolationName() {
        return violationName;
    }

    protected void setViolationName(String violationName) {
        this.violationName = violationName;
    }

    public Long getNum() {
        return num;
    }

    public void updateCount(Long count) {
        this.setNum(count);
    }

    protected void setNum(Long num) {
        this.num = num;
    }

    public String getDriverName() {
        return driverName;
    }

    protected void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    protected void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getCollegeName() {
        return collegeName;
    }

    protected void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getStudstaffno() {
        return studstaffno;
    }

    protected void setStudstaffno(String studstaffno) {
        this.studstaffno = studstaffno;
    }

    /**
     * 一定要实现BaseEnum
     *
     * @author liukai
     */
    public enum Deleted implements BaseEnum {

        NORMAL(0, "正常，未删除"), DELETED(1, "已删除");

        Deleted(Integer value, String description) {
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


    public static class DeletedConverter implements AttributeConverter<Deleted, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Deleted attribute) {
            if (attribute == null) {
                throw new BusinessException("Unknown attribute text  ");
            }
            return attribute.getValue();

        }

        @Override
        public Deleted convertToEntityAttribute(Integer dbData) {
            for (Deleted deleted : Deleted.values()) {
                if (deleted.getValue().equals(dbData)) {
                    return deleted;
                }
            }
            throw new BusinessException("Unknown dbData text : " + dbData);
        }
    }


}


