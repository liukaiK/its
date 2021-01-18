package cn.com.goodlan.its.service.system.region;

import cn.com.goodlan.its.dao.system.region.RegionRepository;
import cn.com.goodlan.its.pojo.dto.RegionDTO;
import cn.com.goodlan.its.pojo.entity.Region;
import cn.com.goodlan.its.pojo.vo.RegionVO;
import cn.com.goodlan.mapstruct.RegionMapper;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 区域管理Service
 *
 * @author liukai
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;


    @Override
    public Page<RegionVO> search(Pageable pageable) {
        Page<Region> page = regionRepository.findAll(pageable);
        List<RegionVO> list = RegionMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(RegionDTO regionDTO) {
        Region region = new Region();
        region.setName(regionDTO.getName());
        regionRepository.save(region);
    }

    @Override
    public void remove(String ids) {
        String[] regionIds = Convert.toStrArray(ids);
        for (String regionId : regionIds) {
            regionRepository.delete(new Region(regionId));
        }
    }

    @Override
    public RegionVO getById(String id) {
        Region region = regionRepository.getOne(id);
        return RegionMapper.INSTANCE.convert(region);
    }

    @Override
    public void update(RegionDTO regionDTO) {
        Region region = regionRepository.getOne(regionDTO.getId());
        region.setName(regionDTO.getName());
        regionRepository.save(region);
    }

}
