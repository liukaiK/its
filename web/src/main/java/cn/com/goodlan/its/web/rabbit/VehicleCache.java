package cn.com.goodlan.its.web.rabbit;

public interface VehicleCache {

    void put(String licensePlateNumber);

    boolean exists(String licensePlateNumber);

}
