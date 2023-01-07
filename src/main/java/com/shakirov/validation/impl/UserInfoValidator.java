package com.shakirov.validation.impl;

import com.shakirov.dto.UserCreateEditDto;
import com.shakirov.database.repository.CompanyRepository;
import com.shakirov.validation.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author igor@shakirov-dev.ru on 29.12.2022
 * @version 1.0
 */

@Component  // По умолчанию этот валлидатор и без этой аннотации может работать
@RequiredArgsConstructor
public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {

    private final CompanyRepository companyRepository;

    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getFirstname()) || StringUtils.hasText(value.getLastname());
    }
}