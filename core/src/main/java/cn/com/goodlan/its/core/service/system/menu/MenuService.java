package cn.com.goodlan.its.core.service.system.menu;

import cn.com.goodlan.its.pojo.dto.MenuDTO;
import cn.com.goodlan.its.pojo.vo.MenuVO;
import cn.com.goodlan.its.pojo.vo.Ztree;

import java.util.List;

public interface MenuService {


    List<MenuVO> search(MenuDTO menuDTO);

    /**
     * 根据id查询一条记录
     */
    MenuVO getMenuById(String id);

    List<Ztree> roleMenuTreeData(String roleId);

    /**
     * 查询所有菜单信息
     *
     * @return 菜单列表
     */
    List<Ztree> menuTreeData();

    void save(MenuDTO menuDTO);

    void update(MenuDTO menuDTO);


    void deleteMenuById(String menuId);

}
