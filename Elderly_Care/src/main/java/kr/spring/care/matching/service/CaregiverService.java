package kr.spring.care.matching.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.spring.care.matching.dto.CaregiverDetail;
import kr.spring.care.user.constant.Role;
import kr.spring.care.user.entity.Caregiver;
import kr.spring.care.user.entity.User;
import kr.spring.care.user.repository.CaregiverRepository;
import kr.spring.care.user.repository.UserRepository;

@Service
public class CaregiverService {
    private final CaregiverRepository caregiverRepository;
    private final UserRepository userRepository;

    public CaregiverService(CaregiverRepository caregiverRepository, UserRepository userRepository) {
        this.caregiverRepository = caregiverRepository;
        this.userRepository = userRepository;
    }

    public List<CaregiverDetail> findAllCaregivers() {
        List<User> caregiverUsers = userRepository.findByRole(Role.CAREGIVER);
        List<CaregiverDetail> caregiverDetails = new ArrayList<>();

        for (User user : caregiverUsers) {
            Optional<Caregiver> caregiverOpt = caregiverRepository.findById(user.getUserId());
            if (caregiverOpt.isPresent()) {
                Caregiver caregiver = caregiverOpt.get();
                CaregiverDetail detail = new CaregiverDetail(user, caregiver);
                caregiverDetails.add(detail);
            }
        }

        return caregiverDetails;
    }

	public CaregiverDetail findCaregiverById(Long caregiverId) {
        // 요양보호사의 상세 정보를 조회합니다.
        Caregiver caregiver = caregiverRepository.findById(caregiverId)
            .orElseThrow(() -> new NoSuchElementException("해당 요양보호사를 찾을 수 없습니다." + caregiverId));

        // Caregiver 엔티티에 직접 연결된 User 객체를 가져옵니다.
        User user = caregiver.getUser();
        if (user == null) {
            throw new NoSuchElementException("해당 ID에 연결된 사용자가 없습니다." + caregiverId);
        }

        // CaregiverDetail 객체를 생성하고 반환합니다.
        return new CaregiverDetail(user, caregiver);
    }

	public Page<CaregiverDetail> findCaregiversPageable(Pageable pageable) {
	    // 사용자 리포지토리를 사용하여 요양보호사 역할을 가진 사용자의 페이지를 가져옵니다.
	    Page<User> caregiverUsersPage = userRepository.findByRole(Role.CAREGIVER, pageable);

	    // 각 User 객체를 CaregiverDetail 객체로 변환합니다.
	    List<CaregiverDetail> caregiverDetails = caregiverUsersPage.stream()
	        .map(user -> {
	            Optional<Caregiver> caregiverOpt = caregiverRepository.findById(user.getUserId());
	            return caregiverOpt.map(caregiver -> new CaregiverDetail(user, caregiver)).orElse(null);
	        })
	        .filter(Objects::nonNull)
	        .collect(Collectors.toList());

	    // Page<CaregiverDetail> 객체를 생성합니다.
	    return new PageImpl<>(caregiverDetails, pageable, caregiverUsersPage.getTotalElements());
	}
}