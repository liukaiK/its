package cn.com.goodlan.its.service.system.user;


import cn.com.goodlan.its.common.exception.BusinessException;
import cn.com.goodlan.its.common.util.AESUtil;
import cn.com.goodlan.its.dao.system.user.UserRepository;
import cn.com.goodlan.its.pojo.dto.ChangePasswordDTO;
import cn.com.goodlan.its.pojo.dto.ResetPasswordDTO;
import cn.com.goodlan.its.pojo.dto.UpdateProfileDTO;
import cn.com.goodlan.its.pojo.dto.UserDTO;
import cn.com.goodlan.its.pojo.entity.Role;
import cn.com.goodlan.its.pojo.entity.User;
import cn.com.goodlan.its.pojo.vo.UserVO;
import cn.com.goodlan.its.util.SecurityUtil;
import cn.com.goodlan.mapstruct.UserMapper;
import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<UserVO> search(UserDTO userDTO, Pageable pageable) {
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(userDTO.getName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), userDTO.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(userDTO.getUsername())) {
                list.add(criteriaBuilder.like(root.get("username").as(String.class), userDTO.getUsername() + "%"));
            }
            if (StringUtils.isNotEmpty(userDTO.getPhoneNumber())) {
                list.add(criteriaBuilder.equal(root.get("phoneNumber").as(String.class), aesEncrypt(userDTO.getPhoneNumber())));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Page<User> page = userRepository.findAll(specification, pageable);
        List<UserVO> list = UserMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(aesEncrypt(userDTO.getPhoneNumber()));
        user.setSex(userDTO.getSex());
        user.setRemark(userDTO.getRemark());
        user.addCollege(userDTO.getCollegeId());
        String[] roleIds = Convert.toStrArray(userDTO.getRoleIds());
        for (String roleId : roleIds) {
            user.addRole(new Role(roleId));
        }
        userRepository.save(user);
    }

    @Override
    public void update(UserDTO userDTO) {
        User user = userRepository.getOne(userDTO.getId());
        user.setRemark(userDTO.getRemark());
        user.setSex(userDTO.getSex());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(aesEncrypt(userDTO.getPhoneNumber()));
        user.addCollege(userDTO.getCollegeId());
        String[] roleIds = Convert.toStrArray(userDTO.getRoleIds());
        user.removeAllRole();
        for (String roleId : roleIds) {
            user.addRole(new Role(roleId));
        }
        userRepository.save(user);
    }

    @Override
    public void remove(String ids) {
        String[] userIds = Convert.toStrArray(ids);
        for (String userId : userIds) {
//            checkUserAllowed(new User(userId));
            userRepository.delete(new User(userId));
        }
        // 删除用户与角色关联
//        userRoleMapper.deleteUserRole(userIds);
        // 删除用户与岗位关联
//        userPostMapper.deleteUserPost(userIds);
//        return userMapper.deleteUserByIds(userIds);

//        userRepository.deleteAll(Arrays.);


    }

    @Override
    public UserVO getById(String id) {
        User user = userRepository.getOne(id);
        return UserMapper.INSTANCE.convert(user);
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        User user = userRepository.getOne(resetPasswordDTO.getId());
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateProfile(UpdateProfileDTO updateProfileDTO) {
        User user = userRepository.getOne(SecurityUtil.getUserId());
        user.setEmail(updateProfileDTO.getEmail());
        user.setSex(updateProfileDTO.getSex());
        user.setPhoneNumber(aesEncrypt(updateProfileDTO.getPhoneNumber()));
        userRepository.save(user);
    }


    /**
     * AES加密
     */
    private String aesEncrypt(String str) {
        return AESUtil.encrypt(str);
    }

    @Override
    public boolean checkUsernameUnique(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean checkUsernameUnique(String userId, String username) {
        return userRepository.existsByUsernameAndIdNot(username, userId);
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = userRepository.getOne(SecurityUtil.getUserId());
        if (passwordIsError(changePasswordDTO, user)) {
            throw new BusinessException("原密码错误!");
        }
        if (!StringUtils.equals(changePasswordDTO.getNewPassword(), changePasswordDTO.getConfirmPassword())) {
            throw new BusinessException("确认密码与新密码不一致!");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    private boolean passwordIsError(ChangePasswordDTO changePasswordDTO, User user) {
        return !passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword());
    }

}
