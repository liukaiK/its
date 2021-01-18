package cn.com.goodlan.its.service.system.region;

import cn.com.goodlan.its.pojo.dto.RegionDTO;
import cn.com.goodlan.its.pojo.vo.RegionVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegionService {

    Page<RegionVO> search(Pageable pageable);

    void save(RegionDTO regionDTO);

    void remove(String ids);

    RegionVO getById(String id);


    void update(RegionDTO regionDTO);
}
