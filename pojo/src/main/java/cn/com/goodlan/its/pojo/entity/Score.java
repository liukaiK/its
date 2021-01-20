package cn.com.goodlan.its.pojo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 分值实体类
 *
 * @author liukai
 */
@Entity
@Table(name = "sys_score")
public class Score extends BaseEntity {

    private String id;

    private String name;

    private Integer number;

    private Violation violation;

    private Region region;


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
    public Violation getViolation() {
        return violation;
    }

    public void setViolation(Violation violation) {
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
}
