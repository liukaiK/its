package cn.com.goodlan.its.core.mapstruct;

import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import cn.com.goodlan.its.core.util.StringUtils;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class EventMapper {

    @Autowired
    private FdfsWebServer fdfsWebServer;

    public abstract List<EventVO> convertList(List<Event> eventList);

//    @Mappings({
//            @Mapping(source = "licensePlateNumber", target = "vehicleNumber"),
//            @Mapping(source = "driverName", target = "driverName"),
//            @Mapping(source = "driverPhone", target = "driverPhone"),
//            @Mapping(source = "collegeName", target = "bmmc"),
//            @Mapping(source = "violationName", target = "violationName"),
//            @Mapping(source = "camera.region.name", target = "regionName"),
//            @Mapping(source = "score", target = "score", qualifiedByName = "convertScore")
//            @Mapping(source = "num", target = "num", qualifiedByName = "convertNum")
//    })
//    public abstract EventVO convert(Event event);


    public EventVO convert(Event event) {
        if (event == null) {
            return null;
        }

        EventVO eventVO = new EventVO();

        eventVO.setVehicleNumber(event.getLicensePlateNumber());
        eventVO.setDriverName(event.getDriverName());
        eventVO.setDriverPhone(event.getDriverPhone());
        eventVO.setBmmc(event.getCollegeName());
        eventVO.setViolationName(event.getViolationName());
        eventVO.setRegionName(eventCameraRegionName(event));
        eventVO.setScore(convertScore(event.getScore()));
        eventVO.setId(event.getId());
        eventVO.setVehicleColor(event.getVehicleColor());
        eventVO.setVehicleSize(event.getVehicleSize());
        eventVO.setPlace(event.getPlace());
        eventVO.setSpeed(event.getSpeed());
        eventVO.setTime(event.getTime());
        if (!StringUtils.startsWith(event.getImageUrl(), "/")) {
            eventVO.setImageUrl(fdfsWebServer.getWebServerUrl() + "/" + event.getImageUrl());
        } else {
            eventVO.setImageUrl(fdfsWebServer.getWebServerUrl() + event.getImageUrl());
        }
        eventVO.setNum(convertNum(event.getViolationName(), event.getNum()));
        eventVO.setSource(event.getSource().getDescription());

        return eventVO;
    }

    private String eventCameraRegionName(Event event) {
        if (event == null) {
            return null;
        }
        String name = event.getRegionName();
        if (name == null) {
            return null;
        }
        return name;
    }

    public String convertScore(Integer score) {
        if (score == 0) {
            return "第一次不扣分";
        } else {
            return score + "分";
        }
    }

    public String convertNum(String violationName, Long num) {
        if (violationName.startsWith("超速")) {
            return "第" + num + "次超速";
        } else {
            return "第" + num + "次违停";
        }
    }

}
