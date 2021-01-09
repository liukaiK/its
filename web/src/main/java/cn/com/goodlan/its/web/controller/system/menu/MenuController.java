package cn.com.goodlan.its.web.controller.system.menu;

import cn.com.goodlan.its.pojo.entity.Role;
import cn.com.goodlan.its.pojo.vo.MenuVO;
import cn.com.goodlan.its.pojo.vo.Ztree;
import cn.com.goodlan.its.service.system.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        return new ModelAndView("system/menu/menu");
    }

    @PreAuthorize("hasAuthority('system:menu:search')")
    @PostMapping("/search")
    public List<MenuVO> search() {
        return menuService.findAll();
    }

    /**
     * 跳转到新增页面
     */
//    @GetMapping("/add/{parentId}")
//    public String add(@PathVariable("parentId") Long parentId, ModelMap modelMap) {
//        Menu menu = null;
//        if (0L != parentId) {
//            menu = menuService.selectMenuById(parentId);
//        } else {
//            menu = new Menu();
//            menu.setMenuId(0L);
//            menu.setMenuName("主目录");
//        }
//        modelMap.put("menu", menu);
//        return "system/menu/add";
//    }
    @GetMapping("/roleMenuTreeData")
    public List<Ztree> roleMenuTreeData(Role role) {
        List<Ztree> ztrees = menuService.roleMenuTreeData(role);
        return ztrees;
    }

}

