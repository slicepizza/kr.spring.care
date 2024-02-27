package kr.spring.care.mockdata.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import kr.spring.care.mockdata.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private String country;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // 관계 설정 (예: Caregiver, Senior, Guardian과의 연결)
    @OneToOne(mappedBy = "user")
    private Caregiver caregiver;

    @OneToOne(mappedBy = "user")
    private Senior senior;

    @OneToOne(mappedBy = "user")
    private Guardian guardian;
}