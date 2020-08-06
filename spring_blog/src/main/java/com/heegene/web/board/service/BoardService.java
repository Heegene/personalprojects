package com.heegene.web.board.service;

import java.util.List;

import com.heegene.common.Pagination;
import com.heegene.web.board.dto.BoardDto;

public interface BoardService {
	
	// 목록조회
	public List<BoardDto> getBoardList(Pagination pagination) throws Exception;
	
	// 글쓰기
	public void insertBoard(BoardDto boardDto) throws Exception;
	
	// 글 조회
	public BoardDto getBoardContent(int bid) throws Exception;
	
	// 글 수정
	public void updateBoard(BoardDto boardDto) throws Exception;
	
	// 글 삭제
	public void deleteBoard(int bid) throws Exception;
	
	// 글 수 확인
	public int getBoardListCnt() throws Exception;
	
}
