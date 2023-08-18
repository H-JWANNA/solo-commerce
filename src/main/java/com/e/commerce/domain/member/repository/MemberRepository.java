package com.e.commerce.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e.commerce.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
