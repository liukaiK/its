package cn.com.goodlan.record;

import cn.com.goodlan.its.WebApplication;
import cn.com.goodlan.its.dao.primary.system.record.RecordRepository;
import cn.com.goodlan.its.pojo.entity.primary.Record;
import cn.com.goodlan.its.service.event.record.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Path;
import java.util.List;

@Slf4j
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class RecordServiceTest {


    @Autowired
    private RecordService recordService;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void search() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
//
//        Root<Record> root = criteriaQuery.from(Record.class);


//        criteriaQuery.groupBy(root.get("licensePlateNumber"));
//
//        criteriaQuery.select(criteriaBuilder.tuple(root.get("licensePlateNumber"), criteriaBuilder.count(root.get("licensePlateNumber"))));
//        TypedQuery<Tuple> q = entityManager.createQuery(criteriaQuery);
//        List<Tuple> resultList = q.getResultList();
//
//        for (Tuple tuple : resultList) {
//            System.out.println(tuple);
//        }


        Specification<Record> specification = (root, query, criteriaBuilder) -> {
            Path<String> licensePlateNumber = root.get("licensePlateNumber");
            query.groupBy(licensePlateNumber);
            query.multiselect(criteriaBuilder.tuple(licensePlateNumber, criteriaBuilder.count(licensePlateNumber).alias("count")));
            return null;
        };

        List<Record> list = recordRepository.findAll(specification);


//        String select = "select new Record (r.licensePlateNumber, count(r.licensePlateNumber)) \n";
//        String count = "select count(r)";
//        String from = "from Record r \n";
//        String group = "group by r.licensePlateNumber";
//
//
//        TypedQuery<Record> query = entityManager.createQuery(select + from + group, Record.class);
//        int pageNumber = 30;
//        List<Record> recordList = query
//                .setFirstResult(pageNumber)
//                .setMaxResults(10)
//                .getResultList();
//        List<RecordVO> list = RecordMapper.INSTANCE.convertList(recordList);
////        PageRequest pageRequest = PageRequest.of(pageNumber, list.size());
////        System.out.println("pageRequest = " + pageRequest);
//
//        CriteriaQuery<Record> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Record.class);
//        Root<Record> root = criteriaQuery.from(Record.class);


    }


}
