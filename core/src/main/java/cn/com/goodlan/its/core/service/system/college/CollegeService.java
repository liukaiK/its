package cn.com.goodlan.its.core.service.system.college;

import cn.com.goodlan.its.core.pojo.dto.CollegeDTO;
import cn.com.goodlan.its.core.pojo.vo.CollegeVO;
import cn.com.goodlan.its.core.pojo.vo.Ztree;

import java.util.List;

public interface CollegeService {

    List<CollegeVO> search(CollegeDTO collegeDTO);


    /**
     * 根据id查询一条记录
     */
    CollegeVO getCollegeById(String id);

    void save(CollegeDTO collegeDTO);

    void remove(String id);

    void update(CollegeDTO collegeDTO);

    List<Ztree> collegeTreeData();
}
