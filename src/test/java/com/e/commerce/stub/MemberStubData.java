package com.e.commerce.stub;

import com.e.commerce.domain.member.dto.RegisterRequest;

public class MemberStubData {
	public static RegisterRequest getStubRegister() {
		return new RegisterRequest(
			"username1",
			"12341234",
			"name",
			"email1@email.oo",
			"010-5432-2134"
		);
	}
}
