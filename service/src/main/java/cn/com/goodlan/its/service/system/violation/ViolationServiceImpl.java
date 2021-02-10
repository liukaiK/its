package cn.com.goodlan.its.service.system.violation;

import cn.com.goodlan.its.dao.system.violation.ViolationTypeRepository;
import cn.com.goodlan.its.pojo.dto.ViolationTypeDTO;
import cn.com.goodlan.its.pojo.entity.ViolationType;
import cn.com.goodlan.its.pojo.vo.ViolationTypeVO;
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
 * @author 王硕
 * @date 2021/1/18-17:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ViolationServiceImpl implements ViolationTypeService {

    @Autowired
    private ViolationTypeRepository violationTypeRepository;

    @Override
    public Page<ViolationTypeVO> search(ViolationTypeDTO violationTypeDTO, Pageable pageable) {
        Page<ViolationType> page = violationTypeRepository.findAll(pageable);
        List<ViolationTypeVO> violationList = ViolationMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(violationList, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(ViolationTypeDTO violationDTO) {
        ViolationType violation = new ViolationType();
        violation.setCode(violationDTO.getCode());
        violation.setName(violationDTO.getName());
        violation.setRemark(violationDTO.getRemark());
        violationTypeRepository.save(violation);
    }

    @Override
    public void remove(String ids) {
        String[] violationIds = Convert.toStrArray(ids);
        for (String violationId : violationIds) {
//            checkUserAllowed(new User(userId));
            violationTypeRepository.delete(new ViolationType(violationId));
        }
    }

    @Override
    public ViolationTypeVO getById(String id) {
        ViolationType violation = violationTypeRepository.getOne(id);
        return ViolationMapper.INSTANCE.convert(violation);
    }

    @Override
    public void update(ViolationTypeDTO violationDTO) {
        ViolationType violation = violationTypeRepository.getOne(violationDTO.getId());
        violation.setCode(violationDTO.getCode());
        violation.setName(violationDTO.getName());
        violation.setRemark(violationDTO.getRemark());
        violationTypeRepository.save(violation);
    }

    @Override
    public List<ViolationTypeVO> findAll() {
        List<ViolationType> violationList = violationTypeRepository.findAll();
        return ViolationMapper.INSTANCE.convertList(violationList);
    }
}
