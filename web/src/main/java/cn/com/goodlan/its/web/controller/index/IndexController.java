package cn.com.goodlan.its.web.controller.index;

import cn.com.goodlan.its.common.constant.SystemConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author liukai
 */
@RestController
public class IndexController {


    @GetMapping({"/index", "/"})
    public ModelAndView index() {
        return new ModelAndView(SystemConstant.PAGE + "/index");
    }

}

