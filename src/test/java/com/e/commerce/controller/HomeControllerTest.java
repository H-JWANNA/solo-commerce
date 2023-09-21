package com.e.commerce.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.e.commerce.application.controller.HomeController;

@WebMvcTest(controllers = HomeController.class,
	excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("홈 Controller Test")
	void homeTest() throws Exception {
		// when
		ResultActions actions =
			mockMvc.perform(get("/"));

		// then
		actions
			.andExpect(status().isOk())
			.andExpect(view().name("home"));
	}
}
