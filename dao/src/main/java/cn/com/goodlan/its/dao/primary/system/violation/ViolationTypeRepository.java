package cn.com.goodlan.its.dao.primary.system.violation;


import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.primary.ViolationType;

import java.util.List;

/**
 * description:
 *
 * @author: 王硕
 * @date: 2021/1/18-17:18
 */
public interface ViolationTypeRepository extends CustomizeRepository<ViolationType, String> {

    List<ViolationType> findAllByOrderByName();

}
