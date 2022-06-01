package cn.com.goodlan.its.core.service.system.camera;

import cn.com.goodlan.its.core.dao.primary.system.camera.CameraRepository;
import cn.com.goodlan.its.core.mapstruct.CameraMapper;
import cn.com.goodlan.its.core.pojo.dto.CameraDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Camera;
import cn.com.goodlan.its.core.pojo.entity.primary.Region;
import cn.com.goodlan.its.core.pojo.vo.CameraVO;
import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 摄像头管理Service
 *
 * @author liukai
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CameraServiceImpl implements CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    @Override
    public Page<CameraVO> search(CameraDTO cameraDTO, Pageable pageable) {
        Page<Camera> page = cameraRepository.findAll(new Specification<Camera>() {
            @Override
            public Predicate toPredicate(Root<Camera> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotEmpty(cameraDTO.getName())) {
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), cameraDTO.getName() + "%"));
                }
                if (StringUtils.isNotEmpty(cameraDTO.getIp())) {
                    list.add(criteriaBuilder.like(root.get("ip").as(String.class), cameraDTO.getIp() + "%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        List<CameraVO> list = CameraMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(CameraDTO cameraDTO) {
        Camera camera = new Camera();
        camera.setName(cameraDTO.getName());
        camera.setIp(cameraDTO.getIp());
        camera.setPosition(cameraDTO.getPosition());
        camera.setRegion(new Region(cameraDTO.getRegionId()));
        cameraRepository.save(camera);
    }

    @Override
    public void update(CameraDTO cameraDTO) {
        Camera camera = cameraRepository.getById(cameraDTO.getId());
        camera.setName(cameraDTO.getName());
        camera.setIp(cameraDTO.getIp());
        camera.setPosition(cameraDTO.getPosition());
        camera.setRegion(new Region(cameraDTO.getRegionId()));
        cameraRepository.save(camera);
    }

    @Override
    public void remove(String ids) {
        String[] cameraIds = Convert.toStrArray(ids);
        for (String cameraId : cameraIds) {
            cameraRepository.delete(new Camera(cameraId));
        }
    }

    @Override
    public CameraVO getById(String id) {
        Camera camera = cameraRepository.getById(id);
        return CameraMapper.INSTANCE.convert(camera);
    }

}
