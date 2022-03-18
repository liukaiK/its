package cn.com.goodlan.its.web.controller.event.analyse;

import cn.com.goodlan.its.core.annotations.ResponseResultBody;
import cn.com.goodlan.its.core.pojo.query.AnalyseQuery;
import cn.com.goodlan.its.core.pojo.vo.AnalyseVo;
import cn.com.goodlan.its.core.service.event.analyse.AnalyseService;
import cn.com.goodlan.its.core.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 统计分析
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/event/analyse")
public class AnalyseController {

    @Autowired
    private AnalyseService analyseService;

    @GetMapping
    @PreAuthorize("hasAuthority('event:analyse:view')")
    public ModelAndView approval(Model model) {
        return new ModelAndView("event/analyse/analyse");
    }


    @PostMapping("/search")
    @PreAuthorize("hasAuthority('event:analyse:search')")
    public List<AnalyseVo> search(AnalyseQuery analyseQuery) {
        return analyseService.search(analyseQuery);
    }

    /**
     * 导出Excel
     */
    @PostMapping("/export")
    @PreAuthorize("hasAuthority('event:analyse:export')")
    public Map<String, Object> export(AnalyseQuery analyseQuery) {
        Map<String, Object> map = new HashMap<>(2);
        List<AnalyseVo> list = analyseService.search(analyseQuery);
        ExcelUtil<AnalyseVo> util = new ExcelUtil<>(AnalyseVo.class);
        String fileName = util.exportExcel(list, "统计分析");
        map.put("fileName", fileName);
        return map;
    }


}
