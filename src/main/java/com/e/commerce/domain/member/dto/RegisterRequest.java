package com.e.commerce.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
	@NotNull(message = "아이디를 입력해주세요.")
	@Size(min = 4, max = 12,
		message = "4글자 이상 12글자 이하로 입력해주세요.")
	@Pattern(regexp = "^[a-z0-9]+$",
		message = "영문 소문자 및 숫자만 사용 가능합니다.")
	String username,

	@NotNull(message = "비밀번호를 입력해주세요.")
	@Size(min = 8,
		message = "최소 8자리 이상으로 설정해주세요.")
	String password,

	@NotNull(message = "이름을 입력해주세요.")
	@Pattern(regexp = "^[가-힇a-zA-Z]+$",
		message = "한글 또는 영문만 입력 가능합니다.")
	String name,

	@Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$",
		message = "올바른 이메일 형식을 입력해주세요.")
	String email,

	@NotNull(message = "전화번호를 입력해주세요.")
	@Pattern(regexp = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$",
		message = "-를 포함하여 입력해주세요.")
	String phoneNumber
) {
}
