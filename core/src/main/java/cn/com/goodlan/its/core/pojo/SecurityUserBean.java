package cn.com.goodlan.its.core.pojo;

import cn.com.goodlan.its.core.pojo.entity.primary.College;
import cn.com.goodlan.its.core.pojo.entity.primary.Menu;
import cn.com.goodlan.its.core.pojo.entity.primary.Role;
import cn.com.goodlan.its.core.pojo.entity.primary.user.User;
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

    private SecurityCollegeBean college;

    private List<SecurityRoleBean> roleList;

    private List<SecurityAuthorityBean> authorities;

    private Integer sts;

    public static SecurityUserBean convertFromUser(User user) {
        return new SecurityUserBean(user);
    }

    private SecurityUserBean(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername().getUsername();
        this.password = user.getPassword();
        this.college = obtainCollege(user.getCollege());
        this.roleList = obtainRoles(user.getRoleList());
        this.authorities = obtainAuthorities(user.getRoleList());
    }

    private SecurityCollegeBean obtainCollege(College college) {
        return SecurityCollegeBean.convertFromCollege(college);
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
                grantedAuthorities.add(SecurityAuthorityBean.convertFormMenu(menu));
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
