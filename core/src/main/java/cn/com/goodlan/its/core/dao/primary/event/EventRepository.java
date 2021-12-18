package cn.com.goodlan.its.core.dao.primary.event;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventRepository extends CustomizeRepository<Event, String> {

    List<Event> findTop30ByOrderByTime();

    /**
     * 根据车辆车牌集合查询违章事件
     *
     * @param plateNumbers 车牌号集合
     * @param pageable
     * @return List<Event>
     */
    Page<Event> findByLicensePlateNumberInOrderByTimeDesc(List<String> plateNumbers, Pageable pageable);

}
