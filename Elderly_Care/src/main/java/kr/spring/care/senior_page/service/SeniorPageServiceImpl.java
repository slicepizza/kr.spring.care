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
		 Optional<Senior> seniorOptional = seniorPageRepository.findByUserUserId(userId);
		 Senior senior = seniorOptional.get();
		    if (senior != null) {
		        // Senior 객체가 존재하는 경우, SeniorDTO 객체 생성
		        SeniorDTO s = new SeniorDTO(senior);
		        return s;
		    } else {
		        return null;
		    }
	}


	@Override
	@Transactional
	public void editUser(UserDTO userDTO) {
		User bfUser = userPageRepository.findById(userDTO.getUserId()).get();
		bfUser.setAddress(userDTO.getAddress());
		bfUser.setGender(userDTO.getGender());
		bfUser.setName(userDTO.getName());
		bfUser.setPhoneNumber(userDTO.getPhoneNumber());
		Senior bfSenior = seniorPageRepository.findByUserUserId(userDTO.getUserId()).get();
		bfSenior.setHasGuardian(userDTO.getHasGuardian());
		bfSenior.setHealth(userDTO.getHealth());
		bfSenior.setRequirements(userDTO.getRequirements());
		bfUser.setSenior(bfSenior);
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
