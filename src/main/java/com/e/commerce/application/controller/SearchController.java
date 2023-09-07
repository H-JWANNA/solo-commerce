package com.e.commerce.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.e.commerce.application.dto.MultiResponseDto;
import com.e.commerce.domain.store.dto.StoreResponseDto;
import com.e.commerce.domain.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
	private final StoreService storeService;

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
