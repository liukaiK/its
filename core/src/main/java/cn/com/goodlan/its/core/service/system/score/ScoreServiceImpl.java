package cn.com.goodlan.its.core.service.system.score;

import cn.com.goodlan.its.core.dao.primary.system.score.ScoreRepository;
import cn.com.goodlan.its.core.mapstruct.ScoreMapper;
import cn.com.goodlan.its.core.pojo.dto.ScoreDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Region;
import cn.com.goodlan.its.core.pojo.entity.primary.Score;
import cn.com.goodlan.its.core.pojo.entity.primary.ViolationType;
import cn.com.goodlan.its.core.pojo.vo.ScoreVO;
import cn.com.goodlan.its.core.util.StringUtils;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public Page<ScoreVO> search(ScoreDTO scoreDTO, Pageable pageable) {
        Page<Score> page;
        if (StringUtils.isNotEmpty(scoreDTO.getRegionId())) {
            page = scoreRepository.findByRegion(new Region(scoreDTO.getRegionId()), pageable);
        } else {
            page = scoreRepository.findAll(pageable);
        }
        List<ScoreVO> list = ScoreMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public List<ScoreVO> findAll() {
        List<Score> scoreList = scoreRepository.findAll();
        return ScoreMapper.INSTANCE.convertList(scoreList);
    }

    @Override
    public void save(ScoreDTO scoreDTO) {
        Score score = new Score();
        score.setName(scoreDTO.getName());
        score.setNumber(scoreDTO.getNumber());
        score.setRegion(new Region(scoreDTO.getRegionId()));
        score.setViolation(new ViolationType(scoreDTO.getViolationId()));
        score.setRemark(scoreDTO.getRemark());
        score.setMaxRange(scoreDTO.getMaxRange());
        score.setMinRange(scoreDTO.getMinRange());
        scoreRepository.save(score);
    }

    @Override
    public void remove(String ids) {
        String[] scoreIds = Convert.toStrArray(ids);
        for (String scoreId : scoreIds) {
            scoreRepository.delete(new Score(scoreId));
        }
    }

    @Override
    public ScoreVO getById(String id) {
        Score score = scoreRepository.getById(id);
        return ScoreMapper.INSTANCE.convert(score);
    }

    @Override
    public void update(ScoreDTO scoreDTO) {
        Score score = scoreRepository.getById(scoreDTO.getId());
        score.setName(scoreDTO.getName());
        score.setNumber(scoreDTO.getNumber());
        score.setRegion(new Region(scoreDTO.getRegionId()));
        score.setViolation(new ViolationType(scoreDTO.getViolationId()));
        score.setRemark(scoreDTO.getRemark());
        score.setMinRange(scoreDTO.getMinRange());
        score.setMaxRange(scoreDTO.getMaxRange());
        scoreRepository.save(score);
    }

    @Override
    public Score getScore(List<Score> scoreList, int speed) {
        Score score = null;

        for (Score tempScore : scoreList) {
            Integer min = tempScore.getMinRange();
            Integer max = tempScore.getMaxRange();

            if (min == null) {
                min = Integer.MIN_VALUE;
            }

            if (max == null) {
                max = Integer.MAX_VALUE;
            }

            if (min <= speed && speed <= max) {
                score = tempScore;
                break;
            }
        }
        return score;
    }


}
