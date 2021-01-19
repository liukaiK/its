package cn.com.goodlan.its.web.controller.system.violation;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.dto.VehicleDTO;
import cn.com.goodlan.its.pojo.dto.ViolationDTO;
import cn.com.goodlan.its.pojo.vo.ViolationVO;
import cn.com.goodlan.its.service.system.violation.ViolationService;
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
 * description:
 *
 * @author: 王硕
 * @date: 2021/1/18-17:09
 */
@RestController
@ResponseResultBody
@RequestMapping("/system/violation")
public class ViolationController {

    @Autowired
    private ViolationService violationService;


    @GetMapping
    @PreAuthorize("hasAuthority('system:violation:view')")
    public ModelAndView violation() {
        return new ModelAndView("system/violation/violation");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:violation:search')")
    public Page<ViolationVO> search(@PageableDefault Pageable pageable) {
        return violationService.search(pageable);
    }


    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:violation:add')")
    public ModelAndView add() {
        return new ModelAndView("system/violation/add");
    }

    /**
     * 新增功能
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('system:violation:add')")
    public void add(@Valid ViolationDTO violationDTO) {
        violationService.save(violationDTO);
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:violation:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("violation", violationService.getById(id));
        return new ModelAndView("system/violation/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:violation:edit')")
    public void edit(@Valid ViolationDTO violationDTO) {
        violationService.update(violationDTO);
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('system:violation:remove')")
    public void remove(String ids) {
        violationService.remove(ids);
    }

}
