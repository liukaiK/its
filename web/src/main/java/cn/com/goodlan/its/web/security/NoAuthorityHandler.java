package cn.com.goodlan.its.web.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 当没有权限的时候 处理器
 *
 * @author liukai
 */
@Slf4j
@Component
public class NoAuthorityHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("没有权限", accessDeniedException);
//        if (HttpUtil.isAjaxRequest(request)) {
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//            response.setStatus(HttpServletResponse.SC_OK);
//            PrintWriter writer = response.getWriter();
//            if (authException instanceof UsernameMoreThanOneException) {
//                Result responseBody = Result.fail(500, "用户数据出现异常,请联系管理员");
//                writer.write(objectMapper.writeValueAsString(responseBody));
//                writer.close();
//                return;
//            }
//
//            Result responseBody = Result.fail(403, "认证已失效,需要重新登录");
//
//            writer.write(objectMapper.writeValueAsString(responseBody));
//            writer.close();
//        } else {
//            response.sendRedirect(SystemConstant.LOGIN_PAGE);
//        }

    }

}
