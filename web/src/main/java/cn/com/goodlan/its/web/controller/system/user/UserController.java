package cn.com.goodlan.its.web.controller.system.user;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.vo.UserVO;
import cn.com.goodlan.its.service.system.role.RoleService;
import cn.com.goodlan.its.service.system.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户管理Controller
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('system:user:create')")
    @GetMapping("/save")
    public void save() {

    }

    @GetMapping
    public ModelAndView user() {
        return new ModelAndView("system/user/user");
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("roles", roleService.selectRoleAll());
        return new ModelAndView("system/user/add");
    }


    @PostMapping("/search")
    public Page<UserVO> search(@PageableDefault Pageable pageable) {
        return userService.search(pageable);
    }

}
