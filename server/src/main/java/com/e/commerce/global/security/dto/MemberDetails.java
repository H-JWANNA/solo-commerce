package com.e.commerce.global.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.e.commerce.domain.member.entity.Member;
import com.e.commerce.global.security.util.CustomAuthorityUtils;

import lombok.Getter;

@Getter
public class MemberDetails extends Member implements UserDetails {
	private String username;
	private String password;
	private String name;
	private String email;
	private String phoneNumber;
	private Integer point;
	private Collection<GrantedAuthority> authorities;

	public MemberDetails(Member member, CustomAuthorityUtils authorityUtils) {
		this.username = member.getUsername();
		this.password = member.getEncryptedPassword();
		this.name = member.getName();
		this.email = member.getEmail();
		this.phoneNumber = member.getPhoneNumber();
		this.point = member.getPoint();
		this.authorities = authorityUtils.createAuthorities(member.getRoles());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
