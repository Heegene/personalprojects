package com.heegene.web.board.service;

import java.util.List;

import com.heegene.common.Pagination;
import com.heegene.common.Search;
import com.heegene.web.board.dto.BoardDto;
import com.heegene.web.board.dto.ReplyDto;

public interface BoardService {
	
	// 게시글 기능
	
	// 목록조회
	public List<BoardDto> getBoardList(Search search) throws Exception;
	
	// 글쓰기
	public void insertBoard(BoardDto boardDto) throws Exception;
	
	// 글 조회
	public BoardDto getBoardContent(int bid) throws Exception;
	
	// 글 수정
	public void updateBoard(BoardDto boardDto) throws Exception;
	
	// 글 삭제
	public void deleteBoard(int bid) throws Exception;
	
	// 글 수 확인
	public int getBoardListCnt(Search search) throws Exception;
	
	
	// 댓글 기능
	
	// 댓글 목록 조회
	public List<ReplyDto> getReplyList(int bid) throws Exception;
	// 댓글 작성
	public int saveReply(ReplyDto replyDto) throws Exception;
	// 댓글 수정
	public int updateReply(ReplyDto replyDto) throws Exception;
	// 댓글 삭제
	public int deleteReply(int rid) throws Exception;
	
	
	
	
	
	
}
