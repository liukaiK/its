package cn.com.goodlan.its.pojo.entity.primary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liukai
 */
@MappedSuperclass
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable {

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 创建用户
     */
//    private String createBy;

    /**
     * 修改用户
     */
//    private String updateBy;


    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    //    @CreatedBy
//    @Column(name = "create_by")
//    public String getCreateBy() {
//        return createBy;
//    }

//    public void setCreateBy(String createBy) {
//        this.createBy = createBy;
//    }

    //    @LastModifiedBy
//    @Column(name = "update_by")
//    public String getUpdateBy() {
//        return updateBy;
//    }

//    public void setUpdateBy(String updateBy) {
//        this.updateBy = updateBy;
//    }

}
