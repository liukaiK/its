package cn.com.goodlan.its.service.system.user;

import cn.com.goodlan.its.pojo.dto.UserDTO;
import cn.com.goodlan.its.pojo.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserVO> search(Pageable pageable);

    void save(UserDTO userDTO);

}
