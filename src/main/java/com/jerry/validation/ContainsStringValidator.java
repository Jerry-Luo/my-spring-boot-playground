package com.jerry.validation;

import com.jerry.validation.constraints.ContainsString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContainsStringValidator implements ConstraintValidator<ContainsString, String> {
    private String str;

    @Override
    public void initialize(ContainsString constraintAnnotation) {
        str = constraintAnnotation.str();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!value.contains(str)) {
            context.disableDefaultConstraintViolation();

            ConstraintValidatorContext.ConstraintViolationBuilder builder =
                    context.buildConstraintViolationWithTemplate("字符串中必须包含" + str);
            builder.addConstraintViolation();
            return false;
        }
        return true;
    }
}
