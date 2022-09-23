package cn.com.goodlan.its.web.controller.whitelist;

import cn.com.goodlan.its.core.annotations.ResponseResultBody;
import cn.com.goodlan.its.core.pojo.entity.primary.whitelist.Whitelist;
import cn.com.goodlan.its.core.service.whitelist.WhitelistService;
import lombok.AllArgsConstructor;
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

/**
 * 白名单Controller
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@AllArgsConstructor
@RequestMapping("/event/whitelist")
public class WhitelistController {

    private WhitelistService whitelistService;

    @GetMapping
    @PreAuthorize("hasAuthority('event:whitelist:view')")
    public ModelAndView whitelist() {
        return new ModelAndView("event/whitelist/whitelist");
    }


    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('event:whitelist:search')")
    public Page<Whitelist> search(String licensePlateNumber, @PageableDefault Pageable pageable) {
        return whitelistService.search(licensePlateNumber, pageable);
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('event:whitelist:add')")
    public ModelAndView add(Model model) {
        return new ModelAndView("event/whitelist/add");
    }


    /**
     * 新增白名单
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('event:whitelist:add')")
    public void add(String licensePlateNumber) {
        whitelistService.save(licensePlateNumber);
    }


    /**
     * 删除白名单
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('event:whitelist:remove')")
    public void remove(String ids) {
        whitelistService.remove(ids);
    }


}
