package com.e.commerce.global.security.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthorityUtils {
	private final String ADMIN_USERNAME = "admin";

	private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "OWNER", "USER");
	private final List<String> USER_ROLES_STRING = List.of("USER");

	// DB에 저장된 Role 기반으로 권한 정보 생성
	public List<GrantedAuthority> createAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = roles.stream()
			.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
			.collect(Collectors.toList());

		log.info("Create Authorities : {}", authorities);

		return authorities;
	}

	public List<String> createRoles(String username) {
		if (username.equals(ADMIN_USERNAME)) {
			return ADMIN_ROLES_STRING;
		}

		return USER_ROLES_STRING;
	}
}
