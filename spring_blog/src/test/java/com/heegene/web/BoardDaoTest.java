package com.heegene.web;

import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heegene.web.board.dao.BoardDao;
import com.heegene.web.board.dto.BoardDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml", "classpath:spring/dataSource-context.xml" })
public class BoardDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);

	@Inject
	private BoardDao boardDao;

	@Test
	@Ignore
	public void testGetBoardList() throws Exception {
		List<BoardDto> list = boardDao.getBoardList();
		logger.info("\n board list \n ");
		if (list.size() > 0) {
			for (BoardDto listitem : list) {
				logger.info(listitem.getTitle());
			}
		} else {
			logger.info("데이터가 존재하지 않습니다.");
		}
	}
	
	@Test
	@Ignore
	public void testGetBoardContent() throws Exception {
		BoardDto boardDto = boardDao.getBoardContent(1);
		
		logger.info("\n board list \n");
		if (boardDto != null) {
			logger.info("글 제목" + boardDto.getTitle());
		}
	}
	
	@Test
	@Ignore
	public void testInsertBoard() throws Exception {
		BoardDto boardDto = new BoardDto();
		
		boardDto.setCate_cd("1");
		boardDto.setTitle("첫 번째 게시물입니다.");
		boardDto.setContent("첫 번째 게시글입니다.");
		boardDto.setTag("1");
		boardDto.setReg_id("abcd");
		
		int result = boardDao.insertBoard(boardDto);
		logger.info("\n insert board result " + result);
		if (result == 1 ) {
			logger.info("게시물 등록 성공");
		} else {
			logger.info("게시글 등록 실패");
		}
	}
	
	@Test
	@Ignore
	public void testUpdateBoard() throws Exception {
		BoardDto boardDto = new BoardDto();
		
		boardDto.setBid(1);
		boardDto.setCate_cd("1");
		boardDto.setTitle("첫 번째 게시물입니다-수정");
		boardDto.setContent("첫 번째 게시물 내용입니다-수정");
		boardDto.setTag("1-1");
		
		int result = boardDao.updateBoard(boardDto);
		logger.info("\n update board result " + result );
		if (result == 1 ) {
			logger.info("게시글 수정 성공");
		} else {
			logger.info("게시글 수정 실패");
		}
	}
	
	@Test
	public void testDeleteBoard() throws Exception {
		int result = boardDao.deleteBoard(1);
		logger.info("\n delete board result " + result );
		
		if (result == 1 ) {
			logger.info("게시글 삭제 성공");
		} else {
			logger.info("게시글 삭제 실패");
		}
		
	}
	
	
	
	

}
