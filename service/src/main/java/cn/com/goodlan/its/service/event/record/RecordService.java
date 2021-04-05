package cn.com.goodlan.its.service.event.record;

import cn.com.goodlan.its.pojo.dto.RecordDTO;
import cn.com.goodlan.its.pojo.vo.RecordVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecordService {


    Page<RecordVO> search(RecordDTO recordDTO, Pageable pageable);

    List<RecordVO> search();

}
