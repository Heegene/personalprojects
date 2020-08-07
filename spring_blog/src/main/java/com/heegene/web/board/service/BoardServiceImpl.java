package com.heegene.web.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heegene.common.Pagination;
import com.heegene.common.Search;
import com.heegene.web.board.dao.BoardDao;
import com.heegene.web.board.dto.BoardDto;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDao boardDao;
	
	
	// 목록 조회
	@Override
	public List<BoardDto> getBoardList(Search search) throws Exception {
		return boardDao.getBoardList(search);
	}
	
	// 글쓰기
	@Override
	public void insertBoard(BoardDto boardDto) throws Exception {
		int result = boardDao.insertBoard(boardDto);
		
		if (result == 1) {
			System.out.println("게시글 입력 성공");
		} else {
			System.out.println("게시글 입력 실패 ");
		}
	}
	// 게시글 조회
	@Override
	public BoardDto getBoardContent(int bid) throws Exception {
		// 조회수 +1을 먼저 수행 후 get content 를 수행
		boardDao.updateViewCnt(bid);
		return boardDao.getBoardContent(bid);
	}
	
	// 글 수정
	@Override
	public void updateBoard(BoardDto boardDto) throws Exception {
		boardDao.updateBoard(boardDto);
	}
	
	// 글 삭제
	@Override
	public void deleteBoard(int bid) throws Exception {
		boardDao.deleteBoard(bid);
		
	}
	
	// 글 수 확인
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		return boardDao.getBoardListCnt(search);
	}
}
