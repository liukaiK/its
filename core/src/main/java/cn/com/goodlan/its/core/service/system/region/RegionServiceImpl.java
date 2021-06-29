package cn.com.goodlan.its.core.service.system.region;

import cn.com.goodlan.its.common.exception.BusinessException;
import cn.com.goodlan.its.core.dao.primary.system.region.RegionRepository;
import cn.com.goodlan.its.core.mapstruct.RegionMapper;
import cn.com.goodlan.its.core.pojo.dto.RegionDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Region;
import cn.com.goodlan.its.core.pojo.vo.RegionVO;
import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
    public Page<RegionVO> search(RegionDTO regionDTO, Pageable pageable) {
        Specification<Region> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(regionDTO.getName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), regionDTO.getName() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Page<Region> page = regionRepository.findAll(specification, pageable);
        List<RegionVO> list = RegionMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(RegionDTO regionDTO) {
        Region region = new Region();
        region.updateName(regionDTO.getName());
        regionRepository.save(region);
    }

    @Override
    public void remove(String ids) {
        List<String> regionIds = Convert.toList(String.class, ids);
        List<Region> regionList = regionRepository.findAllById(regionIds);
        for (Region region : regionList) {
            if (region.hasCamera()) {
                throw new BusinessException("存在摄像头,不允许删除");
            }
        }
        regionRepository.deleteInBatch(regionList);
    }

    @Override
    public RegionVO getById(String id) {
        Region region = regionRepository.getOne(id);
        return RegionMapper.INSTANCE.convert(region);
    }

    @Override
    public List<RegionVO> findAll() {
        List<Region> all = regionRepository.findAll();
        return RegionMapper.INSTANCE.convertList(all);
    }

    @Override
    public void update(RegionDTO regionDTO) {
        Region region = regionRepository.getOne(regionDTO.getId());
        region.updateName(regionDTO.getName());
        regionRepository.save(region);
    }

}
