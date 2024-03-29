package kr.spring.care.caregiver_page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.care.admin.DTO.CaregiverDTO;
import kr.spring.care.caregiver_page.service.CaregiverPageService;
import kr.spring.care.user_page.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@RequestMapping("/caregiverPage/*")
@Controller
@RequiredArgsConstructor
public class CaregiverPageController {
	
	private final CaregiverPageService caregiverPageService;
	
	// 기본사항(User) + 특정사항(Senior) 정보
	@GetMapping("myinfo/{id}")
	public String myinfo(@PathVariable("id") long userId, Model model) {
		UserDTO userInfo = caregiverPageService.myInfo(userId);
		CaregiverDTO caregiverInfo = caregiverPageService.caregiverInfo(userId);
		model.addAttribute("myInfo", userInfo);
		model.addAttribute("caregiverInfo", caregiverInfo);
		return "caregiverPage/myinfo";
	}
	
	
	
	@GetMapping("matchingInfo")
	public String matchingInfo() {
		return "caregiverPage/matchingInfo";
	}
	
	
}
