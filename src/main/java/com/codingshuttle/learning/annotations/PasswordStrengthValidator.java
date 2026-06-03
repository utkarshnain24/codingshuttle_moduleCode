package com.codingshuttle.learning.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordStrengthValidation, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        StringBuilder errors = new StringBuilder();

        if (password.length() < 10) {
            errors.append("Password must be at least 10 characters long. ");
        }

        if (!password.matches(".*[A-Z].*")) {
            errors.append("Password must contain at least one uppercase letter. ");
        }

        if (!password.matches(".*[a-z].*")) {
            errors.append("Password must contain at least one lowercase letter. ");
        }

        if (!password.matches(".*[^A-Za-z0-9].*")) {
            errors.append("Password must contain at least one special character. ");
        }

        if (!errors.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errors.toString())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
