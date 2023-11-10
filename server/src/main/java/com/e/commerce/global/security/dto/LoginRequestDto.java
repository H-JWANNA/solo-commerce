package com.e.commerce.global.security.dto;

public record LoginRequestDto(
	String username,
	String password
) {
}
