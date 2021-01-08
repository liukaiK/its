package cn.com.goodlan.its.service.index;

import cn.com.goodlan.its.pojo.entity.Menu;

import java.util.List;

public interface IndexService {

    /**
     * 查询当前登录人所拥有的菜单
     *
     * @author liukai
     */
    List<Menu> findMenuByCurrentUser();

}
