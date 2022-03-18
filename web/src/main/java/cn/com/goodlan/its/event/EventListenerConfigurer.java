package cn.com.goodlan.its.event;

import cn.com.goodlan.its.event.handler.EventHandler;
import cn.com.goodlan.its.web.rabbit.RabbitObtainEventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liukai
 */
@Configuration
public class EventListenerConfigurer {

    @Autowired
    private EventHandler speedEventHandlerImpl;

    @Autowired
    private EventHandler stopEventHandlerImpl;

    @Bean
    public RabbitObtainEventImpl rabbitObtainEvent() {
        RabbitObtainEventImpl rabbitObtainEvent = new RabbitObtainEventImpl();
        rabbitObtainEvent.addHandler(stopEventHandlerImpl);
        rabbitObtainEvent.addHandler(speedEventHandlerImpl);
        return rabbitObtainEvent;
    }


}
