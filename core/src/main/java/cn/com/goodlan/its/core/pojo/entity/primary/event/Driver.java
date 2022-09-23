package cn.com.goodlan.its.core.pojo.entity.primary.event;

import javax.persistence.Embeddable;

@Embeddable
public class Driver {

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
     * 工号
     */
    private String studstaffno;

    protected Driver() {
    }

    public Driver(String driverName, String driverPhone, String collegeName, String studstaffno) {
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.collegeName = collegeName;
        this.studstaffno = studstaffno;
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

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getStudstaffno() {
        return studstaffno;
    }

    public void setStudstaffno(String studstaffno) {
        this.studstaffno = studstaffno;
    }
}
