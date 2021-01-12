package cn.com.goodlan.its.service.system.menu;

import cn.com.goodlan.its.dao.menu.MenuRepository;
import cn.com.goodlan.its.pojo.entity.Menu;
import cn.com.goodlan.its.pojo.vo.MenuVO;
import cn.com.goodlan.its.pojo.vo.Ztree;
import cn.com.goodlan.its.util.SecurityUtil;
import cn.com.goodlan.mapstruct.MenuMapper;
import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单Service
 *
 * @author liukai
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public List<Menu> findMenuByCurrentUser() {
        return findMenuByUser(SecurityUtil.getUserId());
    }

    @Override
    public List<Menu> findMenuByUser(String userId) {
        return null;
    }

    @Override
    public List<MenuVO> findAll() {
        List<Menu> menuList = menuRepository.findByOrderByParent();
        return MenuMapper.INSTANCE.convertList(menuList);
    }

    @Override
    public List<Ztree> roleMenuTreeData(String roleId) {
        List<Menu> menuList = menuRepository.findAll();
        if (StringUtils.isNotEmpty(roleId)) {
            List<String> menus = menuRepository.findAllByRoleId(roleId);
            return initZtree(menuList, menus, true);
        } else {
            return initZtree(menuList, null, true);
        }
    }

    /**
     * 对象转菜单树
     *
     * @param menuList     菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag    是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Menu> menuList, List<String> roleMenuList, boolean permsFlag) {
        List<Ztree> ztrees = new ArrayList<>();
        boolean isCheck = CollectionUtil.isNotEmpty(roleMenuList);
        for (Menu menu : menuList) {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getId());
            if (menu.getParent() != null) {
                ztree.setParentId(menu.getParent().getId());
            }
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getName());
            if (isCheck) {
                ztree.setChecked(roleMenuList.contains(menu.getId() + menu.getAuthority()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(Menu menu, boolean permsFlag) {
        StringBuilder sb = new StringBuilder();
        sb.append(menu.getName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;").append(menu.getAuthority()).append("</font>");
        }
        return sb.toString();
    }

}
