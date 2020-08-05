package com.heegene.web.board.service;

import java.util.List;

import com.heegene.web.board.dto.BoardDto;

public interface BoardService {
	
	public List<BoardDto> getBoardList() throws Exception;
	
}
