package kr.spring.care.user.entity;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import kr.spring.care.user.constant.Role;
import kr.spring.care.user.dto.UserFormDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
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
    private String gender;
    private String image;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // 관계 설정 (예: Caregiver, Senior, Guardian과의 연결)
    @OneToOne(mappedBy = "user")
    private Caregiver caregiver;

    @OneToOne(mappedBy = "user")
    private Senior senior;

    @OneToOne(mappedBy = "user")
    private Guardian guardian;
    
    //User 생성
    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {
    	User user = new User();
    	user.setName(userFormDto.getName());
    	user.setEmail(userFormDto.getEmail());
    	user.setAddress(userFormDto.getAddress());
    	user.setRole(Role.USER);
    	String password = passwordEncoder.encode(userFormDto.getPassword());
    	user.setPassword(password);
    	user.setCreatedAt(LocalDateTime.now());
    	user.setUpdatedAt(LocalDateTime.now());
    	return user;
    }
}
