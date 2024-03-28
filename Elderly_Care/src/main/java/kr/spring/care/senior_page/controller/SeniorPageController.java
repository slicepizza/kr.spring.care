package kr.spring.care.senior_page.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.care.senior_page.dto.SeniorDTO;
import kr.spring.care.senior_page.service.SeniorPageService;
import kr.spring.care.user.entity.User;
import kr.spring.care.user_page.dto.UserDTO;
import kr.spring.care.user_page.service.UserPageService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/seniorPage/*")
@Controller
@RequiredArgsConstructor
public class SeniorPageController {
	
	private final UserPageService userPageService;
	private final SeniorPageService seniorPageService; 
	
	// 기본사항(User) 정보
	@GetMapping("myinfo/{id}")
	public String myinfo(@PathVariable("id") long userId, Model model) {
		UserDTO userInfo = userPageService.myInfo(userId);
		SeniorDTO seniorInfo = seniorPageService.seniorInfo(userId);
		model.addAttribute("myInfo", userInfo);
		model.addAttribute("seniorInfo", seniorInfo);
		return "seniorPage/myinfo";
	}
	
	@PutMapping("edit")
	@ResponseBody
	public String edit(@RequestBody UserDTO user) {
		userPageService.editUser(user);
		System.out.println("유저메일"+user.getEmail());
		return user.getEmail();
	}
	
	@PutMapping("editPw")
	@ResponseBody
	public String editPw(@RequestBody User user) {
		userPageService.editPw(user);
		return "success";
	}
	
	
	@GetMapping("matchingInfo")
	public String matchingInfo() {
		return "seniorPage/matchingInfo";
	}
	
	
}
