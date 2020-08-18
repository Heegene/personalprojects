package com.heegene.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.heegene.blog.dto.BoardDto;
import com.heegene.blog.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BoardController {
	private BoardService boardService;
	
	@GetMapping("/")
	public String list(Model model) {
		List<BoardDto> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList", boardList);
		return "board/list.html";
	}
	
	@GetMapping("/post")
	public String write() {
		return "board/write.html";
	}
	
	@PostMapping("/post")
	public String write(BoardDto boardDto) {
		boardService.savePost(boardDto);
		
		return "redirect:/";
	}
	
	
	
}
