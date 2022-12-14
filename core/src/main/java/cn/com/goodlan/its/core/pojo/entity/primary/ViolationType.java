package cn.com.goodlan.its.core.pojo.entity.primary;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 违规分类
 *
 * @author 王硕
 */
@Entity
@Table(name = "sys_violation_type")
public class ViolationType extends AbstractEntity {

    private String id;

    /**
     * 违规分类
     */
    private String name;

    /**
     * 违规编码
     */
    private String code;

    private String remark;

    public ViolationType() {
    }

    public ViolationType(String id) {
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

    public void setName(String name) {
        this.name = name;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
