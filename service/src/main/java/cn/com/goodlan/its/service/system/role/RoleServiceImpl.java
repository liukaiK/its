package cn.com.goodlan.its.service.system.role;

import cn.com.goodlan.its.dao.role.RoleRepository;
import cn.com.goodlan.its.pojo.dto.RoleDTO;
import cn.com.goodlan.its.pojo.entity.Menu;
import cn.com.goodlan.its.pojo.entity.Role;
import cn.com.goodlan.its.pojo.vo.RoleVO;
import cn.com.goodlan.mapstruct.RoleMapper;
import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<RoleVO> search(Pageable pageable) {
        Page<Role> page = roleRepository.findAll(pageable);
        List<RoleVO> roleVOS = RoleMapper.INSTANCE.convertList(page.getContent());
        return new PageImpl<>(roleVOS, page.getPageable(), page.getTotalElements());
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
    public String checkRoleNameUnique(Role role) {
//        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
//        Role info = roleMapper.checkRoleNameUnique(role.getRoleName());
//        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
//            return UserConstants.ROLE_NAME_NOT_UNIQUE;
//        }
//        return UserConstants.ROLE_NAME_UNIQUE;
        return null;
    }

    @Override
    public void remove(String ids) {
        String[] roleIds = Convert.toStrArray(ids);
        for (String roleId : roleIds) {
            roleRepository.delete(new Role(roleId));
        }
    }

}
