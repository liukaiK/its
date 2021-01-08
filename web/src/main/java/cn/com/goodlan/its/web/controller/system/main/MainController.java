package cn.com.goodlan.its.web.controller.system.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liukai
 */
@Controller
public class MainController {

    @GetMapping("/system/main")
    public String main() {
        return "/main";
    }

}
