package kr.spring.care.mockdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.mockdata.entity.Senior;

public interface SeniorRepository extends JpaRepository<Senior, Long> {

}
