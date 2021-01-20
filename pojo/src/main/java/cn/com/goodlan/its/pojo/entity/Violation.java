package cn.com.goodlan.its.pojo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description:
 *
 * @author: 王硕
 * @date: 2021/1/18-16:51
 */
@Entity
@Table(name = "sys_violation")
public class Violation extends BaseEntity {

    private String id;

    /**
     * 违规分类
     */
    private String name;
    /**
     * 违规编码
     */
    private String number;

    public Violation() {
    }

    public Violation(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }
}
