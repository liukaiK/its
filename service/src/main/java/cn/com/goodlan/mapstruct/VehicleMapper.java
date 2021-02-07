package cn.com.goodlan.mapstruct;

import cn.com.goodlan.its.pojo.entity.Vehicle;
import cn.com.goodlan.its.pojo.vo.VehicleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    List<VehicleVO> convertList(List<Vehicle> vehicleList);

    @Mappings({
            @Mapping(source = "college.id", target = "collegeId"),
            @Mapping(source = "college.name", target = "collegeName"),
            @Mapping(source = "licensePlateNumber", target = "number")
    })
    VehicleVO convert(Vehicle vehicle);

}
