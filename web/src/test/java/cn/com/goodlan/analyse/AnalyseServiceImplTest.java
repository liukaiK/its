package cn.com.goodlan.analyse;

import cn.com.goodlan.its.WebApplication;
import cn.com.goodlan.its.core.pojo.query.AnalyseQuery;
import cn.com.goodlan.its.core.pojo.vo.AnalyseVo;
import cn.com.goodlan.its.core.service.event.analyse.AnalyseService;
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
public class AnalyseServiceImplTest {

    @Autowired
    private AnalyseService analyseService;

    @Test
    @Transactional
    public void testSearch() {
        List<AnalyseVo> list = analyseService.search(new AnalyseQuery());



    }


}
