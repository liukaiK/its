package cn.com.goodlan.its.web.controller.event.approval;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.dto.EventDTO;
import cn.com.goodlan.its.pojo.vo.EventVO;
import cn.com.goodlan.its.service.event.approval.EventApprovalService;
import cn.com.goodlan.its.service.system.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private EventApprovalService eventApprovalService;

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('event:approval:view')")
    public ModelAndView approval() {
        return new ModelAndView("event/approval/approval");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('event:approval:search')")
    public Page<EventVO> search(EventDTO eventDTO, @PageableDefault Pageable pageable) {
        return eventApprovalService.search(eventDTO, pageable);
    }


    /**
     * 跳转到查看详情页面
     */
    @GetMapping("/detail/{id}")
    @PreAuthorize("hasAuthority('event:approval:detail')")
    public ModelAndView detail(@PathVariable String id, Model model) {
        model.addAttribute("event", eventApprovalService.getById(id));
        return new ModelAndView("event/approval/detail");
    }

    /**
     * 审批功能
     */
    @PostMapping("/approval/{id}")
    @PreAuthorize("hasAuthority('event:approval:approval')")
    public void approval(@PathVariable String id) {
        eventApprovalService.approval(id);
    }

    /**
     * 作废功能
     */
    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasAuthority('event:approval:cancel')")
    public void cancel(@PathVariable String id) {
        eventApprovalService.cancel(id);
    }

}
