package cn.com.goodlan.its.service.system.violation;


import cn.com.goodlan.its.pojo.dto.ViolationDTO;
import cn.com.goodlan.its.pojo.vo.ViolationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ViolationService {

    Page<ViolationVO> search(Pageable pageable);

    void save(ViolationDTO violationDTO);

    void remove(String ids);

    ViolationVO getById(String id);

    void update(ViolationDTO violationDTO);

    List<ViolationVO> findAll();
}
