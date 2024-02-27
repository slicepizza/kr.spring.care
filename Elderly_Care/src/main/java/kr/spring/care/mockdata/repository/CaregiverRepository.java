package kr.spring.care.mockdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.mockdata.entity.Caregiver;

public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {

}
