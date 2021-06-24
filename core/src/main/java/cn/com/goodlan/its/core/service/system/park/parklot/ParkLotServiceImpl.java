package cn.com.goodlan.its.core.service.system.park.parklot;

import cn.com.goodlan.its.dao.primary.park.parklot.ParkLotRepository;
import cn.com.goodlan.its.pojo.dto.ParkLotDTO;
import cn.com.goodlan.its.pojo.entity.primary.ParkLot;
import cn.com.goodlan.its.pojo.vo.ParkLotVO;
import cn.com.goodlan.its.core.mapstruct.ParkLotMapper;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 停车场管理
 *
 * @author liukai
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ParkLotServiceImpl implements ParkLotService {

    @Autowired
    private ParkLotRepository parkLotRepository;


    @Override
    public Page<ParkLotVO> search(ParkLotDTO parkLotDTO, Pageable pageable) {
        Page<ParkLot> page = parkLotRepository.findAll(pageable);
        List<ParkLotVO> list = ParkLotMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(ParkLotDTO parkLotDTO) {
        ParkLot parkLot = new ParkLot();
        parkLot.setName(parkLotDTO.getName());
        parkLot.setCount(parkLotDTO.getCount());
        parkLotRepository.save(parkLot);
    }

    @Override
    public ParkLotVO getById(String id) {
        ParkLot parkLot = parkLotRepository.getOne(id);
        return ParkLotMapper.INSTANCE.convert(parkLot);
    }

    @Override
    public void update(ParkLotDTO parkLotDTO) {
        ParkLot parkLot = parkLotRepository.getOne(parkLotDTO.getId());
        parkLot.setName(parkLotDTO.getName());
        parkLot.setCount(parkLotDTO.getCount());
        parkLotRepository.save(parkLot);
    }

    @Override
    public void remove(String ids) {
        List<String> parkLotIds = Convert.toList(String.class, ids);
        List<ParkLot> regionList = parkLotRepository.findAllById(parkLotIds);
        parkLotRepository.deleteInBatch(regionList);
    }


}
