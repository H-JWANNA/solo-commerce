package com.e.commerce.global.security.provider;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.e.commerce.domain.member.entity.Member;
import com.e.commerce.domain.member.service.MemberService;
import com.e.commerce.global.security.util.CustomAuthorityUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private final MemberService memberService;
	private final CustomAuthorityUtils authorityUtils;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;

		String username = token.getName();
		Optional.ofNullable(username).orElseThrow(
			() -> new UsernameNotFoundException("Invalid Username or Password"));

		Member member = memberService.findMemberByUsername(username);

		String password = member.getEncryptedPassword();
		verifyCredentials(authentication.getCredentials(), password);

		Collection<GrantedAuthority> authorities = authorityUtils.createAuthorities(member.getRoles());

		log.info("Authenticate : {}", username);

		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

	private void verifyCredentials(Object credentials, String password) {
		if (!passwordEncoder.matches((String)credentials, password)) {
			throw new BadCredentialsException("Invalid Username or Password");
		}
	}
}
