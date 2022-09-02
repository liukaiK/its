package cn.com.goodlan.its.event;

import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.event.handler.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class HandlerManagerImpl implements HandlerManager {

    private final List<EventHandler> eventHandlers = new ArrayList<>();

    @Override
    public String handler(Event event, String violationName) {
        for (EventHandler handler : getHandlers()) {
            if (!handler.support(violationName)) {
                continue;
            }
            return handler.handler(event);
        }
        return "不能识别的类型";
    }

    public void addHandler(EventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    private List<EventHandler> getHandlers() {
        return this.eventHandlers;
    }

}
