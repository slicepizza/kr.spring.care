package kr.spring.care.notice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kr.spring.care.notice.model.NoticeComment;


public interface NoticeCommentRepository  
   extends JpaRepository<NoticeComment, Long>{
	
	//댓글추가
	@Modifying
	@Query(value = "insert into noticecomment(content, regdate,Nnum,user_id) values(?1, now(), ?2, ?3) ", 
			nativeQuery = true)
	public void insert(String content, long Nnum, long user_id);
	
	//JPQL(Java Persistence Query Language : 엔티티 객체를 중심)
	//@Query(value= "select * from tbl_comment4  where Nnum=?1", nativeQuery=true) 
	@Query( "select sc from noticecomment sc where sc.notice.num=?1")
	public List<NoticeComment> findByNnum(Long Nnum);

}