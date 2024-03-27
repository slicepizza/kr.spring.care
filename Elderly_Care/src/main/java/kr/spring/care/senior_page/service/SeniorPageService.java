package kr.spring.care.senior_page.service;


import kr.spring.care.user.entity.User;
import kr.spring.care.user_page.dto.UserDTO;

public interface SeniorPageService {

	public User getUser(String email);
	
	public UserDTO myInfo(long userId);
	
	public void editUser(User user);
	
	public void editPw(User user);
	
	
	
}
