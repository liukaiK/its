package cn.com.goodlan.its.web.controller.system.park.parklot;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.pojo.dto.ParkLotDTO;
import cn.com.goodlan.its.pojo.vo.ParkLotVO;
import cn.com.goodlan.its.service.system.park.parklot.ParkLotService;
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
 * 停车场管理Controller
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/park/parklot")
public class ParkLotController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ParkLotService parkLotService;

    @GetMapping
    @PreAuthorize("hasAuthority('park:parklot:view')")
    public ModelAndView parkLot() {
        return new ModelAndView("park/parklot/parklot");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('park:parklot:search')")
    public Page<ParkLotVO> search(ParkLotDTO parkLotDTO, @PageableDefault Pageable pageable) {
        return parkLotService.search(parkLotDTO, pageable);
    }


    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('park:parklot:add')")
    public ModelAndView add(Model model) {
        return new ModelAndView("park/parklot/add");
    }

    /**
     * 新增停车场
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('park:parklot:add')")
    public void add(@Valid ParkLotDTO parkLotDTO) {
        parkLotService.save(parkLotDTO);
    }


    /**
     * 跳转到修改页面
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('park:parklot:edit')")
    public ModelAndView edit(@PathVariable String id, Model model) {
        model.addAttribute("parklot", parkLotService.getById(id));
        return new ModelAndView("park/parklot/edit");
    }

    /**
     * 修改保存
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('park:parklot:edit')")
    public void edit(@Valid ParkLotDTO parkLotDTO) {
        parkLotService.update(parkLotDTO);
    }

    /**
     * 删除停车场
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('park:parklot:remove')")
    public void remove(String ids) {
        parkLotService.remove(ids);
    }
}
