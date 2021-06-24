package cn.com.goodlan.its.core.dao.primary.system.user;


import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author liukai
 */
public interface UserRepository extends CustomizeRepository<User, String> {

    Optional<User> getByUsername(String username);

    @Modifying
    @Transactional
    @Query("update User set lastLoginTime = ?1 where id = ?2")
    void updateLastLoginTime(LocalDateTime localDateTime, String userId);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, String userId);


}
