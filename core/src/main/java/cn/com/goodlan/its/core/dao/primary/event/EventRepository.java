package cn.com.goodlan.its.core.dao.primary.event;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends CustomizeRepository<Event, String> {

    long countByLicensePlateNumberAndViolationNameLikeAndTimeAfterAndDeleted(String licensePlateNumber, String violationName, LocalDateTime localDateTime, Event.Deleted deleted);

    Page<Event> findByDriverStudstaffnoAndDeletedOrderByTimeDesc(String studstaffno, Pageable pageable, Event.Deleted deleted);

    /**
     * 查询违章集合
     *
     * @return
     */
    Page<Event> findAllByDeletedOrderByTimeDesc(Event.Deleted deleted, Pageable pageable);

    long countByTimeGreaterThanEqualAndTimeLessThanEqualAndLicensePlateNumberAndViolationNameLikeAndDeleted(LocalDateTime startTime, LocalDateTime time, String licensePlateNumber, String violationName, Event.Deleted deleted);

    List<Event> findByTimeGreaterThanEqualAndDeleted(LocalDateTime time, Event.Deleted deleted);

    @Query(value = "select e from event e where e.time >= :fistDay and e.time <= :time and e.violationName like :violationName and e.licensePlateNumber = :licensePlateNumber and e.deleted = :deleted order by e.time")
    List<Event> findByTimeGreaterThanEqualAndTimeLessThanEqualAndViolationName(@Param("fistDay") LocalDateTime fistDay, @Param("time") LocalDateTime time, @Param("violationName") String violationName, @Param("licensePlateNumber") String licensePlateNumber, @Param("deleted") Event.Deleted deleted);
}
