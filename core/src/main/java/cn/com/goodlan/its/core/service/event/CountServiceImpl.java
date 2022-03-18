package cn.com.goodlan.its.core.service.event;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

@Service
public class CountServiceImpl implements CountService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Long queryCount(String licensePlateNumber, String violationName) {
        // 获取今年第一天
        LocalDateTime fistDateOfDay = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);
        return eventRepository.countByLicensePlateNumberAndViolationNameLikeAndTimeAfterAndDeleted(licensePlateNumber, violationName + "%", fistDateOfDay, Event.Deleted.NORMAL) + 1;
    }


}
