package com.shakirov.service.async;

import com.shakirov.model.async.AsyncUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

//@Service
@RequiredArgsConstructor
@Slf4j
@Profile(value = "dev")
public class AsyncUserService {
    private static final String USERS_URL_TEMPLATE = "/users/{id}";
    private static final String BROKEN_URL_TEMPLATE = "/broken-url/{id}";
    public static final int DELAY_MILLIS = 100;
    public static final int MAX_RETRY_ATTEMPTS = 3;
    private final WebClient webClient;

    public Mono<AsyncUser> getUserByIdAsync(final String id) {
        return webClient
                .get()
                .uri(USERS_URL_TEMPLATE, id)
                .retrieve()
                .bodyToMono(AsyncUser.class);
    }

    public AsyncUser getUserByIdSync(final String id) {
        return webClient
                .get()
                .uri(USERS_URL_TEMPLATE, id)
                .retrieve()
                .bodyToMono(AsyncUser.class)
                .block();
    }

    public AsyncUser getUserWithRetry(final String id) {
        return webClient
                .get()
                .uri(BROKEN_URL_TEMPLATE, id)
                .retrieve()
                .bodyToMono(AsyncUser.class)
                .retryWhen(Retry.fixedDelay(MAX_RETRY_ATTEMPTS, Duration.ofMillis(DELAY_MILLIS)))
                .block();
    }

    public AsyncUser getUserWithFallback(final String id) {
        return webClient
                .get()
                .uri(BROKEN_URL_TEMPLATE, id)
                .retrieve()
                .bodyToMono(AsyncUser.class)
                .doOnError(error -> log.error("An error has occurred {}", error.getMessage()))
                .onErrorResume(error -> Mono.just(new AsyncUser()))
                .block();
    }

    public AsyncUser getUserWithErrorHandling(final String id) {
        return webClient
                .get()
                .uri(BROKEN_URL_TEMPLATE, id)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("API not found")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RuntimeException("Server is not responding")))
                .bodyToMono(AsyncUser.class)
                .block();
    }
}
