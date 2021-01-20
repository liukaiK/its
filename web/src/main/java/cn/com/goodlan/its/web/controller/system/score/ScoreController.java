package cn.com.goodlan.its.web.controller.system.score;

import cn.com.goodlan.its.common.annotations.ResponseResultBody;
import cn.com.goodlan.its.service.system.score.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 扣分设置
 *
 * @author liukai
 */
@RestController
@ResponseResultBody
@RequestMapping("/system/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;


    @GetMapping
    @PreAuthorize("hasAuthority('system:score:view')")
    public ModelAndView vehicle() {
        return new ModelAndView("system/score/score");
    }

}
