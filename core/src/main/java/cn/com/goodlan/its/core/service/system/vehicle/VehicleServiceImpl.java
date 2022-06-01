package cn.com.goodlan.its.core.service.system.vehicle;

import cn.com.goodlan.its.core.dao.primary.system.vehicle.VehicleRepository;
import cn.com.goodlan.its.core.mapstruct.VehicleMapper;
import cn.com.goodlan.its.core.pojo.dto.VehicleDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.com.goodlan.its.core.pojo.vo.VehicleVO;
import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Page<VehicleVO> search(VehicleDTO vehicleDTO, Pageable pageable) {
        Specification<Vehicle> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(vehicleDTO.getId())) {
                list.add(criteriaBuilder.equal(root.get("id").as(String.class), vehicleDTO.getId()));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Page<Vehicle> page = vehicleRepository.findAll(specification, pageable);
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
        Vehicle vehicle = vehicleRepository.getById(id);
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

    /**
     * 根据工号查询车辆集合
     *
     * @param studstaffno 工号
     * @return List<Vehicle>
     */
    @Override
    public List<Vehicle> findByStudstaffno(String studstaffno) {
        Optional<List<Vehicle>> byStudstaffno = vehicleRepository.getByStudstaffno(studstaffno);
        return byStudstaffno.orElse(new ArrayList<>());
    }
}
