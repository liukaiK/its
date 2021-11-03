package cn.com.goodlan.its.core.service.event.academy;

import cn.com.goodlan.its.core.dao.primary.system.college.CollegeRepository;
import cn.com.goodlan.its.core.dao.primary.system.record.RecordRepository;
import cn.com.goodlan.its.core.mapstruct.RecordMapper;
import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.dto.RecordDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.AcademyStatistics;
import cn.com.goodlan.its.core.pojo.entity.primary.Record;
import cn.com.goodlan.its.core.pojo.vo.RecordVO;
import cn.com.goodlan.its.core.pojo.vo.StatisticsExcel;
import cn.com.goodlan.its.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcademyStatisticsServiceImpl implements AcademyStatisticsService {

    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public Page<AcademyStatistics> academyStatistics(RecordDTO recordDTO, Pageable pageable) {
        List<Map<String, Object>> maps;
        if (StringUtils.isNotBlank(recordDTO.getCollegeName())) {
            maps = recordRepository.academyStatisticsByName(recordDTO.getCollegeName(), pageable);
        } else {
            maps = recordRepository.academyStatistics(pageable);
        }
        List<AcademyStatistics> collect = maps.stream().map(map -> {
            AcademyStatistics academyStatistics = new AcademyStatistics();
            academyStatistics.setName(map.get("name") + "");
            academyStatistics.setNum(Optional.ofNullable(map.get("num")).orElse("--") + "");
            academyStatistics.setLatestTime(Optional.ofNullable(map.get("latestTime")).orElse("--") + "");
            return academyStatistics;
        }).collect(Collectors.toList());
        long count = collegeRepository.count();
        PageImpl<AcademyStatistics> academyStatistics = new PageImpl<>(collect, pageable, count);
        return academyStatistics;
    }

    @Override
    public List<RecordVO> search() {
        List<Record> list = recordRepository.findByOrderByTime();
        return RecordMapper.INSTANCE.convertList(list);
    }

    @Override
    public List<StatisticsExcel> export(EventDTO eventDTO) {
        return recordRepository.exportExcel();
    }


}
