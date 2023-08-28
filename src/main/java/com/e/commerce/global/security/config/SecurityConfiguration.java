package com.e.commerce.global.security.config;

import static org.springframework.security.config.Customizer.*;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	// 정적 자원에 대한 Security Ignoring 처리
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
			.requestMatchers(HttpMethod.POST, "/registers");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers("*").permitAll();
				auth.requestMatchers("auths/**").permitAll();
				auth.requestMatchers("members/**").hasRole("USER");
			});

		http
			.formLogin(form -> form
				.usernameParameter("username")
				.passwordParameter("password")
				.loginPage("/auths/login-form")
				.loginProcessingUrl("/process_login")
				.failureUrl("/auths/login-form?error"));

		http
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/"));

		http
			.exceptionHandling(handle -> handle
				.accessDeniedPage("/auths/access-denied"));

		http.formLogin(withDefaults());

		/*
			동시 세션 관련 설정
			- maximumSessions : 최대 허용 가능 세션 수
			- maxSessionsPreventsLogin : 최대 허용 세션 수에서 추가로 인증이 들어올 경우
				true : 인증 실패, false : 기존 세션 만료
			- expiredUrl : 세션 만료된 경우 이동할 페이지
		*/
		http.sessionManagement(session ->
			session
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.maximumSessions(1)
				.maxSessionsPreventsLogin(false)
				.expiredUrl("/session-expired"));

		return http.build();
	}

	// 만료된 세션 정리
	@Bean
	public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
