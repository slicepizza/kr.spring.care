package kr.spring.care.matching.entity;

import jakarta.persistence.*;
import kr.spring.care.matching.dto.MatchingRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_id")
    private Long id;

    @Column(name = "senior_id")
    private Long seniorId;

    @Column(name = "caregiver_id")
    private Long caregiverId;

    private LocalDate startDate;
    private LocalDate endDate;

    private String status;

    // 팩토리 메소드: MatchingRequestDto를 기반으로 새로운 Matching 객체를 생성
    public static Matching createMatching(MatchingRequestDto requestDto) {
        Matching matching = new Matching();
        matching.setSeniorId(requestDto.getSeniorId());
        matching.setCaregiverId(requestDto.getCaregiverId());
        matching.setStartDate(requestDto.getStartDate());
        matching.setEndDate(requestDto.getEndDate());
        matching.setStatus(requestDto.getStatus());
        return matching;
    }
}
