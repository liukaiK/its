package cn.com.goodlan.its.service.menu;

import cn.com.goodlan.its.pojo.entity.Menu;
import cn.com.goodlan.its.util.SecurityUtil;
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


    @Override
    public List<Menu> findMenuByCurrentUser() {
        return findMenuByUser(SecurityUtil.getUserId());
    }

    @Override
    public List<Menu> findMenuByUser(String userId) {
        return null;
    }


}
