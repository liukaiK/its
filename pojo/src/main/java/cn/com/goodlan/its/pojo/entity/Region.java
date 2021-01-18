package cn.com.goodlan.its.pojo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 区域实体
 *
 * @author liukai
 */
@Entity
@Table(name = "sys_region")
public class Region extends BaseEntity {


    private String id;

    private String name;

    private List<Camera> cameraList;

    public Region() {
    }

    public Region(String id) {
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id")
    public List<Camera> getCameraList() {
        return cameraList;
    }

    public void setCameraList(List<Camera> cameraList) {
        this.cameraList = cameraList;
    }
}
