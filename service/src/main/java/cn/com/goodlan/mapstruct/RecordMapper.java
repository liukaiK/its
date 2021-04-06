package cn.com.goodlan.mapstruct;

import cn.com.goodlan.its.pojo.entity.primary.Record;
import cn.com.goodlan.its.pojo.vo.RecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RecordMapper {

    RecordMapper INSTANCE = Mappers.getMapper(RecordMapper.class);

    List<RecordVO> convertList(List<Record> recordList);

    @Mappings({
//            @Mapping(target = "phoneNumber", expression = "java(cn.com.goodlan.its.common.util.AESUtil.decrypt(user.getPhoneNumber()))"),
//            @Mapping(source = "college.name", target = "collegeName"),
//            @Mapping(source = "college.id", target = "collegeId"),
            @Mapping(source = "licensePlateNumber", target = "vehicleNumber")
//            @Mapping(source = "event.violation.name", target = "violationName"),
//            @Mapping(source = "event.licensePlateNumber", target = "vehicleNumber"),
//            @Mapping(source = "event.camera.region.id", target = "regionId"),
//            @Mapping(source = "event.camera.region.name", target = "regionName"),
    })
    RecordVO convert(Record record);

}