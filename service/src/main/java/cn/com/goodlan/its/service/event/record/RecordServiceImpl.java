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
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private EntityManager entityManager;


    @Override
    public Page<RecordVO> search(RecordDTO recordDTO, Pageable pageable) {

        String qlString = "select new Record(r.licensePlateNumber,count(r.licensePlateNumber),r.bmmc) \n" +
                "from Record r \n" +
                "where 1=1 \n" +
//                "and r.licensePlateNumber = :licensePlateNumber \n" +
                "group by r.licensePlateNumber,r.bmmc";

        TypedQuery<Record> typedQuery = entityManager.createQuery(qlString, Record.class);

//        if (StringUtils.isNotEmpty(recordDTO.getLicensePlateNumber())) {
//            typedQuery.setParameter("licensePlateNumber", recordDTO.getLicensePlateNumber());
//        }


        String countString = "select count(distinct r.licensePlateNumber) \n" +
                "from Record r \n" +
                "where 1=1";

        List<Record> resultList = typedQuery
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        Long total = entityManager.createQuery(countString, Long.class).getSingleResult();

        List<RecordVO> list = RecordMapper.INSTANCE.convertList(resultList);

        return new PageImpl<>(list, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), total);
    }


}
