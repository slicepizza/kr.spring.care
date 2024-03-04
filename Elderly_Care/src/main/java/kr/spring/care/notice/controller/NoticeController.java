package kr.spring.care.notice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.care.notice.model.Notice;
import kr.spring.care.notice.service.NoticeService;
/*import kr.spring.care.config.auth.PrincipalUser;*/
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/notice/*")
@RequiredArgsConstructor
@Log4j2
@Controller
public class NoticeController {
	private final NoticeService noticeService;

//	@PreAuthorize("isAuthenticated()")
	//글 작성 폼으로
	@GetMapping("insert")
	public String insert() {
		return "notice/insert";
	}

	// 글추가
	@GetMapping("/notice/insertlist") // Notice =>title/writer(????)/content
	public String insert(Notice notice) {
		log.info("wwwwww");
		noticeService.insert(notice);
		return "redirect:list";
	}

	
	// 전체보기(페이징+검색) ===> 페이징정보 + 데이터(Notice 정보)
	@GetMapping("list")
	public String list(Model model,
			@PageableDefault(size = 5, sort = "num", direction = Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		Page<Notice> lists = noticeService.findAll(field, word, pageable);
		long count = noticeService.count(field, word);

		model.addAttribute("count", count);
		model.addAttribute("notices", lists);

		return "notice/list";
	}

	// 상세보기
	@GetMapping("view/{num}")
	public String view(@PathVariable long num, Model model) {
		model.addAttribute("notice", noticeService.view(num));
		return "notice/view";
	}

	// 삭제
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public long delete(@PathVariable long num) {
		noticeService.delete(num);
		return num;
	}

	// 수정폼
	@GetMapping("update/{num}")
	public String update(@PathVariable long num, Model model) {
		model.addAttribute("notice", noticeService.view(num));
		return "notice/update";
	}

	// 수정
	@PutMapping("update")
	@ResponseBody
	public long update(@RequestBody Notice notice) {
		noticeService.update(notice);
		return notice.getNum();
	}

}
