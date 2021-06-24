package cn.com.goodlan.its.web.controller.bigscreen;


import cn.com.goodlan.its.core.dao.primary.system.record.RecordRepository;
import cn.com.goodlan.its.core.service.event.statistics.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 大屏展示
 *
 * @author liukai
 */
@Controller
@RequestMapping("/bigscreen")
public class BigScreenController {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping
    public String bigScreen(Model model) {
        model.addAttribute("recordList", statisticsService.search());
        model.addAttribute("zybm", recordRepository.bigScreen());
        return "bigscreen/bigscreen";
    }


}
