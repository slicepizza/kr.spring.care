package kr.spring.care.matching.dto;

import kr.spring.care.mockdata.entity.Caregiver;
import kr.spring.care.mockdata.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaregiverDetail {
    private User user;
    private Caregiver caregiver;

    public CaregiverDetail(User user, Caregiver caregiver) {
        this.user = user;
        this.caregiver = caregiver;
    }
}
