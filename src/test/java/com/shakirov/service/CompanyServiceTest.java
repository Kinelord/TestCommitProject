package com.shakirov.service;

import com.shakirov.dto.CompanyReadDto;
import com.shakirov.model.Company;
import com.shakirov.database.repository.CompanyRepository;
import com.shakirov.spting_pp.listener.EntityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    private static final Integer COMPANY_ID = 1;

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private UserService userService;
    @Mock  // Для установки заглушен необходимые классу CompanyService
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks  // Для тестирования методов этого класса, будет создан объект этого класса
    // вставляет сюда заглушки @Mock чтоб можно было создать это объект
    private CompanyService companyService;

    @Test
    void findById() {

        //GIVEN

        //WHEN
        doReturn(Optional.of(new Company(COMPANY_ID, null, Collections.emptyMap())))
                .when(companyRepository).findById(COMPANY_ID);

        //THEN
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID, null);

        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));

         verify(eventPublisher).publishEvent(any(EntityEvent.class));

         verifyNoMoreInteractions(eventPublisher, userService);

    }
}
//GIVEN
//WHEN
//THEN
