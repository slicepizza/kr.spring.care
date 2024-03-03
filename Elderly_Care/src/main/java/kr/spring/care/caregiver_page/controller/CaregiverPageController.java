package kr.spring.care.caregiver_page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/caregiverPage/*")
@Controller
public class CaregiverPageController {
	
	@GetMapping("matchingInfo")
	public String matchingInfo() {
		return "caregiverPage/matchingInfo";
	}
	
	@GetMapping("myinfo")
	public String myinfo() {
		return "caregiverPage/myinfo";
	}
}
