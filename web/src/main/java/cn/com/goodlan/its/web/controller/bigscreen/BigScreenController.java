package cn.com.goodlan.its.web.controller.bigscreen;


import org.springframework.stereotype.Controller;
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

    @GetMapping
    public String bigScreen() {
        return "bigscreen/bigscreen";
    }

}
