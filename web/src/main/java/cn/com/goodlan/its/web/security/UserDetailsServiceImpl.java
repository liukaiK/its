package cn.com.goodlan.its.web.security;

import cn.com.goodlan.its.dao.user.UserRepository;
import cn.com.goodlan.its.pojo.SecurityUserBean;
import cn.com.goodlan.its.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        SecurityUserBean securityUserBean = new SecurityUserBean();
        securityUserBean.setName(user.getName());
        securityUserBean.setUsername(user.getUsername());
        securityUserBean.setPassword(user.getPassword());

        return securityUserBean;
    }


}
