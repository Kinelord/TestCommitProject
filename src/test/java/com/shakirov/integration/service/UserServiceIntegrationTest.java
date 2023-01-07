package com.shakirov.integration.service;

import com.shakirov.dto.UserCreateEditDto;
import com.shakirov.dto.UserReadDto;
import com.shakirov.integration.IntegrationTestBase;
import com.shakirov.model.Role;
import com.shakirov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author igor@shakirov-dev.ru on 28.12.2022
 * @version 1.0
 */

@RequiredArgsConstructor
public class UserServiceIntegrationTest extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;

    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {

        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());

        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "123",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_1,
                null
        );

        UserReadDto actualResult = userService.create(userDto);
        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertEquals(userDto.getCompanyId(), actualResult.getCompanyId().id());
        assertSame(userDto.getRole(), actualResult.getRole());  // Для сравнения Enum

    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "123",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_1,
                null
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);
        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getCompanyId(), user.getCompanyId().id());
            assertSame(userDto.getRole(), user.getRole());  // Для сравнения Enum

        });
    }

    @Test
    void delete() {

        assertFalse(userService.delete(-12L));
        assertTrue(userService.delete(USER_1));
        assertFalse(userService.delete(USER_1));


    }

}
