package cn.com.goodlan.its.web.controller.system.index;

import cn.com.goodlan.its.pojo.entity.primary.Menu;
import cn.com.goodlan.its.service.framework.ConfigService;
import cn.com.goodlan.its.service.system.index.IndexService;
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

    @Autowired
    private ConfigService configService;


    @GetMapping({"/index", "/"})
    public String index(Model model) {

        model.addAttribute("menus", findMenu());
        model.addAttribute("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        model.addAttribute("skinName", configService.selectConfigByKey("sys.index.skinName"));
        model.addAttribute("ignoreFooter", configService.selectConfigByKey("sys.index.ignoreFooter"));
        return "index";
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

