package com.mashreq.conference.infra.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( {ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default """
                At least 8 characters
                Contains at least one digit
                Contains at least one special character (@, #, $, %, ^, &, +, =, or !)
                Does not contain whitespace""";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}