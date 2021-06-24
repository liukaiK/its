package cn.com.goodlan.its.core.service.system.index;

import cn.com.goodlan.its.pojo.entity.primary.Menu;

import java.util.List;

public interface IndexService {

    /**
     * 查询当前登录人所拥有的菜单
     *
     * @author liukai
     */
    List<Menu> findMenuByCurrentUser();

}
