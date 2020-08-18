package com.heegene.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
					.createdDate(boardEntity.getCreatedDate())
					.build();
			
			boardDtoList.add(boardDto);
		}
		return boardDtoList;
	}
	
	@Transactional
	public BoardDto getPost(Long id) {
		Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
		// findbyid = PK 값을 where 조건으로 하여 데이터를 가져오기 위한 메서드로 JPA repository 인터페이스에 정의되어 있음
		// 반환 값은 Optional 타입으로, entity 를 빼 오기 위해서는 boardEntityWrapper.get 이런식으로 get() 메서드 사용
		
		BoardEntity boardEntity = boardEntityWrapper.get();
		
		BoardDto boardDto = BoardDto.builder()
				.id(boardEntity.getId())
				.title(boardEntity.getTitle())
				.content(boardEntity.getContent())
				.writer(boardEntity.getWriter())
				.createdDate(boardEntity.getCreatedDate())
				.build();
		
		return boardDto;
	}
	
	@Transactional
	public void deletePost(Long id) {
		boardRepository.deleteById(id);
		// pk 값을 where 조건으로 하여 데이터를 삭제하기 위한 method로, JPArepository 인터페이스에 정의되어 있음 
	}
	
}
