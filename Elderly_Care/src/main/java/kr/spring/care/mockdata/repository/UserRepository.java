package kr.spring.care.mockdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.mockdata.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
