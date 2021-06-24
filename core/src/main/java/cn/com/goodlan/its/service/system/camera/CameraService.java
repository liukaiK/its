package cn.com.goodlan.its.service.system.camera;

import cn.com.goodlan.its.pojo.dto.CameraDTO;
import cn.com.goodlan.its.pojo.vo.CameraVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CameraService {

    Page<CameraVO> search(CameraDTO cameraDTO, Pageable pageable);

    void save(CameraDTO cameraDTO);

    void update(CameraDTO cameraDTO);

    void remove(String ids);

    CameraVO getById(String id);

}
