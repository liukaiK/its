package cn.com.goodlan.its.web.security;

import cn.com.goodlan.its.core.constant.SystemConstant;
import cn.com.goodlan.its.core.exception.authentication.UsernameMoreThanOneException;
import cn.com.goodlan.its.core.pojo.Result;
import cn.com.goodlan.its.core.util.HttpUtil;
import cn.com.goodlan.its.core.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liukai
 */
@Slf4j
@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (HttpUtil.isAjaxRequest(request)) {
            if (authException instanceof UsernameMoreThanOneException) {
                ResponseUtil.write(response, objectMapper.writeValueAsString(Result.fail(500, "用户数据出现异常,请联系管理员")), MediaType.APPLICATION_JSON_VALUE);
                return;
            }
            ResponseUtil.write(response, objectMapper.writeValueAsString(Result.fail(403, "页面已经过期 请手动刷新页面")), MediaType.APPLICATION_JSON_VALUE);
        } else {
            response.sendRedirect(SystemConstant.LOGIN_PAGE);
        }
    }

}
