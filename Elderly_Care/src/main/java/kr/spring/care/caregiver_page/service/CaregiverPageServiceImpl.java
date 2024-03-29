package kr.spring.care.caregiver_page.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.care.admin.DTO.CaregiverDTO;
import kr.spring.care.caregiver_page.repository.CaregiverPageRepository;
import kr.spring.care.senior_page.dto.SeniorDTO;
import kr.spring.care.senior_page.repository.SeniorPageRepository;
import kr.spring.care.user.entity.Caregiver;
import kr.spring.care.user.entity.Senior;
import kr.spring.care.user.entity.User;
import kr.spring.care.user_page.dto.UserDTO;
import kr.spring.care.user_page.repository.UserPageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CaregiverPageServiceImpl implements CaregiverPageService{

	private final UserPageRepository userPageRepository;
	private final CaregiverPageRepository caregiverPageRepository;
	private final PasswordEncoder passwordEncoder; 
	
	@Override
	public UserDTO myInfo(long userId) {
		Optional<User> userOptional = userPageRepository.findById(userId);
		return userOptional.map(UserDTO::new).orElse(null); 
	}
	
	@Override
	public CaregiverDTO caregiverInfo(long userId) {
		  Caregiver cagiver = caregiverPageRepository.findByUser_userId(userId).get();
		    if (cagiver != null) {
		        // Senior 객체가 존재하는 경우, SeniorDTO 객체 생성
		    	CaregiverDTO cd = new CaregiverDTO(cagiver);
		        return cd;
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
		Caregiver bfCare = caregiverPageRepository.findByUser_userId(userDTO.getUserId()).get();
//		bfCare.setAvailableHours(userDTO.getAvailableHours());
//		bfCare.setCertification(userDTO.getCertification());
//		bfCare.setExperience(userDTO.getExperience());
//		bfCare.setExperienceYears(userDTO.getExperienceYears());
//		bfCare.setSpecialization(userDTO.getSpecialization());
////		userPageRepository.save(bfCare);
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
