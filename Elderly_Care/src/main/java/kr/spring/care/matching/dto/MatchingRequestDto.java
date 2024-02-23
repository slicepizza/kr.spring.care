package kr.spring.care.matching.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Getter
@Setter
public class MatchingRequestDto {

    private Long seniorId;
    private Long caregiverId;

    @NotNull(message = "시작 날짜는 필수입니다.")
    @PastOrPresent(message = "시작 날짜는 과거나 현재 날짜여야 합니다.")
    private LocalDate startDate;

    @NotNull(message = "종료 날짜는 필수입니다.")
    @Future(message = "종료 날짜는 미래의 날짜여야 합니다.")
    private LocalDate endDate;

    @NotEmpty(message = "상태는 비워둘 수 없습니다.")
    @Size(max = 20, message = "상태는 최대 20자를 넘을 수 없습니다.")
    private String status;

    // 추가적인 필드나 메소드가 필요할 경우 여기에 구현
}
