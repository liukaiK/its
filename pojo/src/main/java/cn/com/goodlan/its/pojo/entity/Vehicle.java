package cn.com.goodlan.its.pojo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 车辆
 *
 * @author liukai
 */
@Entity
@Table(name = "sys_vehicle")
public class Vehicle {

    private String id;

    /**
     * 车牌号
     */
    private String licensePlateNumber;


    /**
     * 校内车还是校外车
     */
    private Integer type;


    private College college;

    /**
     * 车主姓名
     */
    private String driverName;

    /**
     * 车主电话
     */
    private String driverPhone;

    public Vehicle() {

    }

    public Vehicle(String id) {
        this.id = id;
    }

    @Transient
    public void addCollege(String collegeId) {
        this.college = new College(collegeId);
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
    @JoinColumn(name = "college_id")
    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }


    @Column(columnDefinition = "tinyint")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    @Column
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Column
    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
}
