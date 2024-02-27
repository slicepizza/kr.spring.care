package kr.spring.care.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.spring.care.member.entity.Member;

public interface TotalUserRepository extends JpaRepository<Member, Long>{

	@Query(value = "select * from member order by member_id desc limit :pageStart, :pageSize", nativeQuery = true)
	public List<Member> listPage(@Param("pageStart") int PageStart, @Param("pageSize") int PageSize);
	
	@Query(value = "select count(*) from member", nativeQuery = true)
	public int countAlluser();
	
	Member findByName(String name);
	Member findByNameAndAddress(String name, String address);
	List<Member> findByNameLike(String name);
	Page<Member> findAll(Pageable pageable);
}
