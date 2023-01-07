package com.shakirov.config;

import com.shakirov.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
@EnableEnversRepositories(basePackageClasses = ApplicationRunner.class)  // Для подключения механизма
// hibernate envers, но и дополнительно делать запросы к вспомогательным таблицам
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditAware() {

        // SecurityContext.getCurrentUser().getEmail();
        return () -> Optional.of("igor.shakirov.96@mail.ru");
    }


}
