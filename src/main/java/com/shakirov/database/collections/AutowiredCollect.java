package com.shakirov.database.collections;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author igor@shakirov-dev.ru on 07.01.2023
 * @version 1.0
 */

@Configuration
public class AutowiredCollect {
	
	@Bean
	@Qualifier(value = "1")
	@Order(4)
	public CollectAutowired collectAutowired1() {
		return new CollectAutowired("1");
	}
	
	
	@Bean
	@Qualifier(value = "1")
	@Order(3)
	public CollectAutowired collectAutowired2() {
		return new CollectAutowired("2");
	}
	
	
	@Bean
	@Qualifier(value = "1")
//	@Order(2)
	public CollectAutowired collectAutowired3() {
		return new CollectAutowired("3");
	}
	
	
	@Bean
	@Qualifier(value = "1")
	@Order(1)
	public CollectAutowired collectAutowired4() {
		return new CollectAutowired("4");
	}
}
