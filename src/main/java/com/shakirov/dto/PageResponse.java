package com.shakirov.dto;

import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author igor@shakirov-dev.ru on 30.12.2022
 * @version 1.0
 */

@Value
public class PageResponse<T> {
	List<T> content;
	Metadata metadata;

	public static <T> PageResponse<T> of (Page<T> page) {
		Metadata metadata = new Metadata(page.getNumber(), page.getSize(), page.getTotalElements());
		return new PageResponse<>(page.getContent(), metadata);
	}

	@Value
	public static class Metadata {
		int page;
		int size;
		long totalElements;
	}
}
