package kr.spring.care.matching.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.matching.entity.Matching;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
}
