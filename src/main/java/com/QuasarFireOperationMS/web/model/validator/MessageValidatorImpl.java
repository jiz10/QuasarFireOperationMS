package com.QuasarFireOperationMS.web.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

/**
 * @author jiz10
 * 1/5/21
 */
public class MessageValidatorImpl implements ConstraintValidator<MessageValidator, List<String>> {

    @Override
    public void initialize(MessageValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {

        if (Objects.isNull(value))
            return false;

        if (!(value.size() > 0))
            return false;

        return true;
    }
}
