package cn.com.goodlan.its.pojo.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 记录实体
 *
 * @author liukai
 */
@Entity
@Table(name = "eve_record")
public class Record extends AbstractEntity {

    private String id;

    /**
     * 车牌号
     */
    private String licensePlateNumber;

    /**
     * 扣了多少分
     */
    private Integer record;

    private LocalDateTime time;

    private String regionName;

    private Long count;

    private String bmmc;

    public Record() {
    }


    public Record(String licensePlateNumber, Long count, String bmmc) {
        this.licensePlateNumber = licensePlateNumber;
        this.count = count;
        this.bmmc = bmmc;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id='" + id + '\'' +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", record=" + record +
                ", count=" + count +
                '}';
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

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }


    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    @Transient
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Column(name = "bmmc")
    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }
}
