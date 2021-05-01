package com.QuasarFireOperationMS.web.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author jiz10
 * 1/5/21
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MessageValidatorImpl.class)
@Documented
public @interface MessageValidator {

    String message() default "Message invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
