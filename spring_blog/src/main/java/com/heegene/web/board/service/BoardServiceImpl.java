package com.heegene.web.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.heegene.web.board.dao.BoardDao;
import com.heegene.web.board.dto.BoardDto;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDao boardDao;
	
	
	// 목록 조회
	@Override
	public List<BoardDto> getBoardList() throws Exception {
		return boardDao.getBoardList();
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
	
	@Override
	public BoardDto getBoardContent(int bid) throws Exception {
		// 조회수 +1을 먼저 수행 후 get content 를 수행
		boardDao.updateViewCnt(bid);
		return boardDao.getBoardContent(bid);
	}
	
	
}
