package cn.com.goodlan.its.web.controller.event.approval;

import cn.com.goodlan.its.core.annotations.ResponseResultBody;
import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import cn.com.goodlan.its.core.service.event.approval.EventService;
import cn.com.goodlan.its.core.service.system.violation.ViolationTypeService;
import cn.com.goodlan.its.core.util.ExcelUtil;
import cn.com.goodlan.its.core.util.StringUtils;
import cn.com.goodlan.its.event.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/event/approval")
public class ApprovalController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private ViolationTypeService violationTypeService;

    @GetMapping
    @PreAuthorize("hasAuthority('event:approval:view')")
    public ModelAndView approval(Model model, String collageName, String startTime, String status) {
        if (StringUtils.isNotEmpty(collageName)) {
            model.addAttribute("collageName", collageName);
        }
        if (StringUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", startTime);
        }
        if (StringUtils.isNotEmpty(status)) {
            model.addAttribute("status", status);
        }
        model.addAttribute("violationTypeList", violationTypeService.findAll());
        return new ModelAndView("event/approval/approval");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('event:approval:search')")
    public Page<EventVO> search(EventDTO eventDTO, @PageableDefault Pageable pageable) {
        return eventService.search(eventDTO, pageable);
    }


    /**
     * 跳转到查看详情页面
     */
    @GetMapping("/detail/{id}")
    @PreAuthorize("hasAuthority('event:approval:detail')")
    public ModelAndView detail(@PathVariable String id, Model model) {
        model.addAttribute("event", eventService.getById(id));
        return new ModelAndView("event/approval/detail");
    }


    /**
     * 导出Excel
     */
    @PostMapping("/export")
    @PreAuthorize("hasAuthority('event:approval:export')")
    public Map<String, Object> export(EventDTO eventDTO) {
        Map<String, Object> map = new HashMap<>(1);
        List<EventVO> list = eventService.export(eventDTO);
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
        model.addAttribute("event", eventService.getById(id));
        return new ModelAndView("event/approval/approvaldetail");
    }


    /**
     * 审批功能
     */
    @PostMapping("/approval")
    @PreAuthorize("hasAuthority('event:approval:approval')")
    public void approval(String id) {
        approvalService.approval(id);
    }

    /**
     * 作废功能
     */
    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasAuthority('event:approval:cancel')")
    public void cancel(@PathVariable String id) {
        approvalService.cancel(id);
    }

}
