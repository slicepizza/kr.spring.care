package kr.spring.care.mockdata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.mockdata.entity.Senior;
import kr.spring.care.mockdata.entity.User;

public interface SeniorRepository extends JpaRepository<Senior, Long> {
    Optional<Senior> findByUser(User user);
}
