package cn.com.goodlan.its.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆违规次数
 *
 * @author liukai
 */
@Entity
@Table(name = "eve_count")
public class Count {

    private String licensePlateNumber;

    private Long count;

    @Id
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
