package com.heegene.web.board.service;

import java.util.List;

import com.heegene.web.board.dto.BoardDto;

public interface BoardService {
	
	// 목록조회
	public List<BoardDto> getBoardList() throws Exception;
	
	// 글쓰기
	public void insertBoard(BoardDto boardDto) throws Exception;
	
	
}
