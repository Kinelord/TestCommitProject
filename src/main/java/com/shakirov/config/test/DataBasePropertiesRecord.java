package com.shakirov.config.test;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties (prefix = "db-test")
public record DataBasePropertiesRecord(
        String url,
        String username,
        String password,
        String driver,
        String hosts) {


}
