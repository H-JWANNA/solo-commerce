package com.e.commerce.domain.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.e.commerce.domain.member.dto.RegisterRequest;
import com.e.commerce.domain.member.entity.Member;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

	@Mapping(source = "request.username", target = "username")
	@Mapping(source = "encryptedPassword", target = "encryptedPassword")
	@Mapping(source = "request.name", target = "name")
	@Mapping(source = "request.email", target = "email")
	@Mapping(source = "request.phoneNumber", target = "phoneNumber")
	Member toEntity(RegisterRequest request, String encryptedPassword);
}
