package kr.spring.care.board.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import kr.spring.care.user.entity.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BoardDTO {
	
	private Long num;
	private String title;
	private String writer;
	private String content;
	
	private Date regdate;
	private Long hitcount;
	private Long replycnt;

	private User user;

	

	

}
