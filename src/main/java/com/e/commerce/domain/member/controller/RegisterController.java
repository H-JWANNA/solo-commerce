package com.e.commerce.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.e.commerce.domain.member.dto.RegisterRequest;
import com.e.commerce.domain.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("register")
@RequiredArgsConstructor
public class RegisterController {
	private final MemberService memberService;

	@GetMapping
	public String registerForm() {
		log.info("### register-form");
		return "register";
	}

	@PostMapping
	public String register(@Valid RegisterRequest request) {
		log.info("### request username {}", request.username());
		Long savedMemberId = memberService.register(request);
		log.info("### Register Member Id : {}", savedMemberId);

		return "login";
	}
}
