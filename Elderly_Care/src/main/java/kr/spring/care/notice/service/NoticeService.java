package kr.spring.care.notice.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import kr.spring.care.member.entity.Member;
import kr.spring.care.notice.model.Notice;
import kr.spring.care.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;



@Transactional(readOnly = true)  // Transaction 을 수동으로 처리
@Service
@RequiredArgsConstructor
public class NoticeService {
	private final NoticeRepository noticeRepository;
	
	//글 추가
	@Transactional 
	public void insert(Notice notice) {
		noticeRepository.save(notice); 
	}


	//전체보기(페이징+검색)
	@GetMapping("list")
	public Page<Notice> findAll(String field, String word, Pageable pageable){
		Page<Notice> lists = noticeRepository.findAll(pageable);
		if(field.equals("title")) {
			lists = noticeRepository.findByTitleContaining(word, pageable);
		}else if(field.equals("content")) {
			lists=noticeRepository.findByContentContaining(word, pageable);
		}else if(field.equals("id")) {
			lists=noticeRepository.findByContentContaining(word, pageable);
		}
		return lists;
	}
	
	//검색 기능(id로)
	public Notice findNoticeByid(Long id){
		Notice notice = noticeRepository.findById(id).orElse(new Notice());
		return notice;
	}
	
	//전체보기(페이징)
	public Page<Notice> findAll(Pageable pageable){
		return noticeRepository.findAll(pageable);
	}
	//전체보기(기본)
	public List<Notice> list(){
		return noticeRepository.findAll();
	}
	//개수(검색)
	public long count(String field, String word) {
		long count = noticeRepository.count();
		if(field.equals("title")) {
			count = noticeRepository.cntTitleSearch(word);
		}else if(field.equals("content")) {
			count=noticeRepository.cntContentSearch(word);
		}
		
		return count;
		
	}
	//개수
	public long count() {
		return noticeRepository.count();
	}
	//상세보기
	@Transactional
	public Notice view(long num) {
		//조회수 증가
		Notice notice = noticeRepository.findById(num).get();
		notice.setHitcount(notice.getHitcount()+1);
		return notice ;
	}
	//삭제
	@Transactional
	public void delete(long num) {
		noticeRepository.deleteById(num);
	}
	//수정 ==>더티체킹
	@Transactional
	public void update(Notice notice) {
		Notice b = noticeRepository.findById(notice.getNum()).get();
		b.setTitle(notice.getTitle());
		b.setContent(notice.getContent());
		b.setRegdate(new Date());
		
	}
	
	


}

