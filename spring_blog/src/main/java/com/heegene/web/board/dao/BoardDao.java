package com.heegene.web.board.dao;

import java.util.List;

import com.heegene.common.Pagination;
import com.heegene.common.Search;
import com.heegene.web.board.dto.BoardDto;

public interface BoardDao {
	public List<BoardDto> getBoardList(Search search) throws Exception;
	
	public BoardDto getBoardContent(int bid) throws Exception;
	
	public int insertBoard(BoardDto boardDto) throws Exception;
	
	public int updateBoard(BoardDto boardDto) throws Exception;
	
	public int deleteBoard(int bid) throws Exception;
	
	public int updateViewCnt(int bid) throws Exception;
	
	public int getBoardListCnt(Search search) throws Exception;
	
}
