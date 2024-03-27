package kr.spring.care.senior_page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.spring.care.user.entity.Senior;
import kr.spring.care.user.entity.User;

public interface SeniorPageRepository extends JpaRepository<Senior, Long>{

	User findByEmail(String email);
}
