package com.shakirov.database.collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author igor@shakirov-dev.ru on 07.01.2023
 * @version 1.0
 */

@Component
public class CollectAutowired {
	
	private final String test;
	
	public CollectAutowired(@Value("1") String test) {
		this.test = test;
	}
}
