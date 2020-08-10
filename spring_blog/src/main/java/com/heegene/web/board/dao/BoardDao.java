package com.heegene.web.board.dao;

import java.util.List;

import com.heegene.common.Pagination;
import com.heegene.common.Search;
import com.heegene.web.board.dto.BoardDto;
import com.heegene.web.board.dto.ReplyDto;

public interface BoardDao {
	
	// 게시글 
	public List<BoardDto> getBoardList(Search search) throws Exception;
	
	public BoardDto getBoardContent(int bid) throws Exception;
	
	public int insertBoard(BoardDto boardDto) throws Exception;
	
	public int updateBoard(BoardDto boardDto) throws Exception;
	
	public int deleteBoard(int bid) throws Exception;
	
	public int updateViewCnt(int bid) throws Exception;
	
	public int getBoardListCnt(Search search) throws Exception;
	
	
	// 댓글
	
	public List<ReplyDto> getReplyList(int bid) throws Exception;
	
	public int saveReply(ReplyDto replyDto) throws Exception;
	
	public int updateReply(ReplyDto replyDto) throws Exception;
	
	public int deleteReply(int rid) throws Exception;
	
	
}
