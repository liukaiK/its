package cn.com.goodlan.its.dao.menu;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends CustomizeRepository<Menu, String> {

//    List<Menu> findByMenuTypeIn(List<String> menuTypeList);

    @Query("select distinct m from Menu m left join m.roleList role left join role.userList user where user.id = ?1 and m.menuType in ?2")
    List<Menu> findByMenuTypeIn(String userId, List<String> menuTypeList);

//    select distinct m.menu_id, m.parent_id, m.menu_name, m.url, m.visible, m.is_refresh, ifnull(m.perms,'') as perms, m.target, m.menu_type, m.icon, m.order_num, m.create_time
//    from sys_menu m
//    left join sys_role_menu rm on m.menu_id = rm.menu_id
//    left join sys_user_role ur on rm.role_id = ur.role_id
//    LEFT JOIN sys_role ro on ur.role_id = ro.role_id
//    where ur.user_id = #{userId} and m.menu_type in ('M', 'C') and m.visible = 0  AND ro.status = 0
//    order by m.parent_id, m.order_num
}
