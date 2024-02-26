package kr.spring.care.member.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.spring.care.member.constant.Role;
import kr.spring.care.member.dto.MemberFormDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime; // LocalDateTime을 사용하여 날짜 및 시간 관리

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    
    private String name;
    
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    private boolean agreeEvent;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Column(name = "created_at", nullable = false, updatable = false) // 수정 불가능한 생성 날짜
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at") // 수정 가능한 수정 날짜
    private LocalDateTime updatedAt;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        member.setRole(Role.USER);
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setCreatedAt(LocalDateTime.now()); // 생성 날짜 설정
        member.setUpdatedAt(LocalDateTime.now()); 
        return member;
    }
}













