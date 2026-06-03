package com.codingshuttle.learning.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeOrNotValidator implements ConstraintValidator<PrimeOrNotValidation, Integer> {
    @Override
    public boolean isValid(Integer inputNumber, ConstraintValidatorContext context) {
        if (inputNumber <= 1) {
            return false;
        }
        for (int i = 2; i * i <= inputNumber; i++) {
            if (inputNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
}
