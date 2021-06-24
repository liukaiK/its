package cn.com.goodlan.its.core.dao.primary.system.role;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends CustomizeRepository<Role, String> {


    @Query(value = "from Role r left join r.userList u where u.id = ?1")
    List<Role> findByUserList(String userId);

    boolean existsByName(String roleName);

    boolean existsByNameAndIdNot(String roleName, String roleId);

}
