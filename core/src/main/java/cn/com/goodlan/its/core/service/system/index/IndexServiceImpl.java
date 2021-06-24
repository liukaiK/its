package cn.com.goodlan.its.core.service.system.index;

import cn.com.goodlan.its.dao.primary.system.menu.MenuRepository;
import cn.com.goodlan.its.pojo.entity.primary.Menu;
import cn.com.goodlan.its.core.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author liukai
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IndexServiceImpl implements IndexService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findMenuByCurrentUser() {
        return menuRepository.findByMenuTypeIn(SecurityUtil.getUserId(), Arrays.asList(Menu.M, Menu.C));
    }


}
