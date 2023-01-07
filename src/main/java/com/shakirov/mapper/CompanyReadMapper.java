package com.shakirov.mapper;

import com.shakirov.dto.CompanyReadDto;
import com.shakirov.model.Company;
import org.springframework.stereotype.Component;

/**
 * @author igor@shakirov-dev.ru on 27.12.2022
 * @version 1.0
 */

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(object.getId(), object.getName());
    }
}
