package com.shakirov.database.component;

import lombok.RequiredArgsConstructor;

/**
 * @author igor@shakirov-dev.ru on 07.01.2023
 * @version 1.0
 */


@RequiredArgsConstructor
public class ComponentBeanTest {
	
	private final String her;
	
	
	public void initMethod() {
		System.out.println("____________" + her + "___________");
	}
}
