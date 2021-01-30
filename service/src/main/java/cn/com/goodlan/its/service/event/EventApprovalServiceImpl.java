package cn.com.goodlan.its.service.event;

import cn.com.goodlan.its.common.exception.BusinessException;
import cn.com.goodlan.its.dao.event.EventRepository;
import cn.com.goodlan.its.dao.system.record.RecordRepository;
import cn.com.goodlan.its.dao.system.score.ScoreRepository;
import cn.com.goodlan.its.dao.system.violation.ViolationRepository;
import cn.com.goodlan.its.pojo.dto.EventDTO;
import cn.com.goodlan.its.pojo.entity.Event;
import cn.com.goodlan.its.pojo.entity.Record;
import cn.com.goodlan.its.pojo.entity.Score;
import cn.com.goodlan.its.pojo.vo.EventVO;
import cn.com.goodlan.mapstruct.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EventApprovalServiceImpl implements EventApprovalService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ViolationRepository violationRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public Page<EventVO> search(EventDTO eventDTO, Pageable pageable) {
        Page<Event> page = eventRepository.findAll(pageable);
        List<EventVO> list = EventMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public EventVO getById(String id) {
        Event event = eventRepository.getOne(id);
        return EventMapper.INSTANCE.convert(event);
    }

    @Override
    public void approval(String id) {
        Event event = eventRepository.getOne(id);
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
