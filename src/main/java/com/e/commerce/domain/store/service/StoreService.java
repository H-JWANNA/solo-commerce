package com.e.commerce.domain.store.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e.commerce.application.dto.MultiResponseDto;
import com.e.commerce.application.dto.PageInfo;
import com.e.commerce.domain.store.dto.StoreResponseDto;
import com.e.commerce.domain.store.entity.Store;
import com.e.commerce.domain.store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {
	private final StoreRepository storeRepository;

	@Transactional(readOnly = true)
	public MultiResponseDto<StoreResponseDto> getAllStoresByPaging(int page, int size) {
		Page<Store> stores = storeRepository.findAll(PageRequest.of(page, size));

		List<StoreResponseDto> responses = stores.getContent()
			.stream()
			.map(StoreResponseDto::from)
			.toList();

		return new MultiResponseDto<>(responses, PageInfo.from(stores));
	}
}
