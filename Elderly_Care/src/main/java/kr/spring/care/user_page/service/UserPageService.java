package kr.spring.care.user_page.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import kr.spring.care.admin.DTO.UserDTO;
import kr.spring.care.user.entity.User;

public interface UserPageService {

	public User getUser(String email);
	
	public UserDTO myInfo(long userId);
	
	public void editUser(User user);
	
	public void editPw(User user);
	
	//
	public void saveUser(UserDTO user);
	
}
