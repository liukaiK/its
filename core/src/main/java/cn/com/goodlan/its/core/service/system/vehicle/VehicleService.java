package cn.com.goodlan.its.core.service.system.vehicle;

import cn.com.goodlan.its.core.pojo.dto.VehicleDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.com.goodlan.its.core.pojo.vo.VehicleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {

    Page<VehicleVO> search(VehicleDTO vehicleDTO, Pageable pageable);

    void save(VehicleDTO vehicleDTO);

    void remove(String ids);

    VehicleVO getById(String id);

    void update(VehicleDTO vehicleDTO);

    /**
     * 根据工号查询车辆集合
     *
     * @param studstaffno
     * @return
     */
    List<Vehicle> findByStudstaffno(String studstaffno);
}
