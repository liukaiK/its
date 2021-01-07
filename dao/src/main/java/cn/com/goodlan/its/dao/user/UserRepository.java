package cn.com.goodlan.its.dao.user;


import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

/**
 * @author liukai
 */
public interface UserRepository extends CustomizeRepository<User, String> {

    User getByUsername(String username);

    @Modifying
    @Query("update User set lastLoginTime = ?1 where id = ?2")
    User updateLastLoginTime(LocalDateTime localDateTime, String userId);

}
