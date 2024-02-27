package kr.spring.care.mockdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.mockdata.entity.Guardian;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {

}
