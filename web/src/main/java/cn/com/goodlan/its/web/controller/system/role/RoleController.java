package cn.com.goodlan.its.web.controller.system.role;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.dto.RoleDTO;
import cn.com.goodlan.its.pojo.vo.RoleVO;
import cn.com.goodlan.its.service.system.role.RoleService;
import org.apache.commons.lang3.StringUtils;
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
 * 角色管理Controller
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping
    @PreAuthorize("hasAuthority('system:role:view')")
    public ModelAndView role() {
        return new ModelAndView("system/role/role");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:role:search')")
    public Page<RoleVO> search(RoleDTO roleDTO, @PageableDefault Pageable pageable) {
        return roleService.search(roleDTO, pageable);
    }


    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:role:add')")
    public ModelAndView add() {
        return new ModelAndView("system/role/add");
    }

    /**
     * 保存角色
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:role:add')")
    public void create(@Valid RoleDTO roleDTO) {
        roleService.save(roleDTO);
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:role:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("role", roleService.getById(id));
        return new ModelAndView("system/role/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:role:edit')")
    public void edit(@Valid RoleDTO roleDTO) {
        roleService.update(roleDTO);
    }

    /**
     * 删除角色
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('system:role:remove')")
    public void remove(String ids) {
        roleService.remove(ids);
    }


    /**
     * 检查角色名称是否重复
     */
    @PostMapping("/checkRoleNameUnique")
    public boolean checkRoleNameUnique(String roleName, String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            return roleService.checkRoleNameUnique(roleName);
        } else {
            return roleService.checkRoleNameUnique(roleId, roleName);
        }
    }


}

