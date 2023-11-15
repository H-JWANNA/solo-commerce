package com.e.commerce.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e.commerce.domain.member.dto.MemberResponseDto;
import com.e.commerce.domain.member.dto.RegisterRequest;
import com.e.commerce.domain.member.service.MemberService;
import com.e.commerce.global.security.dto.MemberDetails;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterRequest request) {
		log.info("### request username {}", request.username());
		Long savedMemberId = memberService.register(request);
		log.info("### Register Member Id : {}", savedMemberId);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedMemberId);
	}

	@GetMapping
	public ResponseEntity getMember(@AuthenticationPrincipal MemberDetails member) {
		log.info("### getMember : {}", member);
		MemberResponseDto response = memberService.getMember(member);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
