package cn.com.goodlan.its.core.service.system.college;

import cn.com.goodlan.its.core.exception.BusinessException;
import cn.com.goodlan.its.core.dao.primary.system.college.CollegeRepository;
import cn.com.goodlan.its.core.pojo.dto.CollegeDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.College;
import cn.com.goodlan.its.core.pojo.vo.CollegeVO;
import cn.com.goodlan.its.core.pojo.vo.Ztree;
import cn.com.goodlan.its.core.mapstruct.CollegeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(rollbackFor = Exception.class)
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public List<CollegeVO> search(CollegeDTO collegeDTO) {
        List<College> collegeList = collegeRepository.findAll((Specification<College>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(collegeDTO.getName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), collegeDTO.getName() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, Sort.by("parent", "sort"));
        return CollegeMapper.INSTANCE.convertList(collegeList);
    }

    @Override
    public CollegeVO getCollegeById(String id) {
        College college = collegeRepository.getById(id);
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
                throw new BusinessException("???????????????,???????????????");
            }
            if (college.get().hasUser()) {
                throw new BusinessException("?????????????????????,???????????????");
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
     * ??????????????????
     *
     * @param collegeList ????????????
     * @return ???????????????
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
