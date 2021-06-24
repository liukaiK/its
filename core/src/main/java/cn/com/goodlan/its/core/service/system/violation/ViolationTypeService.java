package cn.com.goodlan.its.core.service.system.violation;


import cn.com.goodlan.its.core.pojo.dto.ViolationTypeDTO;
import cn.com.goodlan.its.core.pojo.vo.ViolationTypeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ViolationTypeService {

    Page<ViolationTypeVO> search(ViolationTypeDTO violationTypeDTO, Pageable pageable);

    void save(ViolationTypeDTO violationDTO);

    void remove(String ids);

    ViolationTypeVO getById(String id);

    void update(ViolationTypeDTO violationDTO);

    List<ViolationTypeVO> findAll();
}
