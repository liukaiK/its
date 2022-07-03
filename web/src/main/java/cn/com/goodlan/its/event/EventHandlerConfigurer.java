package cn.com.goodlan.its.event;

import cn.com.goodlan.its.event.handler.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liukai
 */
@Configuration
public class EventHandlerConfigurer {

    @Autowired
    private EventHandler speedEventHandlerImpl;

    @Autowired
    private EventHandler stopEventHandlerImpl;

    @Bean
    public HandlerManager handlerManager() {
        HandlerManager handlerManager = new HandlerManagerImpl();
        handlerManager.addHandler(stopEventHandlerImpl);
        handlerManager.addHandler(speedEventHandlerImpl);
        return handlerManager;
    }


}
