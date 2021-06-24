package cn.com.goodlan.its.core.service.event.statistics;

import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.dto.RecordDTO;
import cn.com.goodlan.its.core.pojo.vo.RecordVO;
import cn.com.goodlan.its.core.pojo.vo.StatisticsExcel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatisticsService {


    Page<RecordVO> search(RecordDTO recordDTO, Pageable pageable);

    List<RecordVO> search();

    List<StatisticsExcel> export(EventDTO eventDTO);

}
