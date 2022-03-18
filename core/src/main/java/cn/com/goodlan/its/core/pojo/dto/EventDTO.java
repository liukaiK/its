package cn.com.goodlan.its.core.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {

    private String id;

    private String name;

    private String vehicleNumber;

    private String driverName;

    private String collegeName;

    private String startTime;

    private String endTime;

    private String violationTypeId;

}
