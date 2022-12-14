package cn.com.goodlan.its.web.controller.system.login;

import cn.com.goodlan.its.core.constant.CaptchaConstant;
import cn.com.goodlan.its.core.util.RSAUtil;
import cn.com.goodlan.its.web.security.captcha.CaptchaGenerator;
import cn.hutool.captcha.ICaptcha;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录Controller
 *
 * @author liukai
 */
@Controller
public class LoginController {

    /**
     * 跳转到登录页面
     */
    @GetMapping("/login")
    public String login(Model model) throws JsonProcessingException {
        return alreadyLogin() ? toMainPage() : toLoginPage(model);
    }

    private String toLoginPage(Model model) {
        model.addAttribute("PUBLIC_KEY", RSAUtil.PUBLIC_KEY);
        return "login";
    }

    private String toMainPage() {
        return "redirect:/";
    }

    private boolean alreadyLogin() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    @GetMapping(value = "/captcha.jpeg", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ICaptcha captcha = CaptchaGenerator.generate(200, 100, CaptchaConstant.CAPTCHA_LENGTH, CaptchaConstant.CAPTCHA_EXPIRE_TIME);
        request.getSession().setAttribute(CaptchaConstant.CAPTCHA_SESSION_KEY, captcha);
        captcha.write(response.getOutputStream());
    }


}
