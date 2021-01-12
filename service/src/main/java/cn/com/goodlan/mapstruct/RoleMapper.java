package cn.com.goodlan.mapstruct;

import cn.com.goodlan.its.pojo.entity.Role;
import cn.com.goodlan.its.pojo.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    List<RoleVO> convertList(List<Role> roleList);

    RoleVO convert(Role role);

}
