package com.heegene.blog.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
// parameter가 없는 기본 생성자 추가(JPA 사용을 위한 기본 생성자 추가)
@Getter
// 모든 필드에 getter 자동생성(setter는 무분별하게 사용 시 안정성을 보장받기 어려우므로 builder 패턴 사용)
@Entity
@Table(name="board") // 엔티티 클래스와 매핑되는 테이블 정보 명시
public class BoardEntity extends TimeEntity {
	@Id // 기본키 명시
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키로 대체키 사용 시 기본키 값 생성 전략 명시
	private Long id;
	
	@Column(length = 10, nullable = false)
	private String writer;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	@Builder // 빌더패턴 클래스 생성 어노테이션(setter 사용 대신 빌더패턴을 사용하여 안정성 보장)
	public BoardEntity(Long id, String title, String content, String writer) {
		this.id = id;
		this.writer = writer;
		this.content = content;
		this.title = title;
	}

}
