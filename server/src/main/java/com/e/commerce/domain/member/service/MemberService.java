package com.e.commerce.domain.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e.commerce.domain.member.dto.MemberResponseDto;
import com.e.commerce.domain.member.dto.RegisterRequest;
import com.e.commerce.domain.member.entity.Member;
import com.e.commerce.domain.member.repository.MemberRepository;
import com.e.commerce.global.security.util.CustomAuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {
	private final PasswordEncoder passwordEncoder;
	private final CustomAuthorityUtils authorityUtils;
	private final MemberRepository memberRepository;

	@Transactional
	public Long register(RegisterRequest request) {
		Member member = Member.builder()
			.username(request.username())
			.encryptedPassword(passwordEncoder.encode(request.password()))
			.name(request.name())
			.email(request.email())
			.phoneNumber(request.phoneNumber())
			.build();

		List<String> roles = authorityUtils.createRoles(request.username());
		member.updateRoles(roles);

		Member savedMember = memberRepository.save(member);

		return savedMember.getMemberId();
	}

	@Transactional
	public MemberResponseDto getMember(Member member) {
		return MemberResponseDto.from(member);
	}

	@Transactional(readOnly = true)
	public Member findMemberByUsername(String username) {
		Optional<Member> optionalMember = memberRepository.findByUsername(username);
		Member member = optionalMember.orElseThrow(
			() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));

		return member;
	}
}
