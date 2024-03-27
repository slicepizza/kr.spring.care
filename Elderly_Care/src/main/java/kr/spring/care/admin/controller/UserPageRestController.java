package kr.spring.care.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import kr.spring.care.user_page.dto.UserDTO;
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
	
	@PostMapping("regUser")
	public ResponseEntity<String> regUser(@RequestBody UserDTO user){
		System.out.println("호출성공");
		userPageService.regUSer(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("회원 등록 성공!");
	}
	
	@GetMapping("/csrf-token")
	public ResponseEntity<Map<String, String>> getCsrfToken(HttpServletRequest request) {
	    CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	    Map<String, String> tokenMap = new HashMap<>();
	    tokenMap.put("token", token.getToken());
	    tokenMap.put("headerName", token.getHeaderName());
	    return ResponseEntity.ok(tokenMap);
	}


}
