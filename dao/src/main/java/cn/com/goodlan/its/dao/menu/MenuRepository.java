package cn.com.goodlan.its.dao.menu;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends CustomizeRepository<Menu, String> {

    @Query("select distinct m from Menu m left join m.roleList role left join role.userList user where user.id = ?1 and m.menuType in ?2")
    List<Menu> findByMenuTypeIn(String userId, List<String> menuTypeList);

}
