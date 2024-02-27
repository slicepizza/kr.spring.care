package kr.spring.care.elderly_user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("elderlyUser/*")
@Controller
public class ElderyUserController {
	
	@GetMapping("mypage")
	public String mypage() {
		return "elderly_user/mypage";
	}
	
	@GetMapping("matchingInfo")
	public String matchingInfo() {
		return "elderly_user/matchingInfo";
	}
	
	@GetMapping("myinfo")
	public String myinfo() {
		return "elderly_user/myinfo";
	}
	
}
