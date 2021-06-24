package cn.com.goodlan.its.web.controller.system.camera;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.core.pojo.dto.CameraDTO;
import cn.com.goodlan.its.core.pojo.vo.CameraVO;
import cn.com.goodlan.its.core.service.system.camera.CameraService;
import cn.com.goodlan.its.core.service.system.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @Autowired
    private RegionService regionService;

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


    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:camera:add')")
    public ModelAndView add(Model model) {
        model.addAttribute("region", regionService.findAll());
        return new ModelAndView("system/camera/add");
    }

    /**
     * 新增保存
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('system:camera:add')")
    public void add(@Valid CameraDTO cameraDTO) {
        cameraService.save(cameraDTO);
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:camera:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("camera", cameraService.getById(id));
        model.addAttribute("region", regionService.findAll());
        return new ModelAndView("system/camera/edit");
    }


    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:camera:edit')")
    public void edit(@Valid CameraDTO cameraDTO) {
        cameraService.update(cameraDTO);
    }

    /**
     * 删除用户
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('system:camera:remove')")
    public void remove(String ids) {
        cameraService.remove(ids);
    }

}
