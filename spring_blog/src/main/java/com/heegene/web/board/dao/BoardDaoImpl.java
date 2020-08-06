package com.heegene.web.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.heegene.common.Pagination;
import com.heegene.web.board.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Inject
	private SqlSession sqlSession;
	// sqlsession 객체생성
	
	
	
	// 목록 불러오기
	@Override
	public List<BoardDto> getBoardList(Pagination pagination) throws Exception {
		return sqlSession.selectList("com.heegene.web.board.boardMapper.getBoardList");
	}
	
	// 내용 조회
	@Override
	public BoardDto getBoardContent(int bid) throws Exception {
		return sqlSession.selectOne("com.heegene.web.board.boardMapper.getBoardContent", bid);
	}
	
	// 글 등록
	@Override
	public int insertBoard(BoardDto boardDto) throws Exception {
		return sqlSession.insert("com.heegene.web.board.boardMapper.insertBoard", boardDto);
	}
	
	// 글 수정
	@Override
	public int updateBoard(BoardDto boardDto) throws Exception {
		return sqlSession.update("com.heegene.web.board.boardMapper.updateBoard", boardDto);
	}
	
	// 글 삭제
	@Override
	public int deleteBoard(int bid) throws Exception {
		return sqlSession.delete("com.heegene.web.board.boardMapper.deleteBoard", bid);
	}
	
	// 조회수 증가
	@Override
	public int updateViewCnt(int bid) throws Exception {
		return sqlSession.update("com.heegene.web.board.boardMapper.updateViewCnt", bid);
	}
	
	// 게시글 수 확인
	@Override
	public int getBoardListCnt() throws Exception {
		return sqlSession.selectOne("com.heegene.web.board.boardMapper.getBoardListCnt");
	}
}
