package cn.com.goodlan.its.pojo.entity.primary;

import cn.hutool.core.collection.CollectionUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 区域实体
 *
 * @author liukai
 */
@Entity
@Table(name = "sys_region")
public class Region extends AbstractEntity {


    private String id;

    private String name;

    private List<Camera> cameraList = new ArrayList<>();

    private List<Score> scoreList = new ArrayList<>();


    public Region() {
    }

    public Region(String id) {
        this.id = id;
    }

    @Transient
    public boolean hasCamera() {
        return CollectionUtil.isNotEmpty(this.cameraList);
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
    @JoinColumn(name = "region_id")
    public List<Camera> getCameraList() {
        return cameraList;
    }

    public void setCameraList(List<Camera> cameraList) {
        this.cameraList = cameraList;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }
}
