package kr.spring.care.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import kr.spring.care.member.dto.MemberFormDto;
import kr.spring.care.member.entity.Member;
import kr.spring.care.member.service.MemberService;
//asdf
@Controller
//@Log4j2
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")	
	public String login() {
		return "member/memberLogin";
	}

	
	@GetMapping("/joinCheck")
	public String memberCheck() {
		return "member/memberJoinCheck";
	}
	
	@GetMapping("/register")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}
	
	@GetMapping("/login/requireLogin")
	public String requireLogin(Model model) {
		model.addAttribute("loginErrorMsg", "로그인이 필요한 기능입니다");
		return "member/memberLogin";
	}
	
	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 패스워드가 잘못되었습니다.");
		return "member/memberLogin";
	}
	

	@PostMapping("/register")
	public String memberForm(@Valid MemberFormDto memberFormDto,
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		
		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);	
		} catch(IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}
		return "redirect:/";
	}
	
	
}
