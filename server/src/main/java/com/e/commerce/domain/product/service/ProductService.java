package com.e.commerce.domain.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e.commerce.application.dto.MultiResponseDto;
import com.e.commerce.application.dto.PageInfo;
import com.e.commerce.domain.product.dto.ProductResponseDto;
import com.e.commerce.domain.product.entity.Product;
import com.e.commerce.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	@Transactional(readOnly = true)
	public MultiResponseDto<ProductResponseDto> getAllProductsByPaging(int page, int size) {
		Page<Product> products = productRepository.findAll(PageRequest.of(page, size));

		List<ProductResponseDto> responses = products.getContent()
			.stream()
			.map(ProductResponseDto::from)
			.toList();

		return new MultiResponseDto<>(responses, PageInfo.from(products));
	}
}
