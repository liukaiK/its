package cn.com.goodlan.its.service.system.camera;

import cn.com.goodlan.its.dao.system.camera.CameraRepository;
import cn.com.goodlan.its.pojo.dto.CameraDTO;
import cn.com.goodlan.its.pojo.entity.Camera;
import cn.com.goodlan.its.pojo.vo.CameraVO;
import cn.com.goodlan.mapstruct.CameraMapper;
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

    }

    @Override
    public void update(CameraDTO cameraDTO) {

    }

    @Override
    public void remove(String ids) {

    }

    @Override
    public CameraVO getById(String id) {
        Camera camera = cameraRepository.getOne(id);
        return CameraMapper.INSTANCE.convert(camera);
    }

}
