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
		System.out.println("사용자아뒤"+ userId);
		Optional<User> userOptional = userPageRepository.findById(userId);
		return userOptional.map(UserDTO::new).orElse(null); 
	}
	
	@Override
	public SeniorDTO seniorInfo(long userId) {
		 Senior senior = seniorPageRepository.findByUserId(userId);
		    if (senior != null) {
		        // Senior 객체가 존재하는 경우, SeniorDTO 객체 생성
		        SeniorDTO s = new SeniorDTO(senior);
		        // 로그 출력
		        System.out.println("시니어 " + s.getUser().getUserId() + s.getHealth() + s.getSeniorName() + s.getRequirements() + s.getHasGuardian());
		        // 생성된 SeniorDTO 객체 반환
		        return s;
		    } else {
		        // Senior 객체가 존재하지 않는 경우, null 반환
		        return null;
		    }
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
