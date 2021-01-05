package cn.com.goodlan.its.dao.user;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.entity.User;

public interface UserRepository extends CustomizeRepository<User, String> {

    User getByUsername(String username);

}
