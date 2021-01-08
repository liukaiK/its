package cn.com.goodlan.its.web.controller.menu;

import cn.com.goodlan.its.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 菜单管理Controller
 *
 * @author liukai
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;

    @PreAuthorize("hasAuthority('system:menu:view')")
    @GetMapping
    public ModelAndView menu() {
        return new ModelAndView("/system/menu/menu");
    }

    @PreAuthorize("hasAuthority('system:menu:list')")
    @GetMapping("/search")
    public void search() {

    }

}
