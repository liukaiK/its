package cn.com.goodlan.its.dao.system.college;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.College;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollegeRepository extends CustomizeRepository<College, String> {

    @Query("from College c order by c.parent, c.sort")
    List<College> findByOrderByParent();

}
