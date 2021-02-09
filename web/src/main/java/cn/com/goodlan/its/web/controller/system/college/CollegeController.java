package cn.com.goodlan.its.web.controller.system.college;

import cn.com.goodlan.its.pojo.Result;
import cn.com.goodlan.its.pojo.dto.CollegeDTO;
import cn.com.goodlan.its.pojo.vo.CollegeVO;
import cn.com.goodlan.its.pojo.vo.Ztree;
import cn.com.goodlan.its.service.system.college.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * 学院管理Controller
 *
 * @author liukai
 */
@RestController
@RequestMapping("/system/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @GetMapping
    @PreAuthorize("hasAuthority('system:college:view')")
    public ModelAndView college() {
        return new ModelAndView("system/college/college");
    }


    /**
     * 查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:college:search')")
    public List<CollegeVO> search(CollegeDTO collegeDTO) {
        return collegeService.search(collegeDTO);
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add/{parentId}")
    @PreAuthorize("hasAuthority('system:college:add')")
    public ModelAndView add(@PathVariable("parentId") String parentId, Model model) {
        String rootId = "0";
        CollegeVO collegeVO;
        if (!rootId.equals(parentId)) {
            collegeVO = collegeService.getCollegeById(parentId);
        } else {
            collegeVO = new CollegeVO();
            collegeVO.setId(rootId);
            collegeVO.setName("主目录");
        }
        model.addAttribute("college", collegeVO);
        return new ModelAndView("system/college/add");
    }

    /**
     * 新增保存
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:college:add')")
    public Result add(@Valid CollegeDTO collegeDTO) {
        collegeService.save(collegeDTO);
        return Result.success();
    }


    /**
     * 修改保存
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:college:edit')")
    public Result update(@Valid CollegeDTO collegeDTO) {
        collegeService.update(collegeDTO);
        return Result.success();
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:college:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("college", collegeService.getCollegeById(id));
        return new ModelAndView("system/college/edit");
    }


    /**
     * 删除学院
     */
    @GetMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('system:college:remove')")
    public Result remove(@PathVariable String id) {
        collegeService.remove(id);
        return Result.success();
    }

    /**
     * 加载所有学院列表树
     */
    @GetMapping("/collegeTreeData")
    public List<Ztree> collegeTreeData() {
        return collegeService.collegeTreeData();
    }


    /**
     * 选择学院树
     */
    @GetMapping("/selectCollegeTree/{collegeId}")
    public ModelAndView selectCollegeTree(@PathVariable("collegeId") String id, Model model) {
        String rootId = "0";
        if (rootId.equals(id)) {
            model.addAttribute("college", new CollegeVO());
        } else {
            model.addAttribute("college", collegeService.getCollegeById(id));
        }
        return new ModelAndView("system/college/tree");
    }


}

