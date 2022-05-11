package cn.com.goodlan.its.web.rabbit;

import cn.com.goodlan.its.core.dao.primary.whitelist.WhitelistRepository;
import cn.com.goodlan.its.core.pojo.TrafficEvent;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Whitelist;
import cn.com.goodlan.its.event.handler.EventHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RabbitObtainEventImpl {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WhitelistRepository whitelistRepository;

    private final List<EventHandler> eventHandlers = new ArrayList<>();

    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    @RabbitListener(queuesToDeclare = @Queue(name = "its.traffic.event", durable = "true"))
    public synchronized void obtainEvent(String message) {
        String content = StringEscapeUtils.unescapeJava(message);
        if (StringUtils.startsWithIgnoreCase(content, "\"")) {
            content = content.substring(1, content.length() - 1);
        }
        TrafficEvent trafficEvent;
        try {
            trafficEvent = objectMapper.readValue(content, TrafficEvent.class);
            log.info("trafficEvent: {}", trafficEvent.toString());
        } catch (JsonProcessingException e) {
            log.error("解析mq消息失败:", e);
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (StringUtils.isEmpty(trafficEvent.getM_PlateNumber())) {
            return;
        }

        if (inWhiteList(trafficEvent)) {
            log.info("白名单中存在此车辆 不记录:{}", trafficEvent.getM_PlateNumber());
            return;
        }

        for (EventHandler handler : getHandlers()) {
            if (!handler.support(trafficEvent.getM_EventName())) {
                continue;
            }
            handler.handler(trafficEvent);
            return;
        }

        log.info("没有可处理 {} 事件的handler", trafficEvent.getM_EventName());

    }

    /**
     * 在不在白名单里面
     */
    private boolean inWhiteList(TrafficEvent trafficEvent) {
        Whitelist whitelist = whitelistRepository.findByLicensePlateNumber(trafficEvent.getM_PlateNumber().trim());
        return whitelist != null;
    }

    public void addHandler(EventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    private List<EventHandler> getHandlers() {
        return this.eventHandlers;
    }

}

