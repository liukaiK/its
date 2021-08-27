package cn.com.goodlan.its.core.pojo.entity.primary;

import cn.com.goodlan.its.core.pojo.entity.primary.user.User;
import cn.hutool.core.collection.CollectionUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色实体
 *
 * @author liukai
 */
@Entity
@Table(name = "sys_role")
public class Role extends AbstractEntity {

    private String id;

    private String name;

    /**
     * 备注
     */
    private String remark;

    private List<User> userList = new ArrayList<>();

    private List<Menu> menuList = new ArrayList<>();


    public Role() {
    }

    public Role(String id) {
        this.id = id;
    }

    @Transient
    public void updateName(String name) {
        this.name = name;
    }

    @Transient
    public void updateRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 角色下面是否有人
     */
    @Transient
    public boolean hasUser() {
        return CollectionUtil.isNotEmpty(this.userList);
    }

    @Transient
    public void addMenu(Menu menu) {
        menuList.add(menu);
    }


    @Transient
    public void removeAllMenu() {
        this.menuList = new ArrayList<>();
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


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_menu", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
