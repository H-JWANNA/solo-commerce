package com.e.commerce.global.exception;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException {
	@Getter
	private final ExceptionCode exceptionCode;

	public BusinessLogicException(ExceptionCode e) {
		super(e.getMessage());
		this.exceptionCode = e;
	}
}
