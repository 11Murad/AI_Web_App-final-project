package com.spring.aiwebapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AllowedImageSizeValidator implements ConstraintValidator<AllowedImageSize, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value == 256 || value == 512 || value == 1024;
    }
}
