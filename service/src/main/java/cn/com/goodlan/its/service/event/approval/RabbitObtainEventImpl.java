package cn.com.goodlan.its.service.event.approval;

import cn.com.goodlan.its.dao.event.EventRepository;
import cn.com.goodlan.its.dao.system.vehicle.VehicleRepository;
import cn.com.goodlan.its.pojo.TrafficEvent;
import cn.com.goodlan.its.pojo.entity.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class RabbitObtainEventImpl {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(name = "its.traffic.event", durable = "true"))
    public void obtainEvent(String message) throws JsonProcessingException {
        TrafficEvent trafficEvent = objectMapper.readValue(message, TrafficEvent.class);
        log.debug(trafficEvent.toString());
        Event event = new Event();
        event.setVehicle(vehicleRepository.getByLicensePlateNumber(trafficEvent.getM_PlateNumber()));
        event.setPlace(trafficEvent.getM_IllegalPlace());
        event.setVehicleNumber(trafficEvent.getM_PlateNumber());
        event.setViolationTime(LocalDateTime.now());
        eventRepository.save(event);
    }

}
