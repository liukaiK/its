package cn.com.goodlan.its.core.pojo.entity.primary;

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
public class Score extends AbstractEntity {

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


    /**
     * 判断是否为超速1类型的
     */
    @Transient
    public boolean isSpeed1() {
        return "126e87e3-42e3-4e53-8128-005843a65173".equals(this.id) || "fee71ddd-9116-4952-b8a1-13639b3032d3".equals(this.id);
    }


    /**
     * 判断是否为超速2类型的
     */
    @Transient
    public boolean isSpeed2() {
        return "e26ee558-26bc-44a6-9767-909fe9accf09".equals(this.id) || "f6a3a200-9c0d-4606-a218-554fb637c908".equals(this.id);
    }


    /**
     * 判断是否为超速3类型的
     */
    @Transient
    public boolean isSpeed3() {
        return "14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d".equals(this.id) || "35552577-cfd6-480e-8586-f7568bb943b4".equals(this.id);
    }

    /**
     * 判断是否为违章停车
     */
    @Transient
    public boolean isStop() {
        return "0f647018-2c28-4bfe-ae10-e9586cfb66b0".equals(this.id);
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
