package com.shakirov.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author igor@shakirov-dev.ru on 29.12.2022
 * @version 1.0
 */

@Slf4j
@RestControllerAdvice(basePackages = "com.shakirov.http.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

}
