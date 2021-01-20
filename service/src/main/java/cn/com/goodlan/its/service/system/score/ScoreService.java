package cn.com.goodlan.its.service.system.score;

import cn.com.goodlan.its.pojo.dto.ScoreDTO;
import cn.com.goodlan.its.pojo.vo.ScoreVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScoreService {

    Page<ScoreVO> search(ScoreDTO scoreDTO, Pageable pageable);

    void save(ScoreDTO scoreDTO);

    void remove(String ids);

    ScoreVO getById(String id);

    void update(ScoreDTO scoreDTO);
}
