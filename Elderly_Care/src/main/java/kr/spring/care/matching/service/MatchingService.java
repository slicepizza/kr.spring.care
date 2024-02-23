package kr.spring.care.matching.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import kr.spring.care.matching.dto.MatchingFormDto;
import kr.spring.care.matching.dto.MatchingRequestDto;
import kr.spring.care.matching.entity.Matching;
import kr.spring.care.matching.repository.MatchingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class MatchingService {

    private final MatchingRepository matchingRepository;

    // 매칭 생성
    public Matching createMatching(@Valid MatchingFormDto matchingFormDto) {
        // 매칭 생성 로직 구현
        // 예를 들어, 요청 데이터를 기반으로 매칭 객체 생성 및 저장
        Matching matching = new Matching();
        // matching의 세부 속성 설정
        return matchingRepository.save(matching);
    }

    // 매칭 ID로 매칭 정보 조회
    public Matching getMatchingById(Long matchingId) {
        return matchingRepository.findById(matchingId)
                .orElseThrow(() -> new EntityNotFoundException("Matching not found: " + matchingId));
    }

    // 매칭 정보 업데이트
    public Matching updateMatching(Matching matching) {
        // 업데이트 로직 구현
        return matchingRepository.save(matching);
    }

    // 매칭 취소
    public void cancelMatching(Long matchingId) {
        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new EntityNotFoundException("Matching not found: " + matchingId));
        // 매칭 취소 로직 구현
        matchingRepository.delete(matching);
    }

    // 기타 필요한 메소드들을 여기에 추가
}
