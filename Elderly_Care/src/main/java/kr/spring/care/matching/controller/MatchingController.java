package kr.spring.care.matching.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matching")
public class MatchingController {

	@GetMapping("/findcaregiver")
	public String findcaregiver() {
		return "matching/matchingfindcaregiver";
	}

	@GetMapping("/findjob")
	public String findjob() {
		return "matching/matchingfindjob";
	}


}
