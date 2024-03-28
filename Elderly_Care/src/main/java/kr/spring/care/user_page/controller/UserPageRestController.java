package kr.spring.care.user_page.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.spring.care.admin.DTO.UserDTO;
import kr.spring.care.user_page.service.UserPageService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/userPage")
public class UserPageRestController {

	private final UserPageService userPageService;
	
	@GetMapping("/myinfo/{id}")
	public ResponseEntity<UserDTO> myinfo(@PathVariable("id") long userId) {
		UserDTO userInfo = userPageService.myInfo(userId);
		System.out.println("앱 데이터"+ userInfo);
		return ResponseEntity.ok(userInfo);
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<String> saveUser(@RequestBody UserDTO user){
		System.out.println("호출성공"+ user);
		return ResponseEntity.status(HttpStatus.CREATED).body("회원 등록 성공!");
	}
}
