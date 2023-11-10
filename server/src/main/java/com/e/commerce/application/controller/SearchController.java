package com.e.commerce.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e.commerce.application.dto.MultiResponseDto;
import com.e.commerce.domain.product.dto.ProductResponseDto;
import com.e.commerce.domain.product.service.ProductService;
import com.e.commerce.domain.store.dto.StoreResponseDto;
import com.e.commerce.domain.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
	private final StoreService storeService;
	private final ProductService productService;

	@GetMapping("/products")
	public ResponseEntity searchProductsForm(
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size) {

		MultiResponseDto<ProductResponseDto> responses = productService.getAllProductsByPaging(page - 1, size);

		return ResponseEntity.status(HttpStatus.OK).body(responses);
	}

	@GetMapping("/stores")
	public ResponseEntity searchStoresForm(
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size) {

		MultiResponseDto<StoreResponseDto> responses = storeService.getAllStoresByPaging(page - 1, size);

		return ResponseEntity.status(HttpStatus.OK).body(responses);
	}
}
