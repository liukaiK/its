package cn.com.goodlan.its.web.controller.event.approval;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.dto.EventDTO;
import cn.com.goodlan.its.pojo.dto.UserDTO;
import cn.com.goodlan.its.pojo.vo.EventVO;
import cn.com.goodlan.its.service.event.EventApprovalService;
import cn.com.goodlan.its.service.system.role.RoleService;
import cn.com.goodlan.its.service.system.user.UserService;
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

    @Autowired
    private RoleService roleService;

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
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('system:user:add')")
    public ModelAndView add(Model model) {
        model.addAttribute("roles", roleService.selectRoleAll());
        return new ModelAndView("system/user/add");
    }

    /**
     * 新增用户
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('system:user:add')")
    public void add(@Valid UserDTO userDTO) {
        userService.save(userDTO);
    }


    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.selectRoleByUser(id));
        return new ModelAndView("system/user/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public void edit(@Valid UserDTO userDTO) {
        userService.update(userDTO);
    }

    /**
     * 删除用户
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('system:user:remove')")
    public void remove(String ids) {
        userService.remove(ids);
    }

}
