package com.e.commerce.stub;

import java.util.ArrayList;
import java.util.List;

import com.e.commerce.application.dto.PageInfo;
import com.e.commerce.domain.product.dto.ProductResponseDto;
import com.e.commerce.domain.store.dto.StoreResponseDto;

public class SearchStubData {
	public static List<StoreResponseDto> getStubStores() {
		List<StoreResponseDto> stores = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			stores.add(
				new StoreResponseDto(
					"storeName" + i,
					"address" + i)
			);
		}

		return stores;
	}

	public static List<ProductResponseDto> getStubProducts() {
		List<ProductResponseDto> products = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			products.add(
				new ProductResponseDto(
					(long)i,
					"productName" + i,
					i * 10000,
					"information" + i)
			);
		}

		return products;
	}

	public static PageInfo getStubPageInfo() {
		return new PageInfo(
			3,
			10,
			30,
			300
		);
	}
}
