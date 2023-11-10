package com.e.commerce.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
	MEMBER_NOT_FOUND(40401, "Member not found"),

	INCORRECT_SIGNATURE(40402, "Mismatched signature"),
	EXPIRED_JWT(41001, "Jwt expired"),
	INVALID_JWT(42201, "Jwt invalid"),
	NOT_EXIST_REFRESH_TOKEN(40403, "Refresh token is not exist"),

	REDIS_ERROR(40404, "Redis connection error");

	final int code;
	final String message;
}
