package cn.com.goodlan.its.core.mapstruct;

import cn.com.goodlan.its.pojo.entity.primary.Score;
import cn.com.goodlan.its.pojo.vo.ScoreVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ScoreMapper {

    ScoreMapper INSTANCE = Mappers.getMapper(ScoreMapper.class);

    List<ScoreVO> convertList(List<Score> scoreList);

    @Mappings({
            @Mapping(source = "region.name", target = "regionName"),
            @Mapping(source = "region.id", target = "regionId"),
            @Mapping(source = "violation.id", target = "violationId"),
            @Mapping(source = "violation.name", target = "violationName")
    })
    ScoreVO convert(Score score);

}
