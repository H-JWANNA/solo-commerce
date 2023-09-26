package com.e.commerce.service;

import static com.e.commerce.stub.MemberStubData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.e.commerce.domain.member.dto.RegisterRequest;
import com.e.commerce.domain.member.entity.Member;
import com.e.commerce.domain.member.repository.MemberRepository;
import com.e.commerce.domain.member.service.MemberService;
import com.e.commerce.global.security.util.CustomAuthorityUtils;

@DisplayName("[Register] Service Test")
@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
	@InjectMocks
	private MemberService memberService;

	@Mock
	private CustomAuthorityUtils authorityUtils;

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Test
	@DisplayName("회원 가입")
	void register() {
		// given
		RegisterRequest request = getStubRegister();
		Member member = getStubMember();
		List<String> roles = List.of("USER");

		given(authorityUtils.createRoles(anyString())).willReturn(roles);
		given(memberRepository.save(any(Member.class))).willReturn(member);

		// when
		Long memberId = memberService.register(request);

		// then
		assertAll(
			() -> assertEquals(member.getMemberId(), memberId),
			() -> verify(passwordEncoder, times(1)).encode(anyString()),
			() -> verify(memberRepository, times(1)).save(any(Member.class))
		);
	}
}
