package com.e.commerce.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.e.commerce.domain.member.dto.RegisterRequest;
import com.e.commerce.domain.member.entity.Member;
import com.e.commerce.domain.member.mapper.MemberMapper;
import com.e.commerce.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final PasswordEncoder passwordEncoder;
	private final MemberMapper mapper;
	private final MemberRepository repository;

	public Long register(RegisterRequest request) {
		String encryptedPassword = passwordEncoder.encode(request.password());
		Member member = mapper.toEntity(request, encryptedPassword);
		Member savedMember = repository.save(member);

		return savedMember.getMemberId();
	}
}
