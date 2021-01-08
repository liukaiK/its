package cn.com.goodlan.its.service.menu;

import cn.com.goodlan.its.pojo.entity.Menu;
import cn.com.goodlan.its.pojo.vo.MenuVO;

import java.util.List;

public interface MenuService {


    /**
     * 查询当前登录人所拥有的菜单
     *
     * @author liukai
     */
    List<Menu> findMenuByCurrentUser();

    /**
     * 根据userId 查询所拥有的菜单
     *
     * @param userId 用户ID
     * @author liukai
     */
    List<Menu> findMenuByUser(String userId);


    List<MenuVO> findAll();

}
