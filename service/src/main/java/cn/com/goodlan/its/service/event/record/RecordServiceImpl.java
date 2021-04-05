package cn.com.goodlan.its.service.event.record;

import cn.com.goodlan.its.common.util.StringUtils;
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

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Page<RecordVO> search(RecordDTO recordDTO, Pageable pageable) {
        Page<Record> findAll;
        if (StringUtils.isNotEmpty(recordDTO.getLicensePlateNumber())) {
            findAll = recordRepository.searchByLicensePlateNumber(recordDTO.getLicensePlateNumber(), pageable);
        } else {
            findAll = recordRepository.search(pageable);
        }
        List<RecordVO> list = RecordMapper.INSTANCE.convertList(findAll.getContent());
        return new PageImpl<>(list, findAll.getPageable(), findAll.getTotalElements());
    }

    @Override
    public List<RecordVO> search() {
        List<Record> list = recordRepository.findByOrderByTime();
        return RecordMapper.INSTANCE.convertList(list);
    }


}
