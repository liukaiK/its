package cn.com.goodlan.its.web.controller.system.vehicle;

import cn.com.goodlan.its.core.annotations.ResponseResultBody;
import cn.com.goodlan.its.core.pojo.dto.VehicleDTO;
import cn.com.goodlan.its.core.pojo.vo.VehicleVO;
import cn.com.goodlan.its.core.service.system.vehicle.VehicleService;
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
    public Page<VehicleVO> search(VehicleDTO vehicleDTO, @PageableDefault Pageable pageable) {
        return vehicleService.search(vehicleDTO, pageable);
    }

}
