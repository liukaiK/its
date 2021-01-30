package cn.com.goodlan.its.service.event.record;

import cn.com.goodlan.its.dao.system.record.RecordRepository;
import cn.com.goodlan.its.pojo.dto.RecordDTO;
import cn.com.goodlan.its.pojo.entity.Record;
import cn.com.goodlan.its.pojo.vo.RecordVO;
import cn.com.goodlan.mapstruct.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;


    @Override
    public Page<RecordVO> search(RecordDTO recordDTO, Pageable pageable) {
        Page<Record> page = recordRepository.findAll(pageable);
        List<RecordVO> list = RecordMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }


}
