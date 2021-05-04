package cn.com.goodlan.its.web.controller.event.statistics;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.common.util.ExcelUtil;
import cn.com.goodlan.its.pojo.dto.EventDTO;
import cn.com.goodlan.its.pojo.entity.primary.Event;
import cn.com.goodlan.its.pojo.vo.EventVO;
import cn.com.goodlan.its.pojo.vo.StatisticsExcel;
import cn.com.goodlan.its.service.event.approval.EventApprovalService;
import cn.com.goodlan.its.service.event.statistics.StatisticsService;
import cn.com.goodlan.its.service.system.violation.ViolationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 违规数据统计Controller
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/event/statistics")
public class StatisticsController {


    @Autowired
    private EventApprovalService eventApprovalService;

    @Autowired
    private ViolationTypeService violationTypeService;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    @PreAuthorize("hasAuthority('event:statistics:view')")
    public ModelAndView statistics(Model model) {
        model.addAttribute("violationTypeList", violationTypeService.findAll());
        return new ModelAndView("event/statistics/statistics");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('event:statistics:search')")
    public Page<EventVO> search(EventDTO eventDTO, @PageableDefault Pageable pageable) {
        eventDTO.setStatus(Event.Status.APPROVAL);
        return eventApprovalService.search(eventDTO, pageable);
    }

    /**
     * 导出Excel
     */
    @PostMapping("/export")
    @PreAuthorize("hasAuthority('event:statistics:export')")
    public Map<String, Object> export(EventDTO eventDTO) {
        Map<String, Object> map = new HashMap<>(1);
        List<StatisticsExcel> list = statisticsService.export(eventDTO);
        ExcelUtil<StatisticsExcel> util = new ExcelUtil<>(StatisticsExcel.class);
        String fileName = util.exportExcel(list, "违规统计");
        map.put("fileName", fileName);
        return map;
    }

}
