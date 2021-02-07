package cn.com.goodlan.its.service.system.park.parklot;

import cn.com.goodlan.its.pojo.dto.ParkLotDTO;
import cn.com.goodlan.its.pojo.vo.ParkLotVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParkLotService {
    Page<ParkLotVO> search(ParkLotDTO parkLotDTO, Pageable pageable);

    void save(ParkLotDTO parkLotDTO);

    ParkLotVO getById(String id);

    void update(ParkLotDTO parkLotDTO);

    void remove(String ids);
}