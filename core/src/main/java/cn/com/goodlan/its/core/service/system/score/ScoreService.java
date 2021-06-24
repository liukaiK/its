package cn.com.goodlan.its.core.service.system.score;

import cn.com.goodlan.its.core.pojo.dto.ScoreDTO;
import cn.com.goodlan.its.core.pojo.vo.ScoreVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScoreService {

    Page<ScoreVO> search(ScoreDTO scoreDTO, Pageable pageable);

    List<ScoreVO> findAll();

    void save(ScoreDTO scoreDTO);

    void remove(String ids);

    ScoreVO getById(String id);

    void update(ScoreDTO scoreDTO);
}
