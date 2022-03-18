package cn.com.goodlan.its.core.dao.primary.event;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends CustomizeRepository<Event, String> {

    List<Event> findTop30ByDeletedOrderByTime(Event.Deleted deleted);

    long countByLicensePlateNumberAndViolationNameLikeAndTimeAfterAndDeleted(String licensePlateNumber, String violationName, LocalDateTime localDateTime, Event.Deleted deleted);

    /**
     * 根据车辆车牌集合查询违章事件
     *
     * @param plateNumbers 车牌号集合
     * @param pageable
     * @return List<Event>
     */
    Page<Event> findByLicensePlateNumberInAndDeletedOrderByTimeDesc(List<String> plateNumbers, Pageable pageable, Event.Deleted deleted);

    /**
     * 查询违章集合
     *
     * @return
     */
    Page<Event> findAllByDeletedOrderByTimeDesc(Event.Deleted deleted, Pageable pageable);

    long countByTimeGreaterThanEqualAndTimeLessThanEqualAndLicensePlateNumberAndViolationNameLikeAndDeleted(LocalDateTime startTime, LocalDateTime time, String licensePlateNumber, String violationName, Event.Deleted deleted);

    List<Event> findByTimeGreaterThanEqualAndDeleted(LocalDateTime time, Event.Deleted deleted);
}
