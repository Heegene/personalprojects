package com.heegene.blog.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.heegene.blog.domain.entity.BoardEntity;
import com.heegene.blog.domain.repository.BoardRepository;
import com.heegene.blog.dto.BoardDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {
	private BoardRepository boardRepository;
	
	@Transactional
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
		// save는 JPA repository에 정의된 method로, DB에 insert, update를 담당하고 매개변수로 entity 전달
	}
	
	@Transactional
	public List<BoardDto> getBoardList() {
		List<BoardEntity> boardEntities = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		// controller와 service 간의 데이터 전달은 dto 객체로 하기 위해
		// repository에서 가져온 entity를 반복문을 통해 dto로 변환 
		
		for (BoardEntity boardEntity : boardEntities) {
			BoardDto boardDto = BoardDto.builder()
					.id(boardEntity.getId())
					.title(boardEntity.getTitle())
					.content(boardEntity.getContent())
					.writer(boardEntity.getWriter())
					.createdDate(boardEntity.getCreateDate())
					.build();
			
			boardDtoList.add(boardDto);
		}
		return boardDtoList;
	}
	
}
