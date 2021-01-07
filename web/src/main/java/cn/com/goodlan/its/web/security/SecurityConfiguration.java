package cn.com.goodlan.its.web.security;

import cn.com.goodlan.its.common.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * SpringSecurity核心配置类
 *
 * @author liukai
 */
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BrowserSecurityConfigurer browserSecurityConfigurer;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(SystemConstant.LOGIN_PAGE).permitAll()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().sameOrigin();
        http.apply(browserSecurityConfigurer);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/js/**",
                "/css/**",
                "/img/**",
                "/fonts/**",
                "/ruoyi.png",
                "/ruoyi/**",
                "/ajax/**",
                "/captcha.jpeg",
                "/plugins/**",
                "/favicon.ico"
        );
    }


}
