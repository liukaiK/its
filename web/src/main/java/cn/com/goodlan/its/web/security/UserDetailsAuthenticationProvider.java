package cn.com.goodlan.its.web.security;


import cn.com.goodlan.its.core.constant.CaptchaConstant;
import cn.com.goodlan.its.core.exception.authentication.BadCaptchaException;
import cn.com.goodlan.its.core.exception.authentication.CaptchaExpiredException;
import cn.com.goodlan.its.core.exception.authentication.UsernameMoreThanOneException;
import cn.com.goodlan.its.core.util.RSAUtil;
import cn.com.goodlan.its.web.security.captcha.Captcha;
import cn.hutool.crypto.CryptoException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liukai
 */
@Slf4j
public class UserDetailsAuthenticationProvider implements AuthenticationProvider {


    @Setter
    private UserDetailsService userDetailsService;

    @Setter
    private PasswordEncoder passwordEncoder;

    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Determine username
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

        UserDetails user;

        try {
            checkCaptcha(authentication);
            user = userDetailsService.loadUserByUsername(username);
            preAuthenticationChecks(user);
            additionalAuthenticationChecks(user, (UsernamePasswordCaptchaAuthenticationToken) authentication);
        } catch (IncorrectResultSizeDataAccessException exception) {
            log.error("??????????????????", exception);
            throw new UsernameMoreThanOneException("???????????????");
        } catch (AuthenticationException exception) {
            log.error("AuthenticationException: ", exception);
            throw exception;
        } catch (CryptoException exception) {
            log.error("username:{} RSA????????????????????????", username);
            throw new BadCredentialsException("RSA????????????????????????");
        }


        return createSuccessAuthentication(user, authentication, user);
    }


    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        UsernamePasswordCaptchaAuthenticationToken result = new UsernamePasswordCaptchaAuthenticationToken(principal, authentication.getCredentials(), authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        return result;
    }


    /**
     * ??????????????????????????? ??????????????????
     */
    private void preAuthenticationChecks(UserDetails user) {
        if (!user.isEnabled()) {
            throw new DisabledException("???????????????");
        }
    }


    /**
     * ????????????
     */
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordCaptchaAuthenticationToken authentication) throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("??????????????????");
        }

        String originPassword = getOriginPassword(authentication.getCredentials().toString());

        if (!passwordEncoder.matches(originPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("??????????????????");
        }


    }


    /**
     * ??????????????????????????????RSA??????
     */
    private String getOriginPassword(String decrypt) {
        return RSAUtil.decrypt(decrypt);
    }


    /**
     * ???????????????
     */
    private void checkCaptcha(Authentication authentication) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String captcha = ((UsernamePasswordCaptchaAuthenticationToken) authentication).getCaptcha();

        Captcha captchaInSession = (Captcha) request.getSession().getAttribute(CaptchaConstant.CAPTCHA_SESSION_KEY);

        if (captchaInSession == null || captchaInSession.isExpired()) {
            throw new CaptchaExpiredException("?????????????????????");
        }

        if (!captcha.equalsIgnoreCase(captchaInSession.getCode())) {
            throw new BadCaptchaException("???????????????");
        }

    }


    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordCaptchaAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
