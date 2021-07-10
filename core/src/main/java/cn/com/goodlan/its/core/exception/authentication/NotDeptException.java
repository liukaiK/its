package cn.com.goodlan.its.core.exception.authentication;

import org.springframework.security.core.AuthenticationException;

public class NotDeptException extends AuthenticationException {

    public NotDeptException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotDeptException(String msg) {
        super(msg);
    }

}
