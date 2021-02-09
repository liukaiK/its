package cn.com.goodlan.its.web.security;

import cn.com.goodlan.its.common.constant.SystemConstant;
import cn.com.goodlan.its.common.util.HttpUtil;
import cn.com.goodlan.its.pojo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 当没有权限的时候 处理器
 *
 * @author liukai
 */
@Slf4j
@Component
public class NoAuthorityHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("访问被拒绝", accessDeniedException);
        if (HttpUtil.isAjaxRequest(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = response.getWriter();
            Result responseBody = Result.fail(500, "认证已失效,需要重新登录");
            writer.write(objectMapper.writeValueAsString(responseBody));
            writer.close();
        } else {
            response.sendRedirect(SystemConstant.LOGIN_PAGE);
        }

    }

}
