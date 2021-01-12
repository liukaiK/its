package cn.com.goodlan.its.service.system.role;

import cn.com.goodlan.its.pojo.dto.RoleDTO;
import cn.com.goodlan.its.pojo.entity.Role;
import cn.com.goodlan.its.pojo.vo.RoleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    Page<RoleVO> search(Pageable pageable);

    RoleVO getById(String roleId);

    void update(RoleDTO roleDTO);

    /**
     * 保存角色
     */
    void save(RoleDTO roleDTO);

    /**
     * 角色下拉框
     */
    List<RoleVO> selectRoleAll();


    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    String checkRoleNameUnique(Role role);

    /**
     * 删除角色
     */
    void remove(String ids);
}
