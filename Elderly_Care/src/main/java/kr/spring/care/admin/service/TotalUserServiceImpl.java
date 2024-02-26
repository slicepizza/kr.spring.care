package kr.spring.care.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.spring.care.admin.repository.TotalUserRepository;
import kr.spring.care.member.constant.Role;
import kr.spring.care.member.entity.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TotalUserServiceImpl implements TotalUserService{
	
	private final TotalUserRepository totalUserRepository; 
	
	@Override
	public List<Member> userList() {
		return totalUserRepository.findAll();
	}
	
	@Transactional
	@Override
	public void authChange(long id, Member member) {
		Member b = totalUserRepository.findById(id).get();
		if(member.getRole().equals(Role.CAREGIVER)) {
			b.setRole(Role.CAREGIVER);
		}else if(member.getRole().equals(Role.USER)) {
			b.setRole(Role.USER);
		}
		
	}

}
