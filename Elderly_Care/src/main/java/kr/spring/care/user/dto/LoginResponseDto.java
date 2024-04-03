package kr.spring.care.user.dto;

import kr.spring.care.user.constant.Role;

public class LoginResponseDto {
    private String email;
    private Role role;
    
    public LoginResponseDto(String email, Role role) {
        this.email = email;
        this.setRole(role);
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}