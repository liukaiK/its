package cn.com.goodlan.its.service.system.region;

import cn.com.goodlan.its.pojo.vo.RegionVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegionService {

    Page<RegionVO> search(Pageable pageable);

}
