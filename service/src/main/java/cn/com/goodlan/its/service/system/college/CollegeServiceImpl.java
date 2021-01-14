package cn.com.goodlan.its.service.system.college;

import cn.com.goodlan.its.common.exception.BusinessException;
import cn.com.goodlan.its.dao.college.CollegeRepository;
import cn.com.goodlan.its.pojo.dto.CollegeDTO;
import cn.com.goodlan.its.pojo.entity.College;
import cn.com.goodlan.its.pojo.vo.CollegeVO;
import cn.com.goodlan.its.pojo.vo.Ztree;
import cn.com.goodlan.mapstruct.CollegeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(rollbackFor = Exception.class)
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public List<CollegeVO> findAll() {
        List<College> collegeList = collegeRepository.findByOrderByParent();
        return CollegeMapper.INSTANCE.convertList(collegeList);
    }

    @Override
    public CollegeVO getCollegeById(String id) {
        College college = collegeRepository.getOne(id);
        return CollegeMapper.INSTANCE.convert(college);
    }

    @Override
    public void save(CollegeDTO collegeDTO) {
        College college = new College();
        college.setName(collegeDTO.getName());
        if (!"0".equals(collegeDTO.getParentId())) {
            college.addParent(collegeDTO.getParentId());
        }
        collegeRepository.save(college);
    }

    @Override
    public void remove(String id) {
        Optional<College> college = collegeRepository.findById(id);
        if (college.isPresent()) {
            if (college.get().hasChildren()) {
                throw new BusinessException("存在子学院,不允许删除");
            }
            if (college.get().hasUser()) {
                throw new BusinessException("此学院存在用户,不允许删除");
            }
            collegeRepository.deleteById(id);
        }
    }

    @Override
    public void update(CollegeDTO collegeDTO) {
        Optional<College> college = collegeRepository.findById(collegeDTO.getId());
        if (college.isPresent()) {
            College collegeFormDatabase = college.get();
            collegeFormDatabase.setName(collegeDTO.getName());
            collegeFormDatabase.setSort(collegeDTO.getSort());
            if (StringUtils.isNotEmpty(collegeDTO.getParentId())) {
                collegeFormDatabase.addParent(collegeDTO.getParentId());
            }
        }
    }

    @Override
    public List<Ztree> collegeTreeData() {
        List<College> collegeList = collegeRepository.findAll();
        return initZtree(collegeList);
    }

    /**
     * 对象转菜单树
     *
     * @param collegeList 学院列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<College> collegeList) {
        List<Ztree> ztrees = new ArrayList<>();
        for (College college : collegeList) {
            Ztree ztree = new Ztree();
            ztree.setId(college.getId());
            if (college.getParent() != null) {
                ztree.setParentId(college.getParent().getId());
            }
            ztree.setName(college.getName());
            ztree.setTitle(college.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }


}
