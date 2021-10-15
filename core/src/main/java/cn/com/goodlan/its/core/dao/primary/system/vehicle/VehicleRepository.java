package cn.com.goodlan.its.core.dao.primary.system.vehicle;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends CustomizeRepository<Vehicle, String> {

    Optional<Vehicle> getByLicensePlateNumber(String number);

    Optional<List<Vehicle>> getByStudstaffno(String studstaffno);

    Vehicle getByDriverPhone(String driverPhone);

}
