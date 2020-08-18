package com.heegene.blog.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
	
	
}
