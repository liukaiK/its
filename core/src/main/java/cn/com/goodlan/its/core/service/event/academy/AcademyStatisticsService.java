package cn.com.goodlan.its.core.service.event.academy;

import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.dto.RecordDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.AcademyStatistics;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import cn.com.goodlan.its.core.pojo.vo.RecordVO;
import cn.com.goodlan.its.core.pojo.vo.StatisticsExcel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AcademyStatisticsService {



    List<RecordVO> search();

    List<StatisticsExcel> export(EventDTO eventDTO);

    Page<AcademyStatistics> academyStatistics(RecordDTO recordDTO, Pageable pageable);
}
