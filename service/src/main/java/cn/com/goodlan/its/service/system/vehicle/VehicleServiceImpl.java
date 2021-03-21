package cn.com.goodlan.its.service.system.vehicle;

import cn.com.goodlan.its.dao.system.vehicle.VehicleRepository;
import cn.com.goodlan.its.pojo.dto.VehicleDTO;
import cn.com.goodlan.its.pojo.entity.Vehicle;
import cn.com.goodlan.its.pojo.vo.VehicleVO;
import cn.com.goodlan.mapstruct.VehicleMapper;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Page<VehicleVO> search(Pageable pageable) {
        Page<Vehicle> page = vehicleRepository.findAll(pageable);
        List<VehicleVO> vehicleList = VehicleMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(vehicleList, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(VehicleDTO vehicleDTO) {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setLicensePlateNumber(vehicleDTO.getNumber());
//        vehicle.setType(vehicleDTO.getType());
//        vehicle.addCollege(vehicleDTO.getCollegeId());
//        vehicleRepository.save(vehicle);
    }

    @Override
    public void remove(String ids) {
        String[] vehicleIds = Convert.toStrArray(ids);
        for (String vehicleId : vehicleIds) {
//            checkUserAllowed(new User(userId));
            vehicleRepository.delete(new Vehicle(vehicleId));
        }
    }

    @Override
    public VehicleVO getById(String id) {
        Vehicle vehicle = vehicleRepository.getOne(id);
        return VehicleMapper.INSTANCE.convert(vehicle);
    }

    @Override
    public void update(VehicleDTO vehicleDTO) {
//        Vehicle vehicle = vehicleRepository.getOne(vehicleDTO.getId());
//        vehicle.setLicensePlateNumber(vehicleDTO.getNumber());
//        vehicle.setType(vehicleDTO.getType());
//        vehicle.addCollege(vehicleDTO.getCollegeId());
//        vehicleRepository.save(vehicle);
    }

}
