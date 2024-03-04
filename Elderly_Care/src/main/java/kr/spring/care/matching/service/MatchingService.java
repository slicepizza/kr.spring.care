package kr.spring.care.matching.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import kr.spring.care.matching.constant.MatchingStatus;
import kr.spring.care.matching.dto.MatchingDetail;
import kr.spring.care.matching.dto.MatchingRequestDto;
import kr.spring.care.matching.entity.Matching;
import kr.spring.care.matching.repository.MatchingRepository;
import kr.spring.care.mockdata.entity.Caregiver;
import kr.spring.care.mockdata.entity.Senior;
import kr.spring.care.mockdata.entity.User;
import kr.spring.care.mockdata.repository.CaregiverRepository;
import kr.spring.care.mockdata.repository.SeniorRepository;
import kr.spring.care.mockdata.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchingService {

    @Autowired
    private final MatchingRepository matchingRepository;
    
    @Autowired
    private CaregiverRepository caregiverRepository;
    
    @Autowired
    private SeniorRepository seniorRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    // 매칭 생성
    public Matching createMatching(@Valid MatchingRequestDto matchingRequestDto) {
        Matching matching = new Matching();
        matching.setSeniorId(matchingRequestDto.getSeniorId());
        matching.setCaregiverId(matchingRequestDto.getCaregiverId());
        matching.setStartDate(matchingRequestDto.getStartDate());
        matching.setEndDate(matchingRequestDto.getEndDate());
        matching.setMatchingCountry(matchingRequestDto.getMatchingCountry());
        matching.setStartTime(matchingRequestDto.getStartTime());
        matching.setEndTime(matchingRequestDto.getEndTime());
        matching.setStatus(matchingRequestDto.getStatus());
        return matchingRepository.save(matching);
    }

    // 매칭 ID로 매칭 정보 조회
    public Matching getMatchingById(Long matchingId) {
        return matchingRepository.findById(matchingId)
                .orElseThrow(() -> new EntityNotFoundException("Matching not found: " + matchingId));
    }

    // 매칭 정보 업데이트
    public Matching updateMatching(Matching matching) {
        // 데이터베이스에 이미 존재하는 매칭인지 확인
        Matching existingMatching = matchingRepository.findById(matching.getId())
                .orElseThrow(() -> new EntityNotFoundException("Matching not found: " + matching.getId()));
        // 필요한 속성 업데이트
        existingMatching.setStartDate(matching.getStartDate());
        existingMatching.setEndDate(matching.getEndDate());
        existingMatching.setStatus(matching.getStatus());
        return matchingRepository.save(existingMatching);
    }

    // 매칭 취소
    public void cancelMatching(Long matchingId) {
        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new EntityNotFoundException("Matching not found: " + matchingId));
        matching.setStatus(MatchingStatus.CANCELLED); // 상태를 취소로 변경
        matchingRepository.save(matching);
    }

    public List<Matching> findAllMatchings() {
        return matchingRepository.findAll();
    }
    
    public Page<Matching> findMatchingsPageable(Pageable pageable) {
        return matchingRepository.findAll(pageable);
    }

    public MatchingDetail getMatchingDetailById(Long matchingId) {
        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new NoSuchElementException("해당 매칭을 찾을 수 없습니다: " + matchingId));

        Long caregiverId = matching.getCaregiverId();
        Caregiver caregiver = null;
        User caregiverUser = null;
        if (caregiverId != null) {
            caregiver = caregiverRepository.findById(caregiverId)
                    .orElseThrow(() -> new NoSuchElementException("해당 요양보호사를 찾을 수 없습니다: " + caregiverId));
            caregiverUser = caregiver.getUser();
        }

        Long seniorId = matching.getSeniorId();
        Senior senior = null;
        User seniorUser = null;
        if (seniorId != null) {
            senior = seniorRepository.findById(seniorId)
                    .orElseThrow(() -> new NoSuchElementException("해당 노인을 찾을 수 없습니다: " + seniorId));
            seniorUser = userRepository.findById(senior.getSeniorId())
                    .orElseThrow(() -> new NoSuchElementException("해당 노인의 사용자 정보를 찾을 수 없습니다: " + seniorId));
        }

        return new MatchingDetail(caregiverUser, caregiver, seniorUser, senior, matching);
    }
    
}
