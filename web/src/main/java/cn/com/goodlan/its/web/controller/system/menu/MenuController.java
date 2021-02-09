package cn.com.goodlan.its.web.controller.system.menu;

import cn.com.goodlan.its.pojo.Result;
import cn.com.goodlan.its.pojo.dto.MenuDTO;
import cn.com.goodlan.its.pojo.vo.MenuVO;
import cn.com.goodlan.its.pojo.vo.Ztree;
import cn.com.goodlan.its.service.system.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
     * 查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:menu:search')")
    public List<MenuVO> search(MenuDTO menuDTO) {
        return menuService.search(menuDTO);
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add/{parentId}")
    @PreAuthorize("hasAuthority('system:menu:add')")
    public ModelAndView add(@PathVariable("parentId") String parentId, Model model) {
        String rootId = "0";
        MenuVO menuVO;
        if (!rootId.equals(parentId)) {
            menuVO = menuService.getMenuById(parentId);
        } else {
            menuVO = new MenuVO();
            menuVO.setId(rootId);
            menuVO.setName("主目录");
        }
        model.addAttribute("menu", menuVO);
        return new ModelAndView("system/menu/add");
    }

    /**
     * 新增保存
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:menu:add')")
    public Result add(@Valid MenuDTO menuDTO) {
        menuService.save(menuDTO);
        return Result.success();
    }


    /**
     * 修改保存
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:menu:edit')")
    public Result update(@Valid MenuDTO menuDTO) {
        menuService.update(menuDTO);
        return Result.success();
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:menu:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("menu", menuService.getMenuById(id));
        return new ModelAndView("system/menu/edit");
    }


    /**
     * 删除菜单
     */
    @GetMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('system:menu:remove')")
    public Result remove(@PathVariable String id) {
        menuService.deleteMenuById(id);
        return Result.success();
    }

    /**
     * 角色管理->分配菜单用的
     */
    @GetMapping("/roleMenuTreeData")
    public List<Ztree> roleMenuTreeData(String roleId) {
        return menuService.roleMenuTreeData(roleId);
    }


    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    public List<Ztree> menuTreeData() {
        return menuService.menuTreeData();
    }


    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public ModelAndView selectMenuTree(@PathVariable("menuId") String menuId, Model model) {
        String rootId = "0";
        if (rootId.equals(menuId)) {
            model.addAttribute("menu", new MenuVO());
        } else {
            model.addAttribute("menu", menuService.getMenuById(menuId));
        }
        return new ModelAndView("system/menu/tree");
    }


}

