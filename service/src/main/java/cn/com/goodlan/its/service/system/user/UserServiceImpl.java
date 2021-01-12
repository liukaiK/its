package cn.com.goodlan.its.service.system.user;


import cn.com.goodlan.its.dao.user.UserRepository;
import cn.com.goodlan.its.pojo.dto.UserDTO;
import cn.com.goodlan.its.pojo.entity.User;
import cn.com.goodlan.its.pojo.vo.UserVO;
import cn.com.goodlan.mapstruct.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<UserVO> search(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserVO> userVOS = UserMapper.INSTANCE.convertList(userPage.getContent());
        return new PageImpl<>(userVOS, userPage.getPageable(), userPage.getTotalElements());
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setSex(userDTO.getSex());
        userRepository.save(user);
    }

}
