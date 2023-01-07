package com.shakirov.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile(value = "dev")
@Configuration
public class WebSocketConfiguration {
}
