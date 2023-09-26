package com.e.commerce.repository;

import static com.e.commerce.stub.MemberStubData.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.e.commerce.domain.member.entity.Member;
import com.e.commerce.domain.member.repository.MemberRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("[Member] Repository Test")
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Test
	@DisplayName("회원 가입")
	void register() {
		// given
		Member member = getStubMember();

		// when
		Member savedMember = memberRepository.save(member);

		// then
		assertEquals(savedMember.getUsername(), member.getUsername());
		assertEquals(savedMember.getEncryptedPassword(), member.getEncryptedPassword());
		assertEquals(savedMember.getName(), member.getName());
		assertEquals(savedMember.getEmail(), member.getEmail());
		assertEquals(savedMember.getPhoneNumber(), member.getPhoneNumber());
		assertEquals(savedMember.getRoles(), member.getRoles());
	}
}
