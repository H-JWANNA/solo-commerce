package com.e.commerce.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e.commerce.domain.member.dto.RegisterRequest;
import com.e.commerce.domain.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class RegisterController {
	private final MemberService memberService;

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterRequest request) {
		Long savedMemberId = memberService.register(request);
		log.info("Register Member Id : {}", savedMemberId);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedMemberId);
	}
}
