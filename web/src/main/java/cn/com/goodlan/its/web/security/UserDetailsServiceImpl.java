package cn.com.goodlan.its.web.security;

import cn.com.goodlan.its.dao.system.user.UserRepository;
import cn.com.goodlan.its.pojo.SecurityUserBean;
import cn.com.goodlan.its.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

        Optional<User> userOptional = userRepository.getByUsername(username);

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return new SecurityUserBean(user);
    }

}
