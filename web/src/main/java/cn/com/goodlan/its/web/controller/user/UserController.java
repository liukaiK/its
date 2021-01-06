package cn.com.goodlan.its.web.controller.user;

import cn.com.goodlan.its.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 用户管理Controller
 *
 * @author liukai
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('user:create')")
    @PostMapping("/")
    public void save() {

    }

}
