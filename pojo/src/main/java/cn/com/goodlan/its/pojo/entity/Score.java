package cn.com.goodlan.its.pojo.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 分值实体类
 *
 * @author liukai
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "sys_score")
public class Score extends BaseEntity {

    private String id;

    private String name;

    /**
     * 分值,扣多少分
     */
    private Integer number;

    private ViolationType violation;

    private Region region;

    private String remark;

    /**
     * 分值最小值
     */
    private Integer minRange;


    /**
     * 分值最大值
     */
    private Integer maxRange;

    public Score() {
    }

    public Score(String scoreId) {
        this.id = scoreId;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "violation_id")
    public ViolationType getViolation() {
        return violation;
    }

    public void setViolation(ViolationType violation) {
        this.violation = violation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(columnDefinition = "tinyint")
    public Integer getMinRange() {
        return minRange;
    }

    public void setMinRange(Integer minRange) {
        this.minRange = minRange;
    }

    @Column(columnDefinition = "tinyint")
    public Integer getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Integer maxRange) {
        this.maxRange = maxRange;
    }
}
