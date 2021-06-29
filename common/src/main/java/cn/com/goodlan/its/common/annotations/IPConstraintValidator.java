package cn.com.goodlan.its.common.annotations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 校验IP
 *
 * @author liukai
 */
public class IPConstraintValidator implements ConstraintValidator<IP, String> {

    private final static String PATTERN = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";

    @Override
    public void initialize(IP constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return Pattern.matches(PATTERN, value);
    }

}
