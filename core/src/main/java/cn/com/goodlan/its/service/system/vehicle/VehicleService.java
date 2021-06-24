package cn.com.goodlan.its.service.system.vehicle;

import cn.com.goodlan.its.pojo.dto.VehicleDTO;
import cn.com.goodlan.its.pojo.vo.VehicleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleService {

    Page<VehicleVO> search(VehicleDTO vehicleDTO, Pageable pageable);

    void save(VehicleDTO vehicleDTO);

    void remove(String ids);

    VehicleVO getById(String id);

    void update(VehicleDTO vehicleDTO);
}
