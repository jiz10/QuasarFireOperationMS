package com.QuasarFireOperationMS.web.model.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;
import javax.validation.Payload;

/**
 * @author jiz10
 * 30/4/21
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SatelliteListValidatorImpl.class)
@Documented
public @interface SatelliteListValidator {

    String message() default "Satellites group invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
