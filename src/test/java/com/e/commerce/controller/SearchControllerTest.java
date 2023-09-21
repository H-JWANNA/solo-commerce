package com.e.commerce.controller;

import static com.e.commerce.stub.SearchStubData.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.e.commerce.application.controller.SearchController;
import com.e.commerce.application.dto.MultiResponseDto;
import com.e.commerce.application.dto.PageInfo;
import com.e.commerce.domain.product.dto.ProductResponseDto;
import com.e.commerce.domain.product.service.ProductService;
import com.e.commerce.domain.store.dto.StoreResponseDto;
import com.e.commerce.domain.store.service.StoreService;

@WebMvcTest(controllers = SearchController.class,
	excludeAutoConfiguration = SecurityAutoConfiguration.class)
@MockBean(JpaMetamodelMappingContext.class)
public class SearchControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StoreService storeService;

	@MockBean
	private ProductService productService;

	@Test
	@DisplayName("상품 검색 폼 Controller Test")
	void searchProductsFormTest() throws Exception {
		// given
		List<ProductResponseDto> products = getStubProducts();
		PageInfo pageInfo = getStubPageInfo();
		MultiResponseDto<ProductResponseDto> responses = new MultiResponseDto<>(products, pageInfo);

		given(productService.getAllProductsByPaging(anyInt(), anyInt())).willReturn(responses);

		// when
		ResultActions actions =
			mockMvc.perform(
				get("/search/products")
					.param("page", String.valueOf(pageInfo.page() + 1))
					.param("size", String.valueOf(pageInfo.size()))
			);

		// then
		actions
			.andExpect(status().isOk())
			.andExpect(view().name("search-products"))
			.andExpect(model().attributeExists("productList"))
			.andExpect(model().attributeExists("pageInfo"));
	}

	@Test
	@DisplayName("매장 검색 폼 Controller Test")
	void searchStoresFormTest() throws Exception {
		// given
		List<StoreResponseDto> stores = getStubStores();
		PageInfo pageInfo = getStubPageInfo();
		MultiResponseDto<StoreResponseDto> responses = new MultiResponseDto<>(stores, pageInfo);

		given(storeService.getAllStoresByPaging(anyInt(), anyInt())).willReturn(responses);

		// when
		ResultActions actions =
			mockMvc.perform(
				get("/search/stores")
					.param("page", String.valueOf(pageInfo.page() + 1))
					.param("size", String.valueOf(pageInfo.size()))
			);

		// then
		actions
			.andExpect(status().isOk())
			.andExpect(view().name("search-stores"))
			.andExpect(model().attributeExists("storeList"))
			.andExpect(model().attributeExists("pageInfo"));
	}
}
