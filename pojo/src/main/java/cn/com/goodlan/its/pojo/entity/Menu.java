package cn.com.goodlan.its.pojo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 菜单实体
 *
 * @author liukai
 */
@Entity
@Table(name = "sys_menu")
public class Menu extends BaseEntity {

    private String id;

    private String name;

    private String authority;

    private String url;

    private Menu parent;

    private List<Menu> children;


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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }


//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        //提高代码健壮性,不是同一个类型就直接返回false,省得向下转型了
//        if (this.getClass() != obj.getClass()) {
//            return false;
//        }
//        Menu menu = (Menu) obj;
//        return this.id.equals(menu.getId());
//    }
//
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }


}
