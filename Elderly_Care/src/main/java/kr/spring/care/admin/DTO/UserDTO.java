package kr.spring.care.admin.DTO;

import java.time.LocalDateTime;

import kr.spring.care.user.constant.Role;
import kr.spring.care.user.entity.User;
import lombok.Getter;

@Getter
public class UserDTO {

	private Long userId;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private Role role;
	private String country;
	
	private LocalDateTime createdAt;
	
	public UserDTO(User entity) {
		this.userId = entity.getUserId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.address = entity.getAddress();
		this.role = entity.getRole();
		this.createdAt = entity.getCreatedAt();
	}
	
}
