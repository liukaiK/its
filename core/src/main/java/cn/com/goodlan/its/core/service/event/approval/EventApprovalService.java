package cn.com.goodlan.its.core.service.event.approval;

import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventApprovalService {

    List<EventVO> searchAll();

    Page<EventVO> search(EventDTO eventDTO, Pageable pageable);

    EventVO getById(String id);

    void approval(String id);

    void cancel(String id);

    List<EventVO> export(EventDTO eventDTO);

}
