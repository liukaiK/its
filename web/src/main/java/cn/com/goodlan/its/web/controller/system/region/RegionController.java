package cn.com.goodlan.its.web.controller.system.region;

import cn.com.goodlan.its.pojo.vo.RegionVO;
import cn.com.goodlan.its.service.system.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 区域管理控制器
 *
 * @author liukai
 */
@RestController
@ControllerAdvice
@RequestMapping("/system/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    @PreAuthorize("hasAuthority('system:region:view')")
    public ModelAndView region() {
        return new ModelAndView("system/region/region");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:region:search')")
    public Page<RegionVO> search(@PageableDefault Pageable pageable) {
        return regionService.search(pageable);
    }


}
