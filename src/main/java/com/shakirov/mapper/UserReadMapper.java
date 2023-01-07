package com.shakirov.mapper;

import com.shakirov.dto.CompanyReadDto;
import com.shakirov.dto.UserReadDto;
import com.shakirov.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author igor@shakirov-dev.ru on 27.12.2022
 * @version 1.0
 */

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User object) {
        CompanyReadDto company = Optional.ofNullable(object.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);

        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getBirthDate(),
                object.getFirstname(),
                object.getLastname(),
                object.getImage(),
                object.getRole(),
                company);
    }
}