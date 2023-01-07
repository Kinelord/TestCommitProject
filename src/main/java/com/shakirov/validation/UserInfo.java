package com.shakirov.validation;

import com.shakirov.validation.impl.UserInfoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author igor@shakirov-dev.ru on 29.12.2022
 * @version 1.0
 */

@Constraint(validatedBy = UserInfoValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInfo {
    String message() default "{Firstname or Lastname should be filled in}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
