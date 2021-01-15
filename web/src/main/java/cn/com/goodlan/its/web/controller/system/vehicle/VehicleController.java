package cn.com.goodlan.its.web.controller.system.vehicle;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.dto.VehicleDTO;
import cn.com.goodlan.its.pojo.vo.VehicleVO;
import cn.com.goodlan.its.service.system.vehicle.VehicleService;
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
 * 车辆管理
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/system/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    @PreAuthorize("hasAuthority('system:vehicle:view')")
    public ModelAndView vehicle() {
        return new ModelAndView("system/vehicle/vehicle");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:vehicle:search')")
    public Page<VehicleVO> search(@PageableDefault Pageable pageable) {
        return vehicleService.search(pageable);
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:vehicle:add')")
    public ModelAndView add() {
        return new ModelAndView("system/vehicle/add");
    }

    /**
     * 新增功能
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('system:vehicle:add')")
    public void add(@Valid VehicleDTO vehicleDTO) {
        vehicleService.save(vehicleDTO);
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:vehicle:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("vehicle", vehicleService.getById(id));
        return new ModelAndView("system/vehicle/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:vehicle:edit')")
    public void edit(@Valid VehicleDTO vehicleDTO) {
        vehicleService.update(vehicleDTO);
    }

    /**
     * 删除车辆
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('system:vehicle:remove')")
    public void remove(String ids) {
        vehicleService.remove(ids);
    }


}
