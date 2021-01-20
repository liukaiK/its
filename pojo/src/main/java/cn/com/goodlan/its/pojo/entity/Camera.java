package cn.com.goodlan.its.pojo.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 摄像头实体类
 *
 * @author liukai
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "sys_camera")
public class Camera extends BaseEntity {

    private String id;

    private String name;

    private String ip;

    /**
     * 厂家
     */
    private String factory;

    private Region region;

    /**
     * 位置
     */
    private String position;

    public Camera() {
    }

    public Camera(String id) {
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
