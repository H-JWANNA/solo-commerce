package com.e.commerce.global.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.e.commerce.domain.member.entity.Member;
import com.e.commerce.domain.member.service.MemberService;
import com.e.commerce.global.security.dto.MemberDetails;
import com.e.commerce.global.security.util.CustomAuthorityUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
	private final MemberService memberService;
	private final CustomAuthorityUtils authorityUtils;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberService.findMemberByUsername(username);

		log.info("Load User By Username : {}", member);

		return new MemberDetails(member, authorityUtils);
	}
}
