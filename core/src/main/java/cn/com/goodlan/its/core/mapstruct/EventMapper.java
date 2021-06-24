package cn.com.goodlan.its.core.mapstruct;

import cn.com.goodlan.its.core.pojo.entity.primary.Event;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
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
            @Mapping(source = "licensePlateNumber", target = "vehicleNumber"),
            @Mapping(source = "vehicle.driverName", target = "driverName"),
            @Mapping(source = "vehicle.driverPhone", target = "driverPhone"),
            @Mapping(source = "vehicle.collegeName", target = "bmmc"),
            @Mapping(source = "score.violation.name", target = "violationName"),
            @Mapping(source = "camera.region.name", target = "regionName"),
            @Mapping(source = "score.number", target = "score"),
            @Mapping(target = "status", expression = "java(event.getStatus().getValue())")
    })
    EventVO convert(Event event);

}
