package cn.com.goodlan.its.service.system.violation;

import cn.com.goodlan.its.dao.system.violation.ViolationRepository;
import cn.com.goodlan.its.pojo.dto.ViolationDTO;
import cn.com.goodlan.its.pojo.entity.Vehicle;
import cn.com.goodlan.its.pojo.entity.Violation;
import cn.com.goodlan.its.pojo.vo.ViolationVO;
import cn.com.goodlan.mapstruct.ViolationMapper;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:
 *
 * @author: 王硕
 * @date: 2021/1/18-17:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ViolationServiceImpl implements ViolationService {

    @Autowired
    private ViolationRepository violationRepository;

    @Override
    public Page<ViolationVO> search(Pageable pageable) {
        Page<Violation> page = violationRepository.findAll(pageable);
        List<ViolationVO> violationList = ViolationMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(violationList, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(ViolationDTO violationDTO) {
        Violation violation = new Violation();
        violation.setNumber(violationDTO.getNumber());
        violation.setName(violationDTO.getName());
        violationRepository.save(violation);
    }

    @Override
    public void remove(String ids) {
        String[] violationIds = Convert.toStrArray(ids);
        for (String violationId : violationIds) {
//            checkUserAllowed(new User(userId));
            violationRepository.delete(new Violation(violationId));
        }
    }

    @Override
    public ViolationVO getById(String id) {
        Violation violation = violationRepository.getOne(id);
        return ViolationMapper.INSTANCE.convert(violation);
    }

    @Override
    public void update(ViolationDTO violationDTO) {
        Violation violation = violationRepository.getOne(violationDTO.getId());
        violation.setNumber(violationDTO.getNumber());
        violation.setName(violationDTO.getName());
        violationRepository.save(violation);
    }

    @Override
    public List<ViolationVO> findAll() {
        List<Violation> violationList = violationRepository.findAll();
        return ViolationMapper.INSTANCE.convertList(violationList);
    }
}
