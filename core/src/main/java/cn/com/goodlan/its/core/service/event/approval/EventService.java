package cn.com.goodlan.its.core.service.event.approval;

import cn.com.goodlan.its.core.pojo.Params;
import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.query.EventQuery;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface EventService {

    Page<EventVO> search(EventQuery eventQuery, Pageable pageable);

    void create(EventDTO eventDTO);

    EventVO getById(String id);

    List<EventVO> export(EventQuery eventQuery);

    void remove(String id);

    /**
     * 根据工号查询违章集合
     *
     * @param studstaffno 工号
     * @param pageable    分页
     * @return
     */
    Map<String, Object> findByUserId(String studstaffno, Pageable pageable);

    Map<String, Object> allEvent(Params params);
}
