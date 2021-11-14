package cn.com.goodlan.its.web.controller.event.academy;

import cn.com.goodlan.its.core.annotations.ResponseResultBody;
import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.dto.RecordDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.AcademyStatistics;
import cn.com.goodlan.its.core.pojo.entity.primary.Event;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import cn.com.goodlan.its.core.pojo.vo.StatisticsExcel;
import cn.com.goodlan.its.core.service.event.academy.AcademyStatisticsService;
import cn.com.goodlan.its.core.service.event.approval.EventApprovalService;
import cn.com.goodlan.its.core.service.event.statistics.StatisticsService;
import cn.com.goodlan.its.core.service.system.violation.ViolationTypeService;
import cn.com.goodlan.its.core.util.ExcelUtil;
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
@RequestMapping("/event/academy")
public class AcademyStatisticsController {

    @Autowired
    private ViolationTypeService violationTypeService;

    @Autowired
    private AcademyStatisticsService statisticsService;

    @GetMapping
    @PreAuthorize("hasAuthority('event:academy:view')")
    public ModelAndView statistics(Model model) {
        model.addAttribute("violationTypeList", violationTypeService.findAll());
        return new ModelAndView("event/academy/academy-statistics");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('event:academy:search')")
    public Page<AcademyStatistics> search(RecordDTO recordDTO, @PageableDefault Pageable pageable) {
        return statisticsService.academyStatistics(recordDTO, pageable);
    }

    /**
     * 导出Excel
     */
    @PostMapping("/export")
    @PreAuthorize("hasAuthority('event:academy:export')")
    public Map<String, Object> export(EventDTO eventDTO) {
        Map<String, Object> map = new HashMap<>(1);
        List<StatisticsExcel> list = statisticsService.export(eventDTO);
        ExcelUtil<StatisticsExcel> util = new ExcelUtil<>(StatisticsExcel.class);
        String fileName = util.exportExcel(list, "违规统计");
        map.put("fileName", fileName);
        return map;
    }

}
