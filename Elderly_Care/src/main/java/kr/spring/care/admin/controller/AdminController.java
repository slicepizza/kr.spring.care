package kr.spring.care.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.care.admin.DTO.PageVO;
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
	
	// 회원관리 페이지(페이징)
	@GetMapping("totUser")
	public String totUserPage(Model model, String pageNum) {
		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		int pageSize = 3;
		int pageStart = (currentPage-1) * pageSize;
		int count = totalUserService.countAllUser();
		System.out.println("유저수"+ count);
		PageVO page = new PageVO(count, pageSize, currentPage);
		model.addAttribute("AllUser", totalUserService.userPageList(pageStart, pageSize));
		model.addAttribute("page", page);
		model.addAttribute("count", count);
		System.out.println("페이지 "+page);
		 System.out.println("유저 "+ totalUserService.userPageList(pageStart, pageSize));
		return "admin/totalUser";
	}
	
	// 회원권한 업데이트
	@PostMapping("authChange/{id}")
	public String update(@PathVariable("id") int id, Member member) {
		totalUserService.authChange(id, member);
		return "redirect:/admin/totUser";
	}
	
}
