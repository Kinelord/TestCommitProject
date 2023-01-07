package com.shakirov.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author igor@shakirov-dev.ru on 29.12.2022
 * @version 1.0
 */

@Slf4j
@ControllerAdvice(basePackages = "com.shakirov.http.controller")
public class ControllerExceptionHandler/* extends ResponseEntityExceptionHandler*/ {


	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception,
	                              HttpServletRequest request,
	                              Model model) {

		log.error("Failed to return response", exception);
		return "error/error500";

	}
}
