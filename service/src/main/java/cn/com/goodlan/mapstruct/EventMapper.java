package cn.com.goodlan.mapstruct;

import cn.com.goodlan.its.pojo.entity.Event;
import cn.com.goodlan.its.pojo.vo.EventVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    List<EventVO> convertList(List<Event> eventList);

    @Mappings({
            @Mapping(source = "vehicleNumber", target = "vehicleNumber"),
            @Mapping(source = "vehicle.college.name", target = "collegeName"),
            @Mapping(source = "violation.name", target = "violationName"),
            @Mapping(source = "camera.region.name", target = "regionName")
    })
    EventVO convert(Event event);

}
