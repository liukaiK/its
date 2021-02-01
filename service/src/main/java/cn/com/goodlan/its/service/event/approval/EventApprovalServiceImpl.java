package cn.com.goodlan.its.service.event.approval;

import cn.com.goodlan.its.common.exception.BusinessException;
import cn.com.goodlan.its.dao.event.EventRepository;
import cn.com.goodlan.its.dao.system.record.RecordRepository;
import cn.com.goodlan.its.dao.system.score.ScoreRepository;
import cn.com.goodlan.its.pojo.dto.EventDTO;
import cn.com.goodlan.its.pojo.entity.Event;
import cn.com.goodlan.its.pojo.entity.Record;
import cn.com.goodlan.its.pojo.entity.Score;
import cn.com.goodlan.its.pojo.entity.User;
import cn.com.goodlan.its.pojo.vo.EventVO;
import cn.com.goodlan.mapstruct.EventMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.nio.charset.StandardCharsets;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public Page<EventVO> search(EventDTO eventDTO, Pageable pageable) {
        Specification<Event> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(eventDTO.getVehicleNumber())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), eventDTO.getVehicleNumber() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Page<Event> page = eventRepository.findAll(specification, pageable);
        List<EventVO> list = EventMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(name = "its.traffic.event", durable = "true"))
    public void create(byte[] message) {
        String result = new String(message, StandardCharsets.UTF_8);
        User user = objectMapper.convertValue(result, User.class);

        Event event = new Event();
//        event.setPlace();

        System.out.println(result);
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
        eventRepository.save(event);

        // 查询要扣除多少分
        Score score = scoreRepository.getByRegionAndViolation(event.getCamera().getRegion(), event.getViolation());

        if (score == null) {
            throw new BusinessException(event.getCamera().getRegion().getName() + " 未设置 " + event.getViolation().getName() + " 扣分规则");
        }


        Record record = new Record();
        record.setEvent(event);
        record.setCollege(event.getVehicle().getCollege());
        record.setScore(score);
        record.setRecord(score.getNumber());
        recordRepository.save(record);
    }

    @Override
    public void cancel(String id) {
        Event event = eventRepository.getOne(id);
        event.setStatus(Event.CANCEL);
        eventRepository.save(event);
    }

}
