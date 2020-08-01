package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	// 게시판 목록 조회
	public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {
		return boardDao.getBoardList(boardForm);
	}
	
	// 게시글 조회
	public BoardDto getBoardDetail(BoardForm boardForm) throws Exception {
		BoardDto boardDto = new BoardDto();
		
		String searchType = boardForm.getSearch_type();
		
		if ("S".equals(searchType)) {
			int updateCnt = boardDao.updateBoardHits(boardForm);
		}
		
		
		
		return boardDto;
		
	}
	
}
