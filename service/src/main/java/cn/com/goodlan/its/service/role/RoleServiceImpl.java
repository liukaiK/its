package cn.com.goodlan.its.service.role;

import cn.com.goodlan.its.dao.role.RoleRepository;
import cn.com.goodlan.its.pojo.entity.Role;
import cn.com.goodlan.its.pojo.vo.RoleVO;
import cn.com.goodlan.mapstruct.RoleMapper;
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

}
