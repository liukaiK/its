package cn.com.goodlan.its.core.mapstruct;

import cn.com.goodlan.its.core.pojo.entity.primary.ViolationType;
import cn.com.goodlan.its.core.pojo.vo.ViolationTypeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * description:
 *
 * @author: 王硕
 * @date: 2021/1/18-17:24
 */
@Mapper
public interface ViolationMapper {
    ViolationMapper INSTANCE = Mappers.getMapper(ViolationMapper.class);

    List<ViolationTypeVO> convertList(List<ViolationType> violationList);

    ViolationTypeVO convert(ViolationType violation);

}
