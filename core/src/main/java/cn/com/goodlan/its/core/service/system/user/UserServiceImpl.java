package cn.com.goodlan.its.core.service.system.user;


import cn.com.goodlan.its.core.dao.primary.system.user.UserRepository;
import cn.com.goodlan.its.core.exception.BusinessException;
import cn.com.goodlan.its.core.mapstruct.UserMapper;
import cn.com.goodlan.its.core.pojo.dto.ChangePasswordDTO;
import cn.com.goodlan.its.core.pojo.dto.ResetPasswordDTO;
import cn.com.goodlan.its.core.pojo.dto.UpdateProfileDTO;
import cn.com.goodlan.its.core.pojo.dto.UserDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Role;
import cn.com.goodlan.its.core.pojo.entity.primary.user.User;
import cn.com.goodlan.its.core.pojo.entity.primary.user.Username;
import cn.com.goodlan.its.core.pojo.vo.UserVO;
import cn.com.goodlan.its.core.util.AESUtil;
import cn.com.goodlan.its.core.util.SecurityUtil;
import cn.hutool.core.convert.Convert;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liukai
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserVO> search(UserDTO userDTO, Pageable pageable) {
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (!query.getResultType().equals(Long.class)) {
                root.fetch("college", JoinType.LEFT);
            }

            if (StringUtils.isNotEmpty(userDTO.getName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), userDTO.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(userDTO.getUsername())) {
                list.add(criteriaBuilder.like(root.get("username").get("username").as(String.class), userDTO.getUsername() + "%"));
            }
            if (StringUtils.isNotEmpty(userDTO.getPhoneNumber())) {
                list.add(criteriaBuilder.equal(root.get("phoneNumber").as(String.class), AESUtil.encrypt(userDTO.getPhoneNumber())));
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
        user.updateName(userDTO.getName());
        user.updateEmail(userDTO.getEmail());
        user.updateUsername(new Username(userDTO.getUsername()));
        user.updatePhoneNumber(userDTO.getPhoneNumber());
        user.updateSex(userDTO.getSex());
        user.updateRemark(userDTO.getRemark());
        user.updatePassword(passwordEncoder.encode(userDTO.getPassword()));
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
        user.updateRemark(userDTO.getRemark());
        user.updateSex(userDTO.getSex());
        user.updateEmail(userDTO.getEmail());
        user.updatePhoneNumber(userDTO.getPhoneNumber());
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
            User user = new User(userId);
            if (user.isAdmin()) {
                throw new BusinessException("超级管理员不能被删除");
            }
            userRepository.delete(user);
        }
    }

    @Override
    public UserVO getById(String id) {
        User user = userRepository.getOne(id);
        return UserMapper.INSTANCE.convert(user);
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        User user = userRepository.getOne(resetPasswordDTO.getId());
        user.updatePassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateProfile(UpdateProfileDTO updateProfileDTO) {
        User user = userRepository.getOne(SecurityUtil.getUserId());
        user.updateEmail(updateProfileDTO.getEmail());
        user.updateSex(updateProfileDTO.getSex());
        user.updatePhoneNumber(updateProfileDTO.getPhoneNumber());
        userRepository.save(user);
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
