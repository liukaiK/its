package cn.com.goodlan.its.pojo.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 记录实体
 *
 * @author liukai
 */
@Entity
@Table(name = "eve_record")
public class Record extends BaseEntity {

    private String id;

    private Event event;

    private Score score;

    /**
     * 扣了多少分
     */
    private Integer record;

    private College college;


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
    @JoinColumn(name = "event_id")
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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


}
