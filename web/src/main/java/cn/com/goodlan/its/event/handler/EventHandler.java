package cn.com.goodlan.its.event.handler;

import cn.com.goodlan.its.core.pojo.TrafficEvent;

public interface EventHandler {

    void handler(TrafficEvent trafficEvent);

    boolean support(String violationName);

}
