package cn.com.goodlan.its.core.mapstruct;

import cn.com.goodlan.its.core.pojo.entity.primary.user.User;
import cn.com.goodlan.its.core.pojo.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserVO> convertList(List<User> userList);

    @Mappings({
            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber"),
            @Mapping(source = "college.name", target = "collegeName"),
            @Mapping(source = "username.username", target = "username"),
            @Mapping(source = "college.id", target = "collegeId")
    })
    UserVO convert(User user);

}