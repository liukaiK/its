package cn.com.goodlan.event;

import cn.com.goodlan.its.WebApplication;
import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Slf4j
@WithMockUser(username = "admin", password = "123433356")
@WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl")
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class EventServiceTest {


    @Autowired
    private EventRepository eventRepository;

    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void refreshEvent() {
        LocalDateTime fistDateOfYear = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);

        List<Event> list = eventRepository.findByTimeGreaterThanEqualAndDeleted(fistDateOfYear, Event.Deleted.NORMAL);

        for (Event event : list) {
            String violationName = "";
            if (event.getViolationName().startsWith("超速")) {
                violationName = "超速";
            } else {
                violationName = "违章停车";
            }

            long count = eventRepository.countByTimeGreaterThanEqualAndTimeLessThanEqualAndLicensePlateNumberAndViolationNameLikeAndDeleted(fistDateOfYear, event.getTime(), event.getLicensePlateNumber(), violationName + '%', Event.Deleted.NORMAL);
            event.setNum(count);
            if (count == 1) {
                event.updateScore(0);
            }
            eventRepository.save(event);
        }

    }

}
