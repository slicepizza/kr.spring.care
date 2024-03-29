package kr.spring.care.user_page.dto;

import java.time.LocalDateTime;

import kr.spring.care.user.constant.Role;
import kr.spring.care.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {

	private Long userId;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private String gender;
	private Role role;
	private String country;
	private String image;
	

	private Boolean hasGuardian;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public UserDTO(User entity) {
		this.userId = entity.getUserId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.address = entity.getAddress();
		this.gender = entity.getGender();
		this.phoneNumber = entity.getPhoneNumber();
		this.role = entity.getRole();
		this.country = entity.getCountry();
		this.image = entity.getImage();
		this.createdAt = entity.getCreatedAt();

		this.image = entity.getImage();
	}
	
}
