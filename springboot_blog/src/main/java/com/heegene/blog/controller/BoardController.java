package com.heegene.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heegene.blog.dto.BoardDto;
import com.heegene.blog.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BoardController {
	private BoardService boardService;
	
	// 게시글 리스트 
	@GetMapping("/")
	public String list(Model model) {
		List<BoardDto> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList", boardList);
		return "board/list.html";
	}
	// 게시글 작성화면
	@GetMapping("/post")
	public String write() {
		return "board/write.html";
	}
	
	// 게시글 입력
	@PostMapping("/post")
	public String write(BoardDto boardDto) {
		boardService.savePost(boardDto);
		
		return "redirect:/";
	}
	
	// 게시글 상세조회 
	@GetMapping("/post/{no}")
	public String detail(@PathVariable("no") Long no, Model model) {
		BoardDto boardDto = boardService.getPost(no);
		
		model.addAttribute("boardDto", boardDto);
		return "board/detail.html";
	}
	
	// 게시글 수정화면
	@GetMapping("/post/edit/{no}")
	public String edit(@PathVariable("no") Long no, Model model) {
		BoardDto boardDto = boardService.getPost(no);
		
		model.addAttribute("boardDto", boardDto);
		return "board/update.html";
	}
	
	// 게시글 수정저장
	@PutMapping("/post/edit/{no}")
	public String update(BoardDto boardDto) {
		boardService.savePost(boardDto);
		// 게시글 추가에서 사용하는 savePost 메서드를 같이 사용함 
		return "redirect:/";
	}
	
	// 게시글 삭제
	@DeleteMapping("/post/{no}")
	public String delete(@PathVariable("no") Long no) {
		boardService.deletePost(no);
		
		return "redirect:/";
	}
	// 유동적으로 변하는 Path variable 처리를 위해 annotation 추가
	// URL 매핑부에서 {변수} 처리를 하면 method parameter로 해당 변수를 받을 수 있음
	
	// 게시글 검색
	@GetMapping("/board/search")
	public String search(@RequestParam(value="keyword") String keyword, Model model) {
		List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
		
		model.addAttribute("boardList", boardDtoList);
		return "board/list.html";
		
	}
	
}
