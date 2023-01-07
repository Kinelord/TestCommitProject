package com.shakirov.dto;

import java.time.LocalDate;

public record PersonalInfoImpl(String firstname,
                               String lastname,
                               LocalDate birthDate) {
}
