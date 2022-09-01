package cn.com.goodlan.its.event;

import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.event.handler.EventHandler;

public interface HandlerManager {

    /**
     * @param violationName 违规类型 超速（不是超速1超速2）或者是违章停车
     */
    String handler(Event event, String violationName);

    void addHandler(EventHandler eventHandler);


}
