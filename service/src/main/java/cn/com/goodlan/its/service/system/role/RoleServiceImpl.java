package cn.com.goodlan.its.service.system.role;

import cn.com.goodlan.its.common.exception.BusinessException;
import cn.com.goodlan.its.dao.system.role.RoleRepository;
import cn.com.goodlan.its.pojo.dto.RoleDTO;
import cn.com.goodlan.its.pojo.entity.Menu;
import cn.com.goodlan.its.pojo.entity.Role;
import cn.com.goodlan.its.pojo.vo.RoleVO;
import cn.com.goodlan.mapstruct.RoleMapper;
import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
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
        Role role = roleRepository.getOne(roleId);
        return RoleMapper.INSTANCE.convert(role);
    }

    @Override
    public void update(RoleDTO roleDTO) {
        String[] menuIds = Convert.toStrArray(roleDTO.getMenuIds());
        Role role = roleRepository.getOne(roleDTO.getId());
        role.removeAllMenu();
        for (String menuId : menuIds) {
            role.addMenu(new Menu(menuId));
        }
        role.setName(roleDTO.getRoleName());
        role.setRemark(roleDTO.getRemark());
        roleRepository.save(role);
    }

    @Override
    public void save(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getRoleName());
        String[] menuIds = Convert.toStrArray(roleDTO.getMenuIds());
        for (String menuId : menuIds) {
            role.addMenu(new Menu(menuId));
        }
        role.setRemark(roleDTO.getRemark());
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
            Role role = roleRepository.getOne(roleId);
            if (role.hasUser()) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getName()));
            }
            roleRepository.delete(role);
        }
    }

}
