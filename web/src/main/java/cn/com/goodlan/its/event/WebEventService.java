package cn.com.goodlan.its.event;

import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.query.EventQuery;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WebEventService {

    Page<EventVO> search(EventQuery eventQuery, Pageable pageable);

    void create(EventDTO eventDTO);

    EventVO getById(String id);

    List<EventVO> export(EventQuery eventQuery);

    void remove(String id);

}
