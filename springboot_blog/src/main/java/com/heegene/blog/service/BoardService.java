package com.heegene.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.heegene.blog.domain.entity.BoardEntity;
import com.heegene.blog.domain.repository.BoardRepository;
import com.heegene.blog.dto.BoardDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {
	private static final int BLOCK_PAGE_NUM_COUNT = 5; // 한 블럭에 존재하는 페이지 번호 수 지정
	private static final int PAGE_POST_COUNT = 4; // 한 페이지 내의 글 수 
	
	
	private BoardRepository boardRepository;
	
	@Transactional
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
		// save는 JPA repository에 정의된 method로, DB에 insert, update를 담당하고 매개변수로 entity 전달
	}
	
	@Transactional
	public List<BoardDto> getBoardList(Integer pageNum) {
		Page<BoardEntity> page = boardRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
		// repository의 find() 관련 메서드를 호출할 때 pageable 인터페이스를 구현한 클래스(pagerequest.of)를 전달하면 페이징 가능
		// 첫 번째 인자: limit(현재 페이지 번호-1)로 적용, SQL조회시 사용되는 limit과 실제 페이지 번호가 차이가 있기 때문
		// 두 번째 인자: offset (몇 개를 가져올지)
		// 세 번째 인자: 정렬 방식 결정. createdDate 기준 오름차순 정렬하여 가져옴
		
		List<BoardEntity> boardEntities = page.getContent();
		// 반환된 page 객체의 getcontent 메서드를 호출하여 entity를 리스트로 꺼내올 수 있음 
		
		List<BoardDto> boardDtoList = new ArrayList<>();
		// controller와 service 간의 데이터 전달은 dto 객체로 하기 위해
		// repository에서 가져온 entity를 반복문을 통해 dto로 변환 
		
		for (BoardEntity boardEntity : boardEntities) {
			boardDtoList.add(this.convertEntityToDto(boardEntity));
		}
		
		return boardDtoList;
	}
	
	@Transactional
	public BoardDto getPost(Long id) {
		Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
		// findbyid = PK 값을 where 조건으로 하여 데이터를 가져오기 위한 메서드로 JPA repository 인터페이스에 정의되어 있음
		// 반환 값은 Optional 타입으로, entity 를 빼 오기 위해서는 boardEntityWrapper.get 이런식으로 get() 메서드 사용
		
		BoardEntity boardEntity = boardEntityWrapper.get();
		
		BoardDto boardDto = BoardDto.builder()
				.id(boardEntity.getId())
				.title(boardEntity.getTitle())
				.content(boardEntity.getContent())
				.writer(boardEntity.getWriter())
				.createdDate(boardEntity.getCreatedDate())
				.build();
		
		return boardDto;
	}
	
	@Transactional
	public void deletePost(Long id) {
		boardRepository.deleteById(id);
		// pk 값을 where 조건으로 하여 데이터를 삭제하기 위한 method로, JPArepository 인터페이스에 정의되어 있음 
	}
	
	@Transactional
	public List<BoardDto> searchPosts(String keyword) {
		List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		if (boardEntities.isEmpty()) {
			return boardDtoList;
		}
		
		for (BoardEntity boardEntity : boardEntities) {
			boardDtoList.add(this.convertEntityToDto(boardEntity));
		}
		
		return boardDtoList;
	}
	
	private BoardDto convertEntityToDto(BoardEntity boardEntity) {
		return BoardDto.builder()
				.id(boardEntity.getId())
				.title(boardEntity.getTitle())
				.content(boardEntity.getContent())
				.writer(boardEntity.getWriter())
				.createdDate(boardEntity.getCreatedDate())
				.build();
	}
	
	@Transactional
	public Long getBoardCount() {
		return boardRepository.count(); // 전체 게시글 개수 가져옴
	}
	
	public Integer[] getPageList(Integer curPageNum) {
		Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];
		
		// 총 게시글 갯수
		Double postsTotalCount = Double.valueOf(this.getBoardCount());
		
		// 총 게시글 기준으로 계산한 마지막 페이지 번호 계산(올림으로 계산)
		Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount/PAGE_POST_COUNT)));
		
		// 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
		Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT) ? 
				curPageNum + BLOCK_PAGE_NUM_COUNT : totalLastPageNum;
		
		// 페이지 시작번호 조정(현재가 3페이지면 1,2,3,4,5 이렇게 보이도록)
		curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;
		
		
		// 페이지 번호 할당 전달
		for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
			pageList[idx] = val;
		}
		
		return pageList;
	}
	
}
