package kr.spring.care.admin.DTO;

import java.time.LocalDateTime;

import kr.spring.care.member.constant.Role;
import kr.spring.care.member.entity.Member;
import lombok.Getter;

@Getter
public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private String address;
	private Role role;
	private LocalDateTime createdAt;
	
	public UserDTO(Member entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.address = entity.getAddress();
		this.role = entity.getRole();
		this.createdAt = entity.getCreatedAt();
	}
	
}
