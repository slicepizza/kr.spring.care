package kr.spring.care.mockdata.component;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import kr.spring.care.mockdata.entity.Caregiver;
import kr.spring.care.mockdata.entity.Guardian;
import kr.spring.care.mockdata.entity.Senior;
import kr.spring.care.mockdata.entity.User;
import kr.spring.care.mockdata.repository.CaregiverRepository;
import kr.spring.care.mockdata.repository.GuardianRepository;
import kr.spring.care.mockdata.repository.SeniorRepository;
import kr.spring.care.mockdata.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CaregiverRepository caregiverRepository;
    private final SeniorRepository seniorRepository;
    private final GuardianRepository guardianRepository;
    private final MockDataGenerator mockDataGenerator;

    public DataInitializer(UserRepository userRepository, CaregiverRepository caregiverRepository, 
                           SeniorRepository seniorRepository, GuardianRepository guardianRepository, 
                           MockDataGenerator mockDataGenerator) {
        this.userRepository = userRepository;
        this.caregiverRepository = caregiverRepository;
        this.seniorRepository = seniorRepository;
        this.guardianRepository = guardianRepository;
        this.mockDataGenerator = mockDataGenerator;
    }

    @Override
    public void run(String... args) throws Exception {
        // Generate mock data
        List<User> users = mockDataGenerator.generateUsers();
        List<Caregiver> caregivers = mockDataGenerator.generateCaregivers(users);
        List<Senior> seniors = mockDataGenerator.generateSeniors(users);
        List<Guardian> guardians = mockDataGenerator.generateGuardians(users, seniors);

        // Save to database
        userRepository.saveAll(users);
        caregiverRepository.saveAll(caregivers);
        seniorRepository.saveAll(seniors);
        guardianRepository.saveAll(guardians);
    }
    
}
