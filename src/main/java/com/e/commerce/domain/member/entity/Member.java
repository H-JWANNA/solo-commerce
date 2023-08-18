package com.e.commerce.domain.member.entity;

import com.e.commerce.domain.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String encryptedPassword;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "phone_number", nullable = false, unique = true)
	private String phoneNumber;

	@Builder.Default
	@Column(name = "point", nullable = false)
	private Integer point = 0;
}
