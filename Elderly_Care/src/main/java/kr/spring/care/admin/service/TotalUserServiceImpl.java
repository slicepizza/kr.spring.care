package kr.spring.care.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.spring.care.admin.DTO.UserDTO;
import kr.spring.care.admin.repository.TotalUserRepository;
import kr.spring.care.member.constant.Role;
import kr.spring.care.member.entity.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TotalUserServiceImpl implements TotalUserService{
	
	private final TotalUserRepository totalUserRepository; 
	
	// 리스트(페이지+검색)
	@Override
	public Page<UserDTO> userPaging(Pageable pageable, String field, String word) {
		int page = pageable.getPageNumber() -1;
		int pageLimit = 10;
		
		Page<Member> allUserPages = totalUserRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Direction.DESC, "id")));
		if(field.equals("name")) {
			allUserPages = totalUserRepository.findByNameContaining(word, PageRequest.of(page, pageLimit, Sort.by(Direction.DESC, "id")));
		}else if(field.equals("email")) {
			allUserPages = totalUserRepository.findByEmailContaining(word, PageRequest.of(page, pageLimit, Sort.by(Direction.DESC, "id")));
		}
		Page<UserDTO> UserDTOs = allUserPages.map(
				allUserPage -> new UserDTO(allUserPage));
		System.out.println("유저dto"+ UserDTOs);
		return UserDTOs;
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
	public long countAllUser() {
		return totalUserRepository.count();
	}
	
	// 유저 수(검색)
	@Override
	public long countUser(String field, String word) {
		long count = totalUserRepository.count();
		if(field.equals("name")) {
			count = totalUserRepository.cntNameSearch(word);
		}else if(field.equals("email")) {
			count = totalUserRepository.cntEmailSearch(word);
		}
		
		return count;
	}
	
	// 유저 상세정보(모달)
	@Override
	public UserDTO userView(long id) {
//		Member mid = totalUserRepository.findById(id).get();
//		UserDTO userById = new UserDTO(mid);
//		System.out.println("유저:"+ userById);
		return null;
	}



	


	

}
