package cn.com.goodlan.its.event;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.dao.primary.system.record.RecordRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.Record;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public void approval(String id) {
        Event event = eventRepository.getOne(id);
        event.approval();
        recordRepository.save(new Record(event));
    }

    @Override
    public void cancel(String id) {
        Event event = eventRepository.getOne(id);
        event.cancel();
    }
}
