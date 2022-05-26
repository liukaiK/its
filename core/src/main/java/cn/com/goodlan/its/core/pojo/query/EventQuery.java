package cn.com.goodlan.its.core.pojo.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventQuery {

    private String id;

    private String name;

    private String vehicleNumber;

    private String driverName;

    private String collegeName;

    private String startTime;

    private String endTime;

    private String violationTypeId;

}
