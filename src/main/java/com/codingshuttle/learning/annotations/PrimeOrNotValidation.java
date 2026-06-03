package com.codingshuttle.learning.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PrimeOrNotValidator.class})
public @interface PrimeOrNotValidation {
    String message() default "Employee Count must be a prime";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
