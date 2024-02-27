package kr.spring.care.matching.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import kr.spring.care.matching.constant.MatchingStatus;

import java.time.LocalDate;

@Getter
@Setter
public class MatchingRequestDto {

    private Long seniorId;
    private Long caregiverId;

    @NotNull(message = "시작 날짜는 필수입니다.")
    @FutureOrPresent(message = "시작 날짜는 현재 혹은 미래 날짜여야 합니다.")
    private LocalDate startDate;

    @NotNull(message = "종료 날짜는 필수입니다.")
    @Future(message = "종료 날짜는 미래의 날짜여야 합니다.")
    private LocalDate endDate;

    private MatchingStatus status;
    
    // 추가적인 필드나 메소드가 필요할 경우 여기에 구현
}
