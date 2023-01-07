package com.shakirov.integration.http.controller;

import com.shakirov.dto.UserCreateEditDto;
import com.shakirov.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.shakirov.dto.UserCreateEditDto.Fields.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author igor@shakirov-dev.ru on 28.12.2022
 * @version 1.0
 */

@RequiredArgsConstructor
@AutoConfigureMockMvc
class UserControllerIntegrationTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(5)));
    }

    @Test
    void findById() {
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(
                        post("/users")
                                .param("username", "test@gmail.com")
                                .param(UserCreateEditDto.Fields.firstname, "Test")
                                .param(lastname, "TestLastName")
                                .param(role, "ADMIN")
                                .param(companyId, "1")
                                .param(birthDate, LocalDate.now().toString())
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")  // {\d+} регулярное выражение, проверяем, чтоб в URI как минимум 1 раз было вставлено число
//                        redirectedUrlPattern("/users/*") Можно вместо числа поставить *
                );

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}