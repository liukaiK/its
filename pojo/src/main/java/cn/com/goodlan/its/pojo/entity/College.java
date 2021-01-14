package cn.com.goodlan.its.pojo.entity;

import cn.hutool.core.collection.CollectionUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 学院实体
 *
 * @author liukai
 */
@Entity
@Table(name = "sys_college")
public class College extends BaseEntity {

    private String id;

    private String name;

    private College parent;

    private Integer sort;

    private List<College> children;

    private List<User> userList;

    public College() {
    }

    public College(String id) {
        this.id = id;
    }

    /**
     * 是否存在子节点
     */
    @Transient
    public boolean hasChildren() {
        return CollectionUtil.isNotEmpty(this.children);
    }

    /**
     * 是否有父节点
     */
    @Transient
    public boolean hasParent() {
        return this.parent != null;
    }

    /**
     * 添加父节点
     */
    @Transient
    public void addParent(String parentId) {
        this.parent = new College(parentId);
    }


    @Transient
    public boolean hasUser() {
        return CollectionUtil.isNotEmpty(this.userList);
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public College getParent() {
        return parent;
    }

    public void setParent(College parent) {
        this.parent = parent;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    public List<College> getChildren() {
        return children;
    }

    public void setChildren(List<College> children) {
        this.children = children;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id")
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
