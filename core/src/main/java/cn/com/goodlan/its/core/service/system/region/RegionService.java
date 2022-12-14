package cn.com.goodlan.its.core.service.system.region;

import cn.com.goodlan.its.core.pojo.dto.RegionDTO;
import cn.com.goodlan.its.core.pojo.vo.RegionVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RegionService {

    Page<RegionVO> search(RegionDTO regionDTO, Pageable pageable);

    void save(RegionDTO regionDTO);

    void remove(String ids);

    RegionVO getById(String id);

    List<RegionVO> findAll();


    void update(RegionDTO regionDTO);
}
