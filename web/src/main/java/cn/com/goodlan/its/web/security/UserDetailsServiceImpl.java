package cn.com.goodlan.its.web.security;

import cn.com.goodlan.its.dao.system.user.UserRepository;
import cn.com.goodlan.its.pojo.SecurityAuthorityBean;
import cn.com.goodlan.its.pojo.SecurityUserBean;
import cn.com.goodlan.its.pojo.entity.Menu;
import cn.com.goodlan.its.pojo.entity.Role;
import cn.com.goodlan.its.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录获取用户信息
 *
 * @author liukai
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        List<SecurityAuthorityBean> authorities = obtainAuthorities(user);

        SecurityUserBean securityUserBean = new SecurityUserBean();
        securityUserBean.setId(user.getId());
        securityUserBean.setName(user.getName());
        securityUserBean.setUsername(user.getUsername());
        securityUserBean.setPassword(user.getPassword());
        securityUserBean.setAuthorities(authorities);
        return securityUserBean;
    }

    private List<SecurityAuthorityBean> obtainAuthorities(User user) {
        List<SecurityAuthorityBean> grantedAuthorities = new ArrayList<>();
        List<Role> roleList = user.getRoleList();

        for (Role role : roleList) {
            List<Menu> menuList = role.getMenuList();
            for (Menu menu : menuList) {
                convert(grantedAuthorities, menu);
            }
        }

        return grantedAuthorities;
    }

    private void convert(List<SecurityAuthorityBean> grantedAuthorities, Menu menu) {
        SecurityAuthorityBean securityAuthorityBean = new SecurityAuthorityBean();
        securityAuthorityBean.setId(menu.getId());
        securityAuthorityBean.setAuthority(menu.getAuthority());
        grantedAuthorities.add(securityAuthorityBean);
    }

}
