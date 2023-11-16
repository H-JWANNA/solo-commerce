package com.e.commerce.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition(
	info = @Info(
		title = "Solo-Commerce API Docs",
		description = "프로젝트 전체 API 명세서",
		version = "v2"
	)
)
@Configuration
public class SwaggerConfig {
	private static final String BEARER_TOKEN_PREFIX = "Bearer";
	private static final String AUTHORIZATION_HEADER = "Authorization";

	@Bean
	public OpenAPI openAPI() {
		SecurityScheme securityScheme = new SecurityScheme()
			.name(AUTHORIZATION_HEADER)
			.type(SecurityScheme.Type.HTTP)
			.scheme(BEARER_TOKEN_PREFIX)
			.bearerFormat("JWT");

		SecurityRequirement securityRequirement = new SecurityRequirement()
			.addList(AUTHORIZATION_HEADER);

		Components components = new Components()
			.addSecuritySchemes(AUTHORIZATION_HEADER, securityScheme);

		return new OpenAPI()
			.addSecurityItem(securityRequirement)
			.components(components);
	}
}
