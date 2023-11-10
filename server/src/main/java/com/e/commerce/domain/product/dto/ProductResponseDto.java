package com.e.commerce.domain.product.dto;

import com.e.commerce.domain.product.entity.Product;

public record ProductResponseDto(
	Long productId,
	String productName,
	Integer price,
	String information
) {
	public static ProductResponseDto from(Product product) {
		return new ProductResponseDto(
			product.getProductId(),
			product.getProductName(),
			product.getPrice(),
			product.getInformation());
	}
}
