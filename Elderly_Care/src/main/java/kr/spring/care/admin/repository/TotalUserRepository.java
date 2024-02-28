package kr.spring.care.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.spring.care.member.entity.Member;

public interface TotalUserRepository extends JpaRepository<Member, Long>{
	
	public Page<Member> findByNameContaining(String word, Pageable pageable);
	public Page<Member> findByEmailContaining(String word, Pageable pageable);
	public Page<Member> findByRoleContaining(String word, Pageable pageable);
	
	@Query(value = "select count(*) from member where name like CONCAT('%',:word,'%')", nativeQuery = true)
	public long cntNameSearch(@Param("word") String word);
	
	@Query(value = "select count(*) from member where email like CONCAT('%',:word,'%')", nativeQuery = true)
	public long cntEmailSearch(@Param("word") String word);
	
	
}
