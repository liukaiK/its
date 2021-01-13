package cn.com.goodlan.its.web.controller.system.menu;

import cn.com.goodlan.its.pojo.entity.Menu;
import cn.com.goodlan.its.pojo.vo.MenuVO;
import cn.com.goodlan.its.pojo.vo.Ztree;
import cn.com.goodlan.its.service.system.menu.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    @PreAuthorize("hasAuthority('system:menu:view')")
    public ModelAndView menu() {
        return new ModelAndView("system/menu/menu");
    }


    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:menu:search')")
    public List<MenuVO> search() {
        return menuService.findAll();
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add/{parentId}")
    @PreAuthorize("hasAuthority('system:menu:add')")
    public ModelAndView add(@PathVariable("parentId") String parentId, Model model) {
        Menu menu;
        if (StringUtils.isEmpty(parentId)) {
//            menu = menuService.selectMenuById(parentId);
        } else {
            menu = new Menu();
            menu.setId(null);
            menu.setName("主目录");
        }
//        model.addAttribute("menu", menu);
        return new ModelAndView("system/menu/add");
    }

    /**
     * 角色管理->分配菜单用的
     */
    @GetMapping("/roleMenuTreeData")
    public List<Ztree> roleMenuTreeData(String roleId) {
        return menuService.roleMenuTreeData(roleId);
    }

}

