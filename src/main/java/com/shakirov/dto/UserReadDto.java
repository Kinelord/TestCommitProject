package com.shakirov.dto;

import com.shakirov.model.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {

    Long id;
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    String image;
    Role role;
    CompanyReadDto companyId;
}
