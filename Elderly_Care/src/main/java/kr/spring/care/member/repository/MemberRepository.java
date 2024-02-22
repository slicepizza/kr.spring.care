package kr.spring.care.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Member findByEmail(String email);
}
