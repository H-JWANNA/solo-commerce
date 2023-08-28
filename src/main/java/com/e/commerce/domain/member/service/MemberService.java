package com.e.commerce.domain.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.e.commerce.domain.member.dto.RegisterRequest;
import com.e.commerce.domain.member.entity.Member;
import com.e.commerce.domain.member.mapper.MemberMapper;
import com.e.commerce.domain.member.repository.MemberRepository;
import com.e.commerce.global.security.util.CustomAuthorityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final PasswordEncoder passwordEncoder;
	private final CustomAuthorityUtils authorityUtils;
	private final MemberMapper mapper;
	private final MemberRepository memberRepository;

	public Long register(RegisterRequest request) {
		List<String> roles = authorityUtils.createRoles(request.username());
		String encryptedPassword = passwordEncoder.encode(request.password());

		Member member = mapper.toEntity(request, encryptedPassword);
		member.updateRoles(roles);

		Member savedMember = memberRepository.save(member);

		return savedMember.getMemberId();
	}

	public Member findMemberByUsername(String username) {
		Optional<Member> optionalMember = memberRepository.findByUsername(username);
		Member member = optionalMember.orElseThrow(
			() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));

		return member;
	}
}
