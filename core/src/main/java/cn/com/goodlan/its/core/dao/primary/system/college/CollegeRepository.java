package cn.com.goodlan.its.core.dao.primary.system.college;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.College;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollegeRepository extends CustomizeRepository<College, String> {

    @Query("from College c order by c.parent, c.sort")
    List<College> findByOrderByParent();

}
