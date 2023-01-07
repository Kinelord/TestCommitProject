package com.shakirov.database.collections;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author igor@shakirov-dev.ru on 07.01.2023
 * @version 1.0
 */

@Component
@RequiredArgsConstructor
public class TestAutowiredCollect {
	
	@Qualifier(value = "1")
	private final List<CollectAutowired> list;
	
	@Qualifier(value = "1")
	private final Map<String, CollectAutowired> map;
}
