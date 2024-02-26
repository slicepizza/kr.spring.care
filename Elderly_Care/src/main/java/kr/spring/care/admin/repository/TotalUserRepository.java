package kr.spring.care.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.member.entity.Member;

public interface TotalUserRepository extends JpaRepository<Member, Long>{

}
