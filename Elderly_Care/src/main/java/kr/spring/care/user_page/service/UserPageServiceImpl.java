package kr.spring.care.user_page.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.care.admin.DTO.UserDTO;
import kr.spring.care.user.entity.User;
import kr.spring.care.user_page.repository.UserPageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPageServiceImpl implements UserPageService{

	private final UserPageRepository userPageRepository;
	private final PasswordEncoder passwordEncoder; 
	
	@Override
	public UserDTO myInfo(long userId) {
		Optional<User> userOptional = userPageRepository.findById(userId);
		return userOptional.map(UserDTO::new).orElse(null); 
	}

	@Override
	public User getUser(String email) {
		return userPageRepository.findByEmail(email);
		 
	}

	@Override
	@Transactional
	public void editUser(User user) {
		User bfUser = userPageRepository.findById(user.getUserId()).get();
		bfUser.setAddress(user.getAddress());
		bfUser.setGender(user.getGender());
		bfUser.setName(user.getName());
		bfUser.setPhoneNumber(user.getPhoneNumber());
		userPageRepository.save(bfUser);
	}

	@Override
	@Transactional
	public void editPw(User user) {
		User bfUser = userPageRepository.findById(user.getUserId()).get();
		String afPw = passwordEncoder.encode(user.getPassword());
		bfUser.setPassword(afPw);
		userPageRepository.save(bfUser);
	}

	@Override
	public void saveUser(UserDTO user) {
		User u = new User();
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setPhoneNumber(user.getPhoneNumber());
		userPageRepository.save(u);
	}

}
