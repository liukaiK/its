package cn.com.goodlan.its.core.pojo.dto;

import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getViolationTypeId() {
        return violationTypeId;
    }

    public void setViolationTypeId(String violationTypeId) {
        this.violationTypeId = violationTypeId;
    }

    public Event.Status getStatus() {
        return status;
    }

    public void setStatus(Event.Status status) {
        this.status = status;
    }
}
