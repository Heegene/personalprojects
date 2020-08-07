package com.heegene.web.board.controller;


import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.heegene.common.Pagination;
import com.heegene.common.Search;
import com.heegene.web.board.dto.BoardDto;
import com.heegene.web.board.service.BoardService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value="/getBoardList", method = RequestMethod.GET)
	public String getBoardList(Model model,
							   @RequestParam(required=false, defaultValue = "1") int page,
							   @RequestParam(required=false, defaultValue = "1") int range,
							   @RequestParam(required=false, defaultValue = "title") String searchType,
							   @RequestParam(required=false) String keyword,
							   @ModelAttribute("search") Search search
											) throws Exception {
		
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 수
		int listCnt = boardService.getBoardListCnt(search);
		
		search.pageInfo(page, range, listCnt);
		
		// pagination 객체 생성
		Pagination pagination = new Pagination();
		pagination.pageInfo(page, range, listCnt);
		
		model.addAttribute("pagination", search);
		model.addAttribute("boardList", boardService.getBoardList(search));
		return "board/index";
	}
	
	
	
	// 글쓰기 클릭 시 호출할 부분 
	@RequestMapping(value="/boardForm")
	public String boardForm(@ModelAttribute("boardDto") BoardDto boardDto) {
		return "board/boardForm";
	}
	
	// 글을 쓰고 저장할 때 호출할 부분(글 목록으로 돌아가도록)
	@RequestMapping(value="/saveBoard", method = RequestMethod.POST)
	public String saveBoard(@ModelAttribute("boardDto") BoardDto boardDto,@RequestParam("mode") String mode, RedirectAttributes rttr) throws Exception {
		if (mode.equals("edit")) {
			boardService.updateBoard(boardDto);
		} else {
			boardService.insertBoard(boardDto);
		}
		
		return "redirect:/board/getBoardList";
		// modelattribute "BoardDto"는 boardDto와 redirectattribute 두가지를 인자로 받음
		// 첫 번째 인자는 화면에서 넘겨주는 값을 boarddto랑 매칭시켜 데이터를 받아옴
		// 두 번째 인자는 글쓰기 이후 돌아가야 할 페이지를 데이터에 전달하기 위한 인자
		// redirectattribute를 사용하는 이유 중 하나는 뒤로가기 버튼을 클릭했을 때 다시 저장단계로 돌아가 도배되는것을 방지하기 때문
	}
	
	@RequestMapping(value = "/getBoardContent", method=RequestMethod.GET)
	public String getBoardContent(Model model, @RequestParam("bid") int bid) throws Exception {
		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		return "board/boardContent";
	}
	
	@RequestMapping(value = "/editForm", method=RequestMethod.GET)
	public String editForm(@RequestParam("bid") int bid,
						   @RequestParam("mode") String mode,
						   Model model) throws Exception {
		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		model.addAttribute("mode", mode);
		model.addAttribute("boardDto", new BoardDto());
		// boardDto 추가한 건 입력폼과의 연동을 위해서 
		return "board/boardForm";
	}
	
	@RequestMapping(value = "/deleteBoard", method=RequestMethod.GET)
	public String deleteBoard(RedirectAttributes rttr, @RequestParam("bid") int bid) throws Exception {
		boardService.deleteBoard(bid);
		return "redirect:/board/getBoardList";
	}
	
	

	
}
	