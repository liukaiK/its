package cn.com.goodlan.mapstruct;

import cn.com.goodlan.its.pojo.entity.ParkLot;
import cn.com.goodlan.its.pojo.vo.ParkLotVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ParkLotMapper {

    ParkLotMapper INSTANCE = Mappers.getMapper(ParkLotMapper.class);

    List<ParkLotVO> convertList(List<ParkLot> parkLotList);

    ParkLotVO convert(ParkLot parkLot);

}
