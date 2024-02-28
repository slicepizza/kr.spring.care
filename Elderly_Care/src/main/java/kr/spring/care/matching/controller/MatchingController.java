package kr.spring.care.matching.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import kr.spring.care.matching.dto.CaregiverDetail;
import kr.spring.care.matching.dto.MatchingRequestDto;
import kr.spring.care.matching.entity.Matching;
import kr.spring.care.matching.service.CaregiverService;
import kr.spring.care.matching.service.MatchingService;

@Controller
@RequestMapping("/matching")
public class MatchingController {

    @Autowired
    MatchingService matchingService;
    @Autowired
    private CaregiverService caregiverService;

    // 요양보호사 구인 페이지
    @GetMapping("/findcaregiver")
    public String matchingFindCaregiver(Model model, Pageable pageable) {
        Page<CaregiverDetail> caregiversPage = caregiverService.findCaregiversPageable(pageable);
        model.addAttribute("caregiversPage", caregiversPage);
        return "matching/matchingFindCaregiver";
    }

    // 요양보호사 구인 페이지 상세
    @GetMapping("/detail")
    public String matchingFindCaregiverDetail(@RequestParam Long caregiverId, Model model) {
        CaregiverDetail caregiverDetail = caregiverService.findCaregiverById(caregiverId);
        model.addAttribute("caregiver", caregiverDetail);
        return "matching/matchingFindCaregiverDetail";
    }

    // 요양보호사 구직 페이지
    @GetMapping("/findjob")
    public String matchingFindJob(Model model) {
        List<Matching> matchings = matchingService.findAllMatchings(); // 모든 매칭 데이터 가져오기
        model.addAttribute("matchings", matchings);
        return "matching/matchingFindJob";
    }

    // 요양보호사 구직 페이지 상세
    @GetMapping("/findjob/{matchingId}")
    public String matchingFindJobDetail(@PathVariable Long matchingId, Model model) {
        Matching matching = matchingService.getMatchingById(matchingId);
        model.addAttribute("matching", matching);
        return "matching/matchingFindJobDetail";
    }

    // 매칭 생성 (예시)
    @PostMapping("/create")
    public String createMatching(@Valid MatchingRequestDto matchingRequestDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "matching/createMatchingForm";
        }
        
        try {
            Matching matching = matchingService.createMatching(matchingRequestDto);
            return "redirect:/matching/details/" + matching.getId();
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "matching/createMatchingForm";
        }
    }
    // 기타 필요한 메소드들을 여기에 추가
}
