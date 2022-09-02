package cn.com.goodlan.its.core.mapstruct;

import cn.com.goodlan.its.core.pojo.entity.primary.event.EventHistory;
import cn.com.goodlan.its.core.pojo.vo.EventHistoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventHistoryMapper {

    EventHistoryMapper INSTANCE = Mappers.getMapper(EventHistoryMapper.class);

    List<EventHistoryVO> convert(List<EventHistory> eventHistories);

    @Mappings({
            @Mapping(target = "id", source = "id")
    })
    EventHistoryVO convert(EventHistory eventHistory);

}
