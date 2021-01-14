package cn.com.goodlan.its.service.system.college;

import cn.com.goodlan.its.pojo.dto.CollegeDTO;
import cn.com.goodlan.its.pojo.vo.CollegeVO;
import cn.com.goodlan.its.pojo.vo.Ztree;

import java.util.List;

public interface CollegeService {

    List<CollegeVO> findAll();


    /**
     * 根据id查询一条记录
     */
    CollegeVO getCollegeById(String id);

    void save(CollegeDTO collegeDTO);

    void remove(String id);

    void update(CollegeDTO collegeDTO);

    List<Ztree> collegeTreeData();
}
