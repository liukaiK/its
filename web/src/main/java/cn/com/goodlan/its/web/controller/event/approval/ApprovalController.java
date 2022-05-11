package cn.com.goodlan.its.web.controller.event.approval;

import cn.com.goodlan.its.core.pojo.Result;
import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import cn.com.goodlan.its.core.service.event.approval.EventService;
import cn.com.goodlan.its.core.service.system.violation.ViolationTypeService;
import cn.com.goodlan.its.core.util.ExcelUtil;
import cn.com.goodlan.its.core.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 违规审批Controller
 *
 * @author liukai
 */
@RestController
@Api(tags = "违规审批")
@AllArgsConstructor
@RequestMapping("/event/approval")
public class ApprovalController {

    private EventService eventService;

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
    @ApiOperation("违规审批查询")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "type", value = "", paramType = "form"),
//            @ApiImplicitParam(name = "pType", value = "", paramType = "form"),
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "0", dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "size", value = "每页数据条数", defaultValue = "10", dataType = "int", paramType = "form"),
    })
    @PreAuthorize("hasAuthority('event:approval:search')")
    public Result<Page<EventVO>> search(EventDTO eventDTO, @ApiIgnore @PageableDefault Pageable pageable) {
        return Result.success(eventService.search(eventDTO, pageable));
    }


    /**
     * 删除数据
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('event:approval:remove')")
    public void remove(String ids) {
        eventService.remove(ids);
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

}
