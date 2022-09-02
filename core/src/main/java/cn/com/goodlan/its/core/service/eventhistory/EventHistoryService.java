package cn.com.goodlan.its.core.service.eventhistory;

import cn.com.goodlan.its.core.pojo.query.EventHistoryQuery;
import cn.com.goodlan.its.core.pojo.vo.EventHistoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventHistoryService {

    Page<EventHistoryVO> search(EventHistoryQuery eventHistoryQuery, Pageable pageable);

    EventHistoryVO getById(String id);

}
