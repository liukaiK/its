package cn.com.goodlan.its.web.controller.eventhistory;

import cn.com.goodlan.its.core.annotations.ResponseResultBody;
import cn.com.goodlan.its.core.pojo.query.EventHistoryQuery;
import cn.com.goodlan.its.core.pojo.vo.EventHistoryVO;
import cn.com.goodlan.its.core.service.eventhistory.EventHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ResponseResultBody
@AllArgsConstructor
@RequestMapping("/event/history")
public class EventHistoryController {

    private final EventHistoryService eventHistoryService;


    @GetMapping
    @PreAuthorize("hasAuthority('event:history:view')")
    public ModelAndView view() {
        return new ModelAndView("event/history/history");
    }

    /**
     * 分页查询
     */
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('event:history:search')")
    public Page<EventHistoryVO> search(EventHistoryQuery eventHistoryQuery, @PageableDefault Pageable pageable) {
        return eventHistoryService.search(eventHistoryQuery, pageable);
    }


    /**
     * 跳转到查看详情页面
     */
    @GetMapping("/detail/{id}")
    @PreAuthorize("hasAuthority('event:history:detail')")
    public ModelAndView detail(@PathVariable String id, Model model) {
        model.addAttribute("history", eventHistoryService.getById(id));
        return new ModelAndView("event/history/detail");
    }

}
