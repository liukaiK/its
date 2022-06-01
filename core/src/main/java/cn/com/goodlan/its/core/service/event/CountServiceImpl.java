package cn.com.goodlan.its.core.service.event;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CountServiceImpl implements CountService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Long queryCountThisYear(String licensePlateNumber, String violationName) {
        // 获取今年第一天
        LocalDateTime fistDateOfDay = DateUtils.getFirstDayOfYear(LocalDateTime.now());
        return eventRepository.countByLicensePlateNumberAndViolationNameLikeAndTimeAfterAndDeleted(licensePlateNumber, violationName + "%", fistDateOfDay, Event.Deleted.NORMAL) + 1;
    }


}
