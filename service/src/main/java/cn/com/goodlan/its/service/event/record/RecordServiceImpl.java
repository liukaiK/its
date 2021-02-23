package cn.com.goodlan.its.service.event.record;

import cn.com.goodlan.its.dao.system.record.RecordRepository;
import cn.com.goodlan.its.pojo.dto.RecordDTO;
import cn.com.goodlan.its.pojo.entity.Record;
import cn.com.goodlan.its.pojo.vo.RecordVO;
import cn.com.goodlan.mapstruct.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private EntityManager entityManager;


    @Override
    public Page<RecordVO> search(RecordDTO recordDTO, Pageable pageable) {

        String qlString = "select new Record(r.licensePlateNumber,count(r.licensePlateNumber)) from Record r where 1=1 group by r.licensePlateNumber";

        String countString = "select count(distinct r.licensePlateNumber) from Record r where 1=1";

        List<Record> resultList = entityManager.createQuery(qlString, Record.class)
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        Long total = entityManager.createQuery(countString, Long.class).getSingleResult();

        List<RecordVO> list = RecordMapper.INSTANCE.convertList(resultList);

        return new PageImpl<>(list, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), total);
    }


}
