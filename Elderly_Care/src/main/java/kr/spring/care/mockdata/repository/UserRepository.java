package kr.spring.care.mockdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.care.mockdata.constant.Role;
import kr.spring.care.mockdata.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);

}
