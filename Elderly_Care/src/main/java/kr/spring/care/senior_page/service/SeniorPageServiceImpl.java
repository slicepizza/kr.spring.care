package kr.spring.care.senior_page.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.care.senior_page.dto.SeniorDTO;
import kr.spring.care.senior_page.repository.SeniorPageRepository;
import kr.spring.care.user.entity.Senior;
import kr.spring.care.user.entity.User;
import kr.spring.care.user_page.dto.UserDTO;
import kr.spring.care.user_page.repository.UserPageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeniorPageServiceImpl implements SeniorPageService{

	private final UserPageRepository userPageRepository;
	private final SeniorPageRepository seniorPageRepository; 
	private final PasswordEncoder passwordEncoder; 
	
	@Override
	public UserDTO myInfo(long userId) {
		Optional<User> userOptional = userPageRepository.findById(userId);
		return userOptional.map(UserDTO::new).orElse(null); 
	}
	
	@Override
	public SeniorDTO seniorInfo(long userId) {
		Optional<Senior> seniorOptional = seniorPageRepository.findById(userId);
		SeniorDTO s = seniorOptional.map(SeniorDTO::new).orElse(null);
		System.out.println("시니어 "+ s.getHealth() + s.getSeniorName() + s.getRequirements() + s.getHasGuardian());
		return seniorOptional.map(SeniorDTO::new).orElse(null);
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

	


	

	

}
