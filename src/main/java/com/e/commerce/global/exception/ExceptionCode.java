package com.e.commerce.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
	MEMBER_NOT_FOUND(40401, "Member not found");

	final int code;
	final String message;
}
