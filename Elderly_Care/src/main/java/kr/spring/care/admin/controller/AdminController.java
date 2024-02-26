package kr.spring.care.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/admin/*")
@Controller
@RequiredArgsConstructor
public class AdminController {
	
	// 관리자 마이페이지
	@GetMapping("mypage")
	public String mypage() {
		return "admin/mypage";
	}
	
	// 게시판관리 페이지
	@GetMapping("totBoard")
	public String totBoard() {
		return "admin/totalBoard";
	}
	
	// 회원관리 페이지
	@GetMapping("totUser")
	public String totUser() {
		return "admin/totalUser";
	}
	
	
	
}
