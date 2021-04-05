package cn.com.goodlan.its.dao.event;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.Event;

import java.util.List;

public interface EventRepository extends CustomizeRepository<Event, String> {

    List<Event> findTop30ByOrderByTime();

}
