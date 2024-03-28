package kr.spring.care.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.user.entity.Guardian;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {
    
}
