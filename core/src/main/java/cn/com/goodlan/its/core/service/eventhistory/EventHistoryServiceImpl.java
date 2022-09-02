package cn.com.goodlan.its.core.service.eventhistory;

import cn.com.goodlan.its.core.dao.primary.event.EventHistoryRepository;
import cn.com.goodlan.its.core.mapstruct.EventHistoryMapper;
import cn.com.goodlan.its.core.pojo.entity.primary.event.EventHistory;
import cn.com.goodlan.its.core.pojo.query.EventHistoryQuery;
import cn.com.goodlan.its.core.pojo.vo.EventHistoryVO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EventHistoryServiceImpl implements EventHistoryService {

    private final EventHistoryRepository eventHistoryRepository;

    @Override
    public Page<EventHistoryVO> search(EventHistoryQuery eventHistoryQuery, Pageable pageable) {
        Page<EventHistory> page = eventHistoryRepository.findAll(querySpecification(eventHistoryQuery), pageable);
        List<EventHistoryVO> list = EventHistoryMapper.INSTANCE.convert(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EventHistoryVO getById(String id) {
        EventHistory eventHistory = eventHistoryRepository.getById(id);
        return EventHistoryMapper.INSTANCE.convert(eventHistory);
    }

    private Specification<EventHistory> querySpecification(EventHistoryQuery eventHistoryQuery) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(eventHistoryQuery.getLicensePlateNumber())) {
                list.add(criteriaBuilder.like(root.get("licensePlateNumber").as(String.class), eventHistoryQuery.getLicensePlateNumber() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
    }

}
