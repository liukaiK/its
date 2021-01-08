package cn.com.goodlan.its.service.menu;

import cn.com.goodlan.its.dao.menu.MenuRepository;
import cn.com.goodlan.its.pojo.entity.Menu;
import cn.com.goodlan.its.pojo.vo.MenuVO;
import cn.com.goodlan.its.util.SecurityUtil;
import cn.com.goodlan.mapstruct.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<Menu> menuList = menuRepository.findAll();
        return MenuMapper.INSTANCE.convertList(menuList);
    }


}
