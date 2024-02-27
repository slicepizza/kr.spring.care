package kr.spring.care.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	// 리스트(페이지)
	@Override
	public List<Member> userPageList(int PageStart, int PageSize) {
		return totalUserRepository.listPage(PageStart, PageSize);
	}
	
	
	// 권한 변경
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

	// 전체유저 수
	@Override
	public int countAllUser() {
		return totalUserRepository.countAlluser();
	}

}
