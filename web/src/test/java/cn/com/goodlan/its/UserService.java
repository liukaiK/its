package cn.com.goodlan.its;

import cn.com.goodlan.its.common.util.AESUtil;
import cn.com.goodlan.its.dao.system.user.UserRepository;
import cn.com.goodlan.its.pojo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@WithMockUser(username = "admin", password = "123433356")
@WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl")
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class UserService {

    @Autowired
    private UserRepository userRepository;


//    @Test
//    @Transactional
//    public void updatePhoneMobile() {
//        List<User> userList = userRepository.findByUsernameNot("admin");
//        for (User user : userList) {
//            String phoneNumber = user.getPhoneNumber();
//            user.setPhoneNumber(AESUtil.encrypt(phoneNumber));
//            userRepository.save(user);
//        }
//
//    }


}
