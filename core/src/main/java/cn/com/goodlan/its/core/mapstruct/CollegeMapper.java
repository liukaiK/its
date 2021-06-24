package cn.com.goodlan.its.core.mapstruct;

import cn.com.goodlan.its.pojo.entity.primary.College;
import cn.com.goodlan.its.pojo.vo.CollegeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CollegeMapper {

    CollegeMapper INSTANCE = Mappers.getMapper(CollegeMapper.class);

    List<CollegeVO> convertList(List<College> menuList);

    @Mappings({
            @Mapping(source = "parent.id", target = "parentId"),
            @Mapping(source = "parent.name", target = "parentName")
    })
    CollegeVO convert(College college);

}
