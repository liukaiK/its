package cn.com.goodlan.its.web.controller.system.role;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.dto.RoleDTO;
import cn.com.goodlan.its.pojo.entity.Role;
import cn.com.goodlan.its.pojo.vo.RoleVO;
import cn.com.goodlan.its.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ModelAndView role() {
        return new ModelAndView("system/role/role");
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("system/role/add");
    }

    /**
     * 保存角色
     */
    @PostMapping("/create")
    public void create(@Valid RoleDTO roleDTO) {
        roleService.save(roleDTO);
    }

    @PostMapping("/search")
    public Page<RoleVO> search(@PageableDefault Pageable pageable) {
        return roleService.search(pageable);
    }


    /**
     * 检查角色名称是否重复
     */
    @PostMapping("/checkRoleNameUnique")
    public String checkRoleNameUnique(Role role) {
        return "";
    }

}

