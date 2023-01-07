package com.shakirov.database.component;

import org.springframework.context.annotation.Bean;

/**
 * @author igor@shakirov-dev.ru on 07.01.2023
 * @version 1.0
 */


//@Component
public class TestBeanComponent {
	
	@Bean(value = "Igor", initMethod = "initMethod")
	public ComponentBeanTest componentBeanTest() {
		return new ComponentBeanTest("Her2");
	}
}
