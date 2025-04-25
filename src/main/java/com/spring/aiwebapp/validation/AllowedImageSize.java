package com.spring.aiwebapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = AllowedImageSizeValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface AllowedImageSize {
    String message() default "Image size must be 256, 512, or 1024";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
