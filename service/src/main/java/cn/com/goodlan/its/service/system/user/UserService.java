package cn.com.goodlan.its.service.system.user;

import cn.com.goodlan.its.pojo.dto.UpdateProfileDTO;
import cn.com.goodlan.its.pojo.dto.UserDTO;
import cn.com.goodlan.its.pojo.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserVO> search(UserDTO userDTO, Pageable pageable);

    void save(UserDTO userDTO);

    void update(UserDTO userDTO);

    void remove(String ids);

    UserVO getById(String id);

    void updateProfile(UpdateProfileDTO updateProfileDTO);

    /**
     * 检查账号是否已经存在
     *
     * @param username 账号
     * @return 是否存在
     */
    boolean checkUsernameUnique(String username);

    /**
     * 检查账号是否已经存在, 排除userId
     *
     * @param userId   用户ID
     * @param username 账号
     * @return 是否存在
     */
    boolean checkUsernameUnique(String userId, String username);

}
