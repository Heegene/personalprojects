package com.heegene.web.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.heegene.web.board.dao.BoardDao;
import com.heegene.web.board.dto.BoardDto;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDao boardDao;
	
	@Override
	public List<BoardDto> getBoardList() throws Exception {
		return boardDao.getBoardList();
	}
	
	
	
	
}
