package kr.spring.care.admin.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.care.admin.DTO.UserDTO;
import kr.spring.care.admin.service.TotalUserService;
import kr.spring.care.mockdata.entity.User;
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
	
	// 회원관리 페이지(페이징)
	@GetMapping("totUser")
	public String userListPaging(Model model,
			@PageableDefault(page = 1) Pageable pageable,
			@RequestParam(required = false, defaultValue = "")String field,
			@RequestParam(required = false, defaultValue = "")String word) {
		Page<UserDTO> alluser = totalUserService.userPaging(pageable, field, word);
		long allCount = totalUserService.countAllUser();
		long count = totalUserService.countUser(field, word);
		
		int blockLimit = 5;
		int startPage = (((int)Math.ceil((double)pageable.getPageNumber() / blockLimit)) -1) * blockLimit +1;   
		int endPage = Math.min((startPage + blockLimit -1), alluser.getTotalPages());	
		
		model.addAttribute("allUser", alluser);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("AlluserCnt", allCount);
		model.addAttribute("userCnt", count);
		
		
		return "admin/totalUser";
	}
	
	// 회원권한 업데이트
	@PostMapping("authChange/{userId}")
	public String update(@PathVariable("userId") long userId, User user) {
		totalUserService.authChange(userId, user);
		return "redirect:/admin/totUser";
	}
	
	// 회원 상세정보(모달)
	@GetMapping("userView/{id}")
	public String userView(@PathVariable("id") long id, Model model) {
		System.out.println("유저아디"+ id);
		model.addAttribute("userById", totalUserService.userView(id));
		return "admin/totalUser :: modalContents";
	}
	
}
