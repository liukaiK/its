package cn.com.goodlan.its.core.pojo.dto;

import cn.com.goodlan.its.core.pojo.entity.primary.Event;
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

    private String violationTypeId;

    private Event.Status status;

}
