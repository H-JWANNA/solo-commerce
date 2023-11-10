package com.e.commerce.application.dto;

import java.util.List;

public record MultiResponseDto<T>(
	List<T> data,
	PageInfo pageInfo
) {
}
