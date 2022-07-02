package cn.com.goodlan.its.web.security;

import cn.com.goodlan.its.core.dao.primary.system.user.UserRepository;
import cn.com.goodlan.its.core.pojo.domain.security.SecurityUserBean;
import cn.com.goodlan.its.core.pojo.entity.primary.user.User;
import cn.com.goodlan.its.core.pojo.entity.primary.user.Username;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.getByUsername(new Username(username));

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return SecurityUserBean.convertFromUser(user);
    }

}
