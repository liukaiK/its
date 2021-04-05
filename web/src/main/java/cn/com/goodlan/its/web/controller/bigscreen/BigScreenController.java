package cn.com.goodlan.its.web.controller.bigscreen;


import cn.com.goodlan.its.pojo.vo.RecordVO;
import cn.com.goodlan.its.service.event.approval.EventApprovalService;
import cn.com.goodlan.its.service.event.record.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 大屏展示
 *
 * @author liukai
 */
@Controller
@RequestMapping("/bigscreen")
public class BigScreenController {

    @Autowired
    private EventApprovalService eventApprovalService;

    @Autowired
    private RecordService recordService;

    @GetMapping
    public String bigScreen(Model model) {
        List<RecordVO> recordList = recordService.search();
        model.addAttribute("recordList", recordList);
        return "bigscreen/bigscreen";
    }

}
