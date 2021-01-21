package cn.com.goodlan.its.common.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验手机号码
 *
 * @author liukai
 */
public class SexConstraintValidator implements ConstraintValidator<MobileNumber, String> {

    @Override
    public void initialize(MobileNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return "男".equals(value) || "女".equals(value);
    }

}
