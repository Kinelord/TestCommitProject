package com.shakirov.integration.service;

import com.shakirov.integration.annotation.IT;
import com.shakirov.config.test.DataBaseProperties;
import com.shakirov.dto.CompanyReadDto;
import com.shakirov.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Это есть в SpringBootTest
/*
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationRunner.class,
        initializers = ConfigDataApplicationContextInitializer.class)
*/

// Засунунли все в свою аннотацию и поставили ее
/*
@ActiveProfiles("test")
@SpringBootTest
*/

@IT
@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)  // Чтоб не загромождать аннотациями, вынесли в отдельную проперти
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;
    private final DataBaseProperties dataBaseProperties;

    @Test
    void findById() {

        //GIVEN

        //WHEN

        //THEN
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID, null);

        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

}
