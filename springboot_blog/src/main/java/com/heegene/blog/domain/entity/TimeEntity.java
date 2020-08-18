package com.heegene.blog.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass // 테이블로 매핑하지 않고 자식 클래스(entity) 에게 매핑정보 상속
@EntityListeners(AuditingEntityListener.class) // 해당Entity는 auditing 기능 사용한다는 것 JPA에게 알림
public abstract class TimeEntity {
	// 데이터 조작 시 자동으로 날짜를 수정해주는 JPA의 Auditing 기능 사용
	@CreatedDate // Entity가 처음 저장될 때 생성일을 주입
	@Column(updatable = false) // 생성일은 update가 필요하지 않으므로 updatable false설정
	private LocalDateTime createDate; 
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}
