package cn.com.goodlan.its.dao.primary.system.score;


import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.primary.Region;
import cn.com.goodlan.its.pojo.entity.primary.Score;
import cn.com.goodlan.its.pojo.entity.primary.ViolationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author liukai
 */
public interface ScoreRepository extends CustomizeRepository<Score, String> {

    Score getByRegionAndViolation(Region region, ViolationType violation);

    Page<Score> findByRegion(Region region, Pageable pageable);

}
