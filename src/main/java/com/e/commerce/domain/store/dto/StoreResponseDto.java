package com.e.commerce.domain.store.dto;

import com.e.commerce.domain.store.entity.Store;

public record StoreResponseDto(
	String storeName,
	String address
) {
	public static StoreResponseDto from(Store store) {
		return new StoreResponseDto(store.getStoreName(), store.getAddress());
	}
}
