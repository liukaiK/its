package cn.com.goodlan.its.core.service.system.role;

import cn.com.goodlan.its.core.dao.primary.system.role.RoleRepository;
import cn.com.goodlan.its.core.exception.BusinessException;
import cn.com.goodlan.its.core.mapstruct.RoleMapper;
import cn.com.goodlan.its.core.pojo.dto.RoleDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Menu;
import cn.com.goodlan.its.core.pojo.entity.primary.Role;
import cn.com.goodlan.its.core.pojo.vo.RoleVO;
import cn.hutool.core.convert.Convert;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Page<RoleVO> search(RoleDTO roleDTO, Pageable pageable) {
        Specification<Role> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(roleDTO.getRoleName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), roleDTO.getRoleName() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
        Page<Role> page = roleRepository.findAll(specification, pageable);
        List<RoleVO> list = RoleMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public RoleVO getById(String roleId) {
        Role role = roleRepository.getById(roleId);
        return RoleMapper.INSTANCE.convert(role);
    }

    @Override
    public void update(RoleDTO roleDTO) {
        String[] menuIds = Convert.toStrArray(roleDTO.getMenuIds());
        Role role = roleRepository.getById(roleDTO.getId());
        role.updateName(roleDTO.getRoleName());
        role.updateRemark(roleDTO.getRemark());
        role.removeAllMenu();
        for (String menuId : menuIds) {
            role.addMenu(new Menu(menuId));
        }
        roleRepository.save(role);
    }

    @Override
    public void save(RoleDTO roleDTO) {
        Role role = new Role();
        role.updateName(roleDTO.getRoleName());
        role.updateRemark(roleDTO.getRemark());
        String[] menuIds = Convert.toStrArray(roleDTO.getMenuIds());
        for (String menuId : menuIds) {
            role.addMenu(new Menu(menuId));
        }
        roleRepository.save(role);
    }

    @Override
    public List<RoleVO> selectRoleAll() {
        List<Role> roleList = roleRepository.findAll();
        return RoleMapper.INSTANCE.convertList(roleList);
    }

    @Override
    public List<RoleVO> selectRoleByUser(String userId) {
        List<RoleVO> roleVOList = selectRoleAll();
        List<Role> roleList = roleRepository.findByUserList(userId);
        for (RoleVO roleVO : roleVOList) {
            for (Role role : roleList) {
                if (roleVO.getId().equals(role.getId())) {
                    roleVO.setCheck(true);
                }
            }
        }
        return roleVOList;
    }

    @Override
    public boolean checkRoleNameUnique(String roleName) {
        return roleRepository.existsByName(roleName);
    }

    @Override
    public boolean checkRoleNameUnique(String roleId, String roleName) {
        return roleRepository.existsByNameAndIdNot(roleName, roleId);
    }


    @Override
    public void remove(String ids) {
        String[] roleIds = Convert.toStrArray(ids);
        for (String roleId : roleIds) {
            Role role = roleRepository.getById(roleId);
            if (role.hasUser()) {
                throw new BusinessException(String.format("%1$s?????????,????????????", role.getName()));
            }
            roleRepository.delete(role);
        }
    }

}
