package cn.com.goodlan.its.core.pojo.entity.primary;

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
     * 车主姓名
     */
    private String driverName;

    /**
     * 车主电话
     */
    private String driverPhone;

    /**
     * 部门名称
     */
    private String collegeName;

    /**
     * 例:教职工车辆(B1类)
     */
    private String carType;

    /**
     * 工号
     */
    private String studstaffno;

    protected Vehicle() {

    }

    public Vehicle(String id) {
        this.id = id;
    }

    public Vehicle(String licensePlateNumber, String driverName, String driverPhone, String collegeName, String studstaffno) {
        this.licensePlateNumber = licensePlateNumber;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.collegeName = collegeName;
        this.studstaffno = studstaffno;
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
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

    @Column(name = "bmmc")
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getStudstaffno() {
        return studstaffno;
    }

    public void setStudstaffno(String studstaffno) {
        this.studstaffno = studstaffno;
    }
}
