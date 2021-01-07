package cn.com.goodlan.its.common.exception;

/**
 * 密码错误异常
 *
 * @author liukai
 */
public class BadPasswordException extends BusinessException {

    public BadPasswordException(String message) {
        super(message);
    }
}