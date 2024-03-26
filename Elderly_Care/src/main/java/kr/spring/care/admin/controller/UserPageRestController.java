package kr.spring.care.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.spring.care.admin.DTO.UserDTO;
import kr.spring.care.user_page.service.UserPageService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/userPage/")
public class UserPageRestController {
	
private final UserPageService userPageService;
	
	@GetMapping("myinfo/{id}")
	public ResponseEntity<UserDTO> myinfo(@PathVariable("id") long userId) {
		System.out.println("호출성공");
		UserDTO userdto = userPageService.myInfo(userId);
		System.out.println("사용자정보"+ userdto.getEmail());
		return ResponseEntity.ok(userdto);
	}

}
