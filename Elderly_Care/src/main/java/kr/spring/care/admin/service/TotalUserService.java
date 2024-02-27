package kr.spring.care.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import kr.spring.care.member.entity.Member;

public interface TotalUserService {
	// 유저리스트(페이징)
	public List<Member> userPageList(int pageStart, int pageSize);
//	public Page<Member> userPageList(int page);
//	public 
	
	// 권한 변경
	public void authChange(long id, Member member);
	
	// 전체유저 수
	public int countAllUser();
	
	
}
