package cn.com.goodlan.its.service.event;

import cn.com.goodlan.its.pojo.dto.EventDTO;
import cn.com.goodlan.its.pojo.vo.EventVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventApprovalService {

    Page<EventVO> search(EventDTO eventDTO, Pageable pageable);

}
