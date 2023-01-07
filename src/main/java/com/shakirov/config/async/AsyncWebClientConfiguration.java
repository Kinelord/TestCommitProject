package com.shakirov.config.async;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@Configuration
@Profile(value = "dev")
public class AsyncWebClientConfiguration {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final int TIMEOUT = 1000;

    @Bean
    public WebClient webClientWithTimeout() {
        final var tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                });

//        WebClient.builder()
//                .baseUrl(BASE_URL)
//                .filter(basicAuthentication("user", "password")) // org.springframework.web.reactive.function.client.basicAuthentication()
//                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
//                .build();

        return WebClient.builder()
                .baseUrl(BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
