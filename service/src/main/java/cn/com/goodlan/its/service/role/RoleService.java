package cn.com.goodlan.its.service.role;

import cn.com.goodlan.its.pojo.vo.RoleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    Page<RoleVO> search(Pageable pageable);


}
