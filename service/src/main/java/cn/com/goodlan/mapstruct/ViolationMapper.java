package cn.com.goodlan.mapstruct;

import cn.com.goodlan.its.pojo.entity.Vehicle;
import cn.com.goodlan.its.pojo.entity.Violation;
import cn.com.goodlan.its.pojo.vo.VehicleVO;
import cn.com.goodlan.its.pojo.vo.ViolationVO;
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

    List<ViolationVO> convertList(List<Violation> violationList);

    ViolationVO convert(Violation violation);

}
