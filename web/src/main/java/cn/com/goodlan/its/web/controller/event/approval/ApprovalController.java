package cn.com.goodlan.its.web.controller.event.approval;

import cn.com.goodlan.its.core.annotations.ResponseResultBody;
import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.query.EventQuery;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import cn.com.goodlan.its.core.service.system.violation.ViolationTypeService;
import cn.com.goodlan.its.core.util.ExcelUtil;
import cn.com.goodlan.its.core.util.StringUtils;
import cn.com.goodlan.its.event.WebEventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 违规审批Controller
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@AllArgsConstructor
@RequestMapping("/event/approval")
public class ApprovalController {

    private WebEventService webEventService;

    private ViolationTypeService violationTypeService;

    @GetMapping
    @PreAuthorize("hasAuthority('event:approval:view')")
    public ModelAndView approval(Model model, String collageName, String startTime) {
        if (StringUtils.isNotEmpty(collageName)) {
            model.addAttribute("collageName", collageName);
        }
        if (StringUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", startTime);
        }
        model.addAttribute("violationTypeList", violationTypeService.findAll());
        return new ModelAndView("event/approval/approval");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('event:approval:search')")
    public Page<EventVO> search(EventQuery eventQuery, @PageableDefault Pageable pageable) {
        return webEventService.search(eventQuery, pageable);
    }


    /**
     * 删除数据
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('event:approval:remove')")
    public void remove(String ids) {
        webEventService.remove(ids);
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('event:approval:add')")
    public ModelAndView add(Model model) {
        return new ModelAndView("event/approval/add");
    }

    /**
     * 新增违规数据
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('event:approval:add')")
    public void add(EventDTO eventDTO, Model model) {
        webEventService.create(eventDTO);
    }


    /**
     * 跳转到查看详情页面
     */
    @GetMapping("/detail/{id}")
    @PreAuthorize("hasAuthority('event:approval:detail')")
    public ModelAndView detail(@PathVariable String id, Model model) {
        model.addAttribute("event", webEventService.getById(id));
        return new ModelAndView("event/approval/detail");
    }


    /**
     * 导出Excel
     */
    @PostMapping("/export")
    @PreAuthorize("hasAuthority('event:approval:export')")
    public Map<String, Object> export(EventQuery eventQuery) {
        Map<String, Object> map = new HashMap<>(1);
        List<EventVO> list = webEventService.export(eventQuery);
        ExcelUtil<EventVO> util = new ExcelUtil<>(EventVO.class);
        String fileName = util.exportExcel(list, "审批数据");
        map.put("fileName", fileName);
        return map;
    }

    /**
     * 跳转到审批页面
     */
    @GetMapping("/approvaldetail/{id}")
    @PreAuthorize("hasAuthority('event:approval:approval')")
    public ModelAndView approval(@PathVariable String id, Model model) {
        model.addAttribute("event", webEventService.getById(id));
        return new ModelAndView("event/approval/approvaldetail");
    }


    @GetMapping("/vehicleNumberModal")
    @PreAuthorize("hasAuthority('event:approval:search')")
    public ModelAndView vehicleNumberModal(Model model, String vehicleNumber) {
        model.addAttribute("violationTypeList", violationTypeService.findAll());
        model.addAttribute("vehicleNumber", vehicleNumber);
        return new ModelAndView("event/approval/vehicleNumberModal");
    }


}
