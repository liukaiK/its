package cn.com.goodlan.its.core.mapstruct;

import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class EventMapper {

    public abstract List<EventVO> convertList(List<Event> eventList);

    @Mappings({
            @Mapping(source = "licensePlateNumber", target = "vehicleNumber"),
            @Mapping(source = "driverName", target = "driverName"),
            @Mapping(source = "driverPhone", target = "driverPhone"),
            @Mapping(source = "collegeName", target = "bmmc"),
            @Mapping(source = "violationName", target = "violationName"),
            @Mapping(source = "camera.region.name", target = "regionName"),
            @Mapping(source = "score", target = "score"),
    })
    public abstract EventVO convert(Event event);


}
