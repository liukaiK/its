package cn.com.goodlan.its.dao.system.vehicle;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends CustomizeRepository<Vehicle, String> {

    Optional<Vehicle> getByLicensePlateNumber(String number);

}
