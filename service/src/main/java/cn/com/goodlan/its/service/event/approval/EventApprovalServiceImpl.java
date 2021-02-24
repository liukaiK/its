package cn.com.goodlan.its.service.event.approval;

import cn.com.goodlan.its.common.exception.BusinessException;
import cn.com.goodlan.its.dao.event.EventRepository;
import cn.com.goodlan.its.dao.system.record.RecordRepository;
import cn.com.goodlan.its.pojo.dto.EventDTO;
import cn.com.goodlan.its.pojo.entity.Event;
import cn.com.goodlan.its.pojo.entity.Record;
import cn.com.goodlan.its.pojo.entity.Score;
import cn.com.goodlan.its.pojo.vo.EventVO;
import cn.com.goodlan.mapstruct.EventMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class EventApprovalServiceImpl implements EventApprovalService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Page<EventVO> search(EventDTO eventDTO, Pageable pageable) {
        Specification<Event> specification = querySpecification(eventDTO);
        Page<Event> page = eventRepository.findAll(specification, pageable);
        List<EventVO> list = EventMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public List<EventVO> export(EventDTO eventDTO) {
        Specification<Event> specification = querySpecification(eventDTO);
        List<Event> list = eventRepository.findAll(specification);
        return EventMapper.INSTANCE.convertList(list);
    }

    private Specification<Event> querySpecification(EventDTO eventDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(eventDTO.getVehicleNumber())) {
                list.add(criteriaBuilder.like(root.get("licensePlateNumber").as(String.class), eventDTO.getVehicleNumber() + "%"));
            }
            if (StringUtils.isNotEmpty(eventDTO.getStartTime())) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("time").as(String.class), eventDTO.getStartTime()));
            }
            if (StringUtils.isNotEmpty(eventDTO.getEndTime())) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("time").as(String.class), eventDTO.getEndTime()));
            }
            if (StringUtils.isNotEmpty(eventDTO.getStatus())) {
                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), Integer.valueOf(eventDTO.getStatus())));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
    }

    @Override
    public EventVO getById(String id) {
        Event event = eventRepository.getOne(id);
        return EventMapper.INSTANCE.convert(event);
    }

    @Override
    public void approval(String id) {
        Event event = eventRepository.getOne(id);

        if (event.getStatus().equals(Event.APPROVAL)) {
            throw new BusinessException("该违规事件已经审批 请刷新页面");
        }

        event.setStatus(Event.APPROVAL);
        event.setApprovalTime(LocalDateTime.now());
        eventRepository.save(event);

        // 获取要扣除多少分
        Score score = event.getScore();

        Record record = new Record();
        record.setLicensePlateNumber(event.getLicensePlateNumber());
        record.setRegionName(event.getRegion().getName());
        record.setTime(event.getTime());
        record.setRecord(score.getNumber());
        recordRepository.save(record);
    }

    @Override
    public void cancel(String id) {
        Event event = eventRepository.getOne(id);
        event.setStatus(Event.CANCEL);
        event.setApprovalTime(LocalDateTime.now());
        eventRepository.save(event);
    }


}
