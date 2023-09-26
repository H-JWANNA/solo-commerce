package com.e.commerce.controller;

import static com.e.commerce.stub.MemberStubData.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.e.commerce.domain.member.controller.RegisterController;
import com.e.commerce.domain.member.dto.RegisterRequest;
import com.e.commerce.domain.member.service.MemberService;

@WebMvcTest(controllers = RegisterController.class,
	excludeAutoConfiguration = SecurityAutoConfiguration.class)
@MockBean(JpaMetamodelMappingContext.class)
@DisplayName("[Register] Controller Test")
public class RegisterControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private MemberService memberService;

	@Test
	@DisplayName("회원 가입 폼")
	void registerFormTest() throws Exception {
		// when
		ResultActions actions =
			mockMvc.perform(
				get("/register"));

		// then
		actions
			.andExpect(status().isOk())
			.andExpect(view().name("register"));
	}

	@Test
	@DisplayName("회원 가입 로직")
	void registerTest() throws Exception {
		// given
		RegisterRequest request = getStubRegister();
		given(memberService.register(any(RegisterRequest.class))).willReturn(anyLong());

		// when
		ResultActions actions =
			mockMvc.perform(
				post("/register")
					.accept(MediaType.APPLICATION_FORM_URLENCODED)
					.param("username", request.username())
					.param("password", request.password())
					.param("name", request.name())
					.param("email", request.email())
					.param("phoneNumber", request.phoneNumber())
			);

		// then
		actions
			.andExpect(status().isCreated())
			.andExpect(view().name("login"));
	}
}
