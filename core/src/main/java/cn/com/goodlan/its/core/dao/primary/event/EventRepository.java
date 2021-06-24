package cn.com.goodlan.its.core.dao.primary.event;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.Event;

import java.util.List;

public interface EventRepository extends CustomizeRepository<Event, String> {

    List<Event> findTop30ByOrderByTime();

}
