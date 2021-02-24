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
public class Record extends BaseEntity {

    private String id;

    /**
     * 车牌号
     */
    private String licensePlateNumber;

    private Score score;

    /**
     * 扣了多少分
     */
    private Integer record;

    private College college;

    private LocalDateTime time;

    private String regionName;

    private Long count;

    public Record() {
    }


    public Record(String licensePlateNumber, Long count) {
        this.licensePlateNumber = licensePlateNumber;
        this.count = count;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_id")
    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id")
    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
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
}
