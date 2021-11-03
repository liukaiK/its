package cn.com.goodlan.its.core.dao.primary.system.record;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.Record;
import cn.com.goodlan.its.core.pojo.vo.StatisticsExcel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface RecordRepository extends CustomizeRepository<Record, String> {

    @Query("select new Record(r.licensePlateNumber, count(r.licensePlateNumber), r.bmmc) from Record r group by r.licensePlateNumber,r.bmmc")
    Page<Record> search(Pageable pageable);

    @Query("select new Record(r.licensePlateNumber, count(r.licensePlateNumber), r.bmmc) from Record r where r.licensePlateNumber = ?1 group by r.licensePlateNumber,r.bmmc")
    Page<Record> searchByLicensePlateNumber(String licensePlateNumber, Pageable pageable);

    List<Record> findByOrderByTime();

    /**
     * 大屏违规车辆统计
     */
    @Query(value = "select t1.bmmc as bmmc,t1.count as count " +
            "from (select bmmc, count(*) as count from eve_record where bmmc is not null group by bmmc) as t1\n" +
            "order by count desc\n" +
            "limit 10", nativeQuery = true)
    List<Object> bigScreen();

    @Query("select new cn.com.goodlan.its.core.pojo.vo.StatisticsExcel(r.bmmc, sum(r.record)) from Record r group by r.bmmc order by sum(r.record) ")
    List<StatisticsExcel> exportExcel();

    @Query(value = "SELECT c.name,SUM( co.count ) as num, DATE_FORMAT(\t(SELECT\n" +
            "\tMAX( eve.time )\n" +
            "FROM\n" +
            "\tsys_college c1 \n" +
            "\tLEFT JOIN sys_vehicle v ON c1.NAME = v.bmmc\n" +
            "\tLEFT JOIN eve_count co ON v.license_plate_number = co.license_plate_number\n" +
            "\tLEFT JOIN eve_event eve ON v.license_plate_number = eve.license_plate_number\n" +
            "\tWHERE c1.`name` = c.`name`\n" +
            "GROUP BY\n" +
            "\tc.NAME \n" +
            "ORDER BY\n" +
            "\teve.time DESC),'%Y-%m-%d %H:%i:%s') as latestTime \n" +
            "\tFROM\n" +
            "\t sys_college c LEFT JOIN sys_vehicle v ON c.NAME = v.bmmc\n" +
            "\tLEFT JOIN eve_count co ON v.license_plate_number = co.license_plate_number \n" +
            "GROUP BY c.id", nativeQuery = true)
    List<Map<String,Object>> academyStatistics(Pageable pageable);

    @Query(value = "SELECT c.name,SUM( co.count ) as num, DATE_FORMAT(\t(SELECT\n" +
            "\tMAX( eve.time )\n" +
            "FROM\n" +
            "\tsys_college c1 \n" +
            "\tLEFT JOIN sys_vehicle v ON c1.NAME = v.bmmc\n" +
            "\tLEFT JOIN eve_count co ON v.license_plate_number = co.license_plate_number\n" +
            "\tLEFT JOIN eve_event eve ON v.license_plate_number = eve.license_plate_number\n" +
            "\tWHERE c1.`name` = c.`name`\n" +
            "GROUP BY\n" +
            "\tc.NAME \n" +
            "ORDER BY\n" +
            "\teve.time DESC),'%Y-%m-%d %H:%i:%s') as latestTime \n" +
            "\tFROM\n" +
            "\t sys_college c LEFT JOIN sys_vehicle v ON c.NAME = v.bmmc\n" +
            "\tLEFT JOIN eve_count co ON v.license_plate_number = co.license_plate_number \n" +
            " WHERE c.name like CONCAT('%',?1,'%')"+
            "GROUP BY c.id", nativeQuery = true)
    List<Map<String,Object>> academyStatisticsByName(String name, Pageable pageable);
}
