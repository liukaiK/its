package cn.com.goodlan.its.core.mapstruct;

import cn.com.goodlan.its.core.pojo.entity.primary.Camera;
import cn.com.goodlan.its.core.pojo.vo.CameraVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CameraMapper {

    CameraMapper INSTANCE = Mappers.getMapper(CameraMapper.class);

    List<CameraVO> convertList(List<Camera> cameraList);

    @Mappings({
            @Mapping(source = "region.name", target = "regionName"),
            @Mapping(source = "region.id", target = "regionId")
    })
    CameraVO convert(Camera camera);

}
