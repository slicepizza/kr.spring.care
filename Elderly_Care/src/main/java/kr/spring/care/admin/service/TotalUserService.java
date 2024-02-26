package kr.spring.care.admin.service;

import java.util.List;

import kr.spring.care.member.entity.Member;

public interface TotalUserService {

	public List<Member> userList();
	
	public void authChange(long id, Member member);
}
