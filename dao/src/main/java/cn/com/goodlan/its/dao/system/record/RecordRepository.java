package cn.com.goodlan.its.dao.system.record;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends CustomizeRepository<Record, String> {

    @Query("select new Record(r.licensePlateNumber, count(r.licensePlateNumber), r.bmmc) from Record r group by r.licensePlateNumber,r.bmmc")
    Page<Record> search(Pageable pageable);

    @Query("select new Record(r.licensePlateNumber, count(r.licensePlateNumber), r.bmmc) from Record r where r.licensePlateNumber = ?1 group by r.licensePlateNumber,r.bmmc")
    Page<Record> searchByLicensePlateNumber(String licensePlateNumber, Pageable pageable);

    List<Record> findByOrderByTime();

}
