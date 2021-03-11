package cn.com.goodlan.its.pojo;

import cn.com.goodlan.its.pojo.entity.Menu;
import cn.com.goodlan.its.pojo.entity.Role;
import cn.com.goodlan.its.pojo.entity.User;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 存放在SpringSecurity中的实体用户对象
 *
 * @author liukai
 */
@Data
@ToString
public class SecurityUserBean implements UserDetails {

    private String id;

    private String name;

    private String username;

    private String password;

    private SecurityDeptBean dept;

    private List<SecurityRoleBean> roleList;

    private List<SecurityAuthorityBean> authorities;

    private Integer sts;

    public SecurityUserBean() {

    }

    public SecurityUserBean(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roleList = obtainRoles(user.getRoleList());
        this.authorities = obtainAuthorities(user.getRoleList());
    }

    private List<SecurityRoleBean> obtainRoles(List<Role> roleList) {
        List<SecurityRoleBean> roles = new ArrayList<>();
        for (Role role : roleList) {
            roles.add(new SecurityRoleBean(role));
        }
        return roles;
    }

    private List<SecurityAuthorityBean> obtainAuthorities(List<Role> roleList) {
        List<SecurityAuthorityBean> grantedAuthorities = new ArrayList<>();
        for (Role role : roleList) {
            List<Menu> menuList = role.getMenuList();
            for (Menu menu : menuList) {
                grantedAuthorities.add(new SecurityAuthorityBean(menu));
            }
        }
        return grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public User castToUser() {
        return new User(this.id);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
