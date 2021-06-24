package cn.com.goodlan.its.web.controller.system.score;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.dto.ScoreDTO;
import cn.com.goodlan.its.pojo.vo.ScoreVO;
import cn.com.goodlan.its.core.service.system.region.RegionService;
import cn.com.goodlan.its.core.service.system.score.ScoreService;
import cn.com.goodlan.its.core.service.system.violation.ViolationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * 扣分设置
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/system/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ViolationTypeService violationService;


    @GetMapping
    @PreAuthorize("hasAuthority('system:score:view')")
    public ModelAndView score(Model model) {
        model.addAttribute("regionList", regionService.findAll());
        return new ModelAndView("system/score/score");
    }


    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('system:score:search')")
    public Page<ScoreVO> search(ScoreDTO scoreDTO, @PageableDefault Pageable pageable) {
        return scoreService.search(scoreDTO, pageable);
    }


    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:score:add')")
    public ModelAndView add(Model model) {
        model.addAttribute("violation", violationService.findAll());
        model.addAttribute("region", regionService.findAll());
        return new ModelAndView("system/score/add");
    }

    /**
     * 新增保存
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('system:score:add')")
    public void add(@Valid ScoreDTO scoreDTO) {
        scoreService.save(scoreDTO);
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:score:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("score", scoreService.getById(id));
        model.addAttribute("violation", violationService.findAll());
        model.addAttribute("region", regionService.findAll());
        return new ModelAndView("system/score/edit");
    }


    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:score:edit')")
    public void edit(@Valid ScoreDTO scoreDTO) {
        scoreService.update(scoreDTO);
    }

    /**
     * 删除用户
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('system:score:remove')")
    public void remove(String ids) {
        scoreService.remove(ids);
    }


}
