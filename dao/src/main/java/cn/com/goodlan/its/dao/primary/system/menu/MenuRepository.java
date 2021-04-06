package cn.com.goodlan.its.dao.primary.system.menu;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.primary.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends CustomizeRepository<Menu, String> {

    @Query("select distinct m from Menu m left join m.roleList role left join role.userList user where user.id = ?1 and m.menuType in ?2 order by m.sort")
    List<Menu> findByMenuTypeIn(String userId, List<String> menuTypeList);


    @Query("from Menu m order by m.parent, m.sort")
    List<Menu> findByOrderByParent();

    @Query("from Menu m where m.name like ?1 order by m.parent, m.sort")
    List<Menu> findByName(String menuName);

    @Query("select concat(m.id,m.authority) from Menu m left join m.roleList role where role.id = ?1")
    List<String> findAllByRoleId(String roleId);

}
