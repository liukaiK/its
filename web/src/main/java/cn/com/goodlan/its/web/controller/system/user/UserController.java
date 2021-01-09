package cn.com.goodlan.its.web.controller.system.user;

import cn.com.goodlan.its.service.system.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理Controller
 *
 * @author liukai
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('system:user:create')")
    @GetMapping("/save")
    public void save() {

    }

}
