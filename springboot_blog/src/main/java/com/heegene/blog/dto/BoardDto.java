package com.heegene.blog.dto;

import java.time.LocalDateTime;

import com.heegene.blog.domain.entity.BoardEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
	// 각 계층에서 필요한 객체 전달은 Entity 객체가 아닌 dto 객체를 통해 주고받는 것이 좋으므로
	// dto와 entity 분리 
	
	private Long id;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public BoardEntity toEntity() { // dto에서 필요한 부분을 빌더패턴으로 entity화
		BoardEntity boardEntity = BoardEntity.builder()
				.id(id)
				.writer(writer)
				.title(title)
				.content(content)
				.build();
		return boardEntity;
	}
	
	@Builder
	public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
}
