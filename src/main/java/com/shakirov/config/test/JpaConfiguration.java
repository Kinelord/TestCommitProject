package com.shakirov.config.test;

import com.shakirov.config.condition.JpaCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Conditional(value = JpaCondition.class)
@Slf4j
@ComponentScan(value = "com.web")
public class JpaConfiguration {

//    @Bean
//    ClassB classB = new ClassB("f", null);

//    @PostConstruct
    void init() {
        log.warn("Jpa configuration is enabled");
    }

/*    @Bean
    @ConfigurationProperties (prefix = "db")
    public DataBaseProperties dataBaseProperties() {
        return new DataBaseProperties();
    }*/
}
