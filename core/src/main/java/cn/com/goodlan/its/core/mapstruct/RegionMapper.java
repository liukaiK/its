package cn.com.goodlan.its.core.mapstruct;

import cn.com.goodlan.its.core.pojo.entity.primary.Region;
import cn.com.goodlan.its.core.pojo.vo.RegionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RegionMapper {

    RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);

    List<RegionVO> convertList(List<Region> regionList);

    RegionVO convert(Region Region);

}
