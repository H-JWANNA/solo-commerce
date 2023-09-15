package com.e.commerce.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.e.commerce.application.dto.MultiResponseDto;
import com.e.commerce.domain.product.dto.ProductResponseDto;
import com.e.commerce.domain.product.service.ProductService;
import com.e.commerce.domain.store.dto.StoreResponseDto;
import com.e.commerce.domain.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
	private final StoreService storeService;
	private final ProductService productService;

	@GetMapping("/products")
	public String searchProductsForm(
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size, Model model) {
		MultiResponseDto<ProductResponseDto> responses = productService.getAllProductsByPaging(page - 1, size);
		model.addAttribute("productList", responses.data());
		model.addAttribute("pageInfo", responses.pageInfo());

		return "search-products";
	}

	@GetMapping("/stores")
	public String searchStoresForm(
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size, Model model) {
		MultiResponseDto<StoreResponseDto> responses = storeService.getAllStoresByPaging(page - 1, size);
		model.addAttribute("storeList", responses.data());
		model.addAttribute("pageInfo", responses.pageInfo());

		return "search-stores";
	}
}
