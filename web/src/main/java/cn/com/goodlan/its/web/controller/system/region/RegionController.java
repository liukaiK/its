package cn.com.goodlan.its.web.controller.system.region;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.dto.RegionDTO;
import cn.com.goodlan.its.pojo.vo.RegionVO;
import cn.com.goodlan.its.service.system.region.RegionService;
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
 * 区域管理控制器
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
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

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:region:add')")
    public ModelAndView add(Model model) {
        return new ModelAndView("system/region/add");
    }

    /**
     * 新增区域
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('system:region:add')")
    public void add(@Valid RegionDTO regionDTO) {
        regionService.save(regionDTO);
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:region:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("region", regionService.getById(id));
        return new ModelAndView("system/region/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:region:edit')")
    public void edit(@Valid RegionDTO regionDTO) {
        regionService.update(regionDTO);
    }


    /**
     * 删除区域
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('system:region:remove')")
    public void remove(String ids) {
        regionService.remove(ids);
    }

}
