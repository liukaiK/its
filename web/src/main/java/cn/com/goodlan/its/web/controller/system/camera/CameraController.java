package cn.com.goodlan.its.web.controller.system.camera;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.dto.CameraDTO;
import cn.com.goodlan.its.pojo.vo.CameraVO;
import cn.com.goodlan.its.service.system.camera.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 摄像头管理控制器
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/system/camera")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @GetMapping
    @PreAuthorize("hasAuthority('system:camera:view')")
    public ModelAndView camera() {
        return new ModelAndView("system/camera/camera");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:camera:search')")
    public Page<CameraVO> search(CameraDTO cameraDTO, @PageableDefault Pageable pageable) {
        return cameraService.search(cameraDTO, pageable);
    }


}
