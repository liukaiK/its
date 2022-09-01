package cn.com.goodlan.its.core.pojo.entity.primary.event;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 事件实体
 *
 * @author liukai
 */
@Data
@Table
@Entity
@DynamicUpdate
@DynamicInsert
public class EventHistory {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    private String id;

    private String eventName;

    /**
     * 车主姓名
     */
    private String driverName;

    /**
     * 车牌号
     */
    private String licensePlateNumber;

    /**
     * 车主电话
     */
    private String driverPhone;


    /**
     * 违规地点
     */
    private String place;

    /**
     * 事件图片url
     */
    private String imageUrl;

    private String collegeName;

    /**
     * 车速
     */
    private Integer speed;

    private String ip;

    private String result;

    private LocalDateTime happenTime;

    private LocalDateTime createTime;

    public EventHistory() {
        this.createTime = LocalDateTime.now();
    }

}
