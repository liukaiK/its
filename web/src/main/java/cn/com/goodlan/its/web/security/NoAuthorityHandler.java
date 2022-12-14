package cn.com.goodlan.its.web.security;

import cn.com.goodlan.its.core.constant.SystemConstant;
import cn.com.goodlan.its.core.pojo.Result;
import cn.com.goodlan.its.core.util.HttpUtil;
import cn.com.goodlan.its.core.util.ResponseUtil;
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
        if (HttpUtil.isAjaxRequest(request)) {
            ResponseUtil.write(response, objectMapper.writeValueAsString(Result.fail(500, "页面已经过期,请刷新页面")), MediaType.APPLICATION_JSON_VALUE);
        } else {
            response.sendRedirect(SystemConstant.LOGIN_PAGE);
        }
    }

}
