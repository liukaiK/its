package cn.com.goodlan.its.web.controller.system.violation;

import cn.com.goodlan.its.core.annotations.ResponseResultBody;
import cn.com.goodlan.its.core.pojo.dto.ViolationTypeDTO;
import cn.com.goodlan.its.core.pojo.vo.ViolationTypeVO;
import cn.com.goodlan.its.core.service.system.violation.ViolationTypeService;
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
 * 违规分类Controller
 *
 * @author 王硕
 * @date 2021/1/18-17:09
 */
@RestController
@ResponseResultBody
@RequestMapping("/system/violation")
public class ViolationTypeController {

    @Autowired
    private ViolationTypeService violationTypeService;


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
    public Page<ViolationTypeVO> search(ViolationTypeDTO violationTypeDTO, @PageableDefault Pageable pageable) {
        return violationTypeService.search(violationTypeDTO, pageable);
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
    public void add(@Valid ViolationTypeDTO violationDTO) {
        violationTypeService.save(violationDTO);
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:violation:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("violation", violationTypeService.getById(id));
        return new ModelAndView("system/violation/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:violation:edit')")
    public void edit(@Valid ViolationTypeDTO violationDTO) {
        violationTypeService.update(violationDTO);
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('system:violation:remove')")
    public void remove(String ids) {
        violationTypeService.remove(ids);
    }

}
