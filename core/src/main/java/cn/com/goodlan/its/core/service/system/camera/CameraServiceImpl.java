package cn.com.goodlan.its.core.service.system.camera;

import cn.com.goodlan.its.core.dao.primary.system.camera.CameraRepository;
import cn.com.goodlan.its.core.pojo.dto.CameraDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Camera;
import cn.com.goodlan.its.core.pojo.entity.primary.Region;
import cn.com.goodlan.its.core.pojo.vo.CameraVO;
import cn.com.goodlan.its.core.mapstruct.CameraMapper;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Page<Camera> page = cameraRepository.findAll(pageable);
        List<CameraVO> list = CameraMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(CameraDTO cameraDTO) {
        Camera camera = new Camera();
        camera.setName(cameraDTO.getName());
        camera.setFactory(cameraDTO.getFactory());
        camera.setIp(cameraDTO.getIp());
        camera.setPosition(cameraDTO.getPosition());
        camera.setRegion(new Region(cameraDTO.getRegionId()));
        cameraRepository.save(camera);
    }

    @Override
    public void update(CameraDTO cameraDTO) {
        Camera camera = cameraRepository.getOne(cameraDTO.getId());
        camera.setName(cameraDTO.getName());
        camera.setFactory(cameraDTO.getFactory());
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
        Camera camera = cameraRepository.getOne(id);
        return CameraMapper.INSTANCE.convert(camera);
    }

}
