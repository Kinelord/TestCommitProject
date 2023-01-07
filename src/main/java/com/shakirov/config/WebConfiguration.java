package com.shakirov.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author igor@shakirov-dev.ru on 29.12.2022
 * @version 1.0
 */

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer{
	
//	private final AsyncUserService asyncUserService;
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		WebMvcConfigurer.super.addFormatters(registry);
//        registry.addConverter(Jsr310Converters.StringToLocalDateConverter.INSTANCE);
	}
	
//	@Bean
//	public CommandLineRunner CommandLineRunnerBean() {
//		return (args) -> {
//			System.out.println("******************Start CommandLineRunner");
//			AsyncUser userByIdSync = asyncUserService
////                    .getUserByIdAsync("1")
//					.getUserByIdSync("1");
////                    .subscribe(user -> log.info("___________Get user async: {}", user));
//		};
//	}
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Herrrrr_____________________");
//    }
}
