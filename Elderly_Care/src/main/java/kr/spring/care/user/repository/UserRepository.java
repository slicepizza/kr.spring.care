package kr.spring.care.user.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.user.constant.Role;
import kr.spring.care.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);

	Page<User> findByRole(Role role, Pageable pageable);

	User findByEmail(String username);
	
    // 이름으로 검색
    Page<User> findByNameContaining(String name, Pageable pageable);

    // 이메일로 검색
    Page<User> findByEmailContaining(String email, Pageable pageable);

    // 지역으로 검색
    Page<User> findByCountryContaining(String country, Pageable pageable);
}
