package kr.spring.care.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.care.admin.service.TotalUserService;
import kr.spring.care.member.entity.Member;
import lombok.RequiredArgsConstructor;

@RequestMapping("/admin/*")
@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final TotalUserService totalUserService; 
	
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
	public String totUser(Model model) {
		model.addAttribute("AllUser", totalUserService.userList());
		return "admin/totalUser";
	}
	
	// 회원권한 업데이트
	@PostMapping("authChange/{id}")
	public String update(@PathVariable("id") long id, Member member) {
		totalUserService.authChange(id, member);
		return "redirect:/admin/totalUser";
	}
	
}
