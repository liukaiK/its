package cn.com.goodlan.its.event.handler;

import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;

public interface EventHandler {

    void handler(Event event);

    boolean support(String violationName);

}
