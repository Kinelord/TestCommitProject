package com.shakirov.dto;

import lombok.Value;

/**
 * @author igor@shakirov-dev.ru on 26.12.2022
 * @version 1.0
 */

@Value
public class LoginDto {

    String username;
    String password;
}
