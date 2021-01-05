package cn.com.goodlan.its.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户实体
 *
 * @author liukai
 */
@Entity
@Table(name = "sys_user")
public class User extends BaseEntity {

    private String id;

    private String username;

    private String name;

    private String password;






    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, columnDefinition = "char(60)")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
