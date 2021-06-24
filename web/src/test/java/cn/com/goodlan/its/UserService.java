package cn.com.goodlan.its;

import cn.com.goodlan.its.core.dao.primary.system.user.UserRepository;
import cn.com.goodlan.its.core.pojo.TrafficEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@WithMockUser(username = "admin", password = "123433356")
@WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl")
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;


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

    @Test
    public void test() {
        String str = "{\"id\":\"4028d985776d8524017781d900923323\",\"m_EventName\":\"交通卡口\",\"m_PlateNumber\":\"黑AX717N\",\"m_PlateType\":\"Normal\",\"m_PlateColor\":\"Blue\",\"m_VehicleColor\":\"White\",\"m_VehicleType\":\"Unknown\",\"m_VehicleSize\":\"小型车\",\"m_FileCount\":\"1\",\"m_FileIndex\":\"1\",\"m_GroupID\":\"299982174\",\"m_IllegalPlace\":\"航天路拍2H栋方向\",\"m_LaneNumber\":\"1\",\"m_bPicEnble\":1,\"m_OffSet\":992595,\"m_FileLength\":29250,\"m_Utc\":\"2021-02-19T16:02:00.000+0000\",\"eventType\":23,\"bigImage\":null,\"plateImage\":null,\"nupperSpeedLimit\":30,\"nspeed\":30}";

        try {
            TrafficEvent event = objectMapper.readValue(str, TrafficEvent.class);

            log.error(event.toString());


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }


}
