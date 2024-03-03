package kr.spring.care.user_page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/userPage/*")
@Controller
public class UserPageController {
	
	@GetMapping("matchingInfo")
	public String matchingInfo() {
		return "userPage/matchingInfo";
	}
	
	@GetMapping("myinfo")
	public String myinfo() {
		return "userPage/myinfo";
	}
	
}
