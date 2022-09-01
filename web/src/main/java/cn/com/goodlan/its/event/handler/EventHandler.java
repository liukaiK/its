package cn.com.goodlan.its.event.handler;

import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;

public interface EventHandler {

    /**
     * @return 处理结果
     */
    String handler(Event event);

    boolean support(String violationName);

}
