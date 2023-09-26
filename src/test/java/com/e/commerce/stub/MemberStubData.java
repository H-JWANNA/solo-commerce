package com.e.commerce.stub;

import java.util.List;

import com.e.commerce.domain.member.dto.RegisterRequest;
import com.e.commerce.domain.member.entity.Member;

public class MemberStubData {
	public static RegisterRequest getStubRegister() {
		return new RegisterRequest(
			"username1test",
			"12341234",
			"nametest",
			"email1@email.oo",
			"010-5432-2134"
		);
	}

	public static Member getStubMember() {
		return Member.builder()
			.username("username1test")
			.encryptedPassword("12341234")
			.name("nametest")
			.email("email1@email.oo")
			.phoneNumber("010-5432-2134")
			.roles(List.of("USER"))
			.build();
	}
}
