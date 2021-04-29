package cn.com.goodlan.its.pojo.dto;

import cn.com.goodlan.its.pojo.entity.primary.Event;
import lombok.Data;

@Data
public class EventDTO {

    private String id;

    private String name;

    private String vehicleNumber;

    private String driverName;

    private String collegeName;

    private String startTime;

    private String endTime;

    private Event.Status status;

}
