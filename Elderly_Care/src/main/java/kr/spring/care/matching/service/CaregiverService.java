package kr.spring.care.matching.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.spring.care.matching.dto.CaregiverDetail;
import kr.spring.care.mockdata.constant.Role;
import kr.spring.care.mockdata.entity.Caregiver;
import kr.spring.care.mockdata.entity.User;
import kr.spring.care.mockdata.repository.CaregiverRepository;
import kr.spring.care.mockdata.repository.UserRepository;

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
}