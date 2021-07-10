package cn.com.goodlan.its.core.annotations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 校验账号规则
 *
 * @author liukai
 */
public class UsernameConstraintValidator implements ConstraintValidator<Username, String> {

    private final static String PATTERN = "^[a-zA-Z0-9]{4,16}$";

    @Override
    public void initialize(Username constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return Pattern.matches(PATTERN, value);
    }

}
