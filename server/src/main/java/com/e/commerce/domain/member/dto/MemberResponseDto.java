package com.e.commerce.domain.member.dto;

import com.e.commerce.domain.member.entity.Member;

public record MemberResponseDto(
	String username,
	String name,
	String email,
	String phoneNumber,
	Integer point
) {
	public static MemberResponseDto from(Member member) {
		return new MemberResponseDto(
			member.getUsername(),
			member.getName(),
			member.getEmail(),
			member.getPhoneNumber(),
			member.getPoint()
		);
	}
}
