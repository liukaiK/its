package cn.com.goodlan.its.web.controller.index;

import cn.com.goodlan.its.common.constant.SystemConstant;
import cn.com.goodlan.its.pojo.entity.Menu;
import cn.com.goodlan.its.service.index.IndexService;
import cn.com.goodlan.its.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * @author liukai
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;


    @GetMapping({"/index", "/"})
    public String index(Model model) {

        model.addAttribute("menus", findMenu());

        return SystemConstant.PAGE + "/index";
    }

    /**
     * 查询当前登录人拥有的菜单
     *
     * @author liukai
     */
    private List<Menu> findMenu() {
        List<Menu> menuList = indexService.findMenuByCurrentUser();
        menuList.forEach(menu -> menu.setChildren(null));
        return TreeUtils.getChildPerms(menuList);
    }

}

