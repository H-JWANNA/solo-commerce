package com.e.commerce.application.dto;

import org.springframework.data.domain.Page;

public record PageInfo(
	int page,
	int size,
	int totalPages,
	long totalElements
) {
	public static PageInfo from(Page<?> p) {
		return new PageInfo(
			p.getNumber(),
			p.getSize(),
			p.getTotalPages(),
			p.getTotalElements());
	}
}
