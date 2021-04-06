package cn.com.goodlan.its.dao.primary.event;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.primary.Event;

import java.util.List;

public interface EventRepository extends CustomizeRepository<Event, String> {

    List<Event> findTop30ByOrderByTime();

}
