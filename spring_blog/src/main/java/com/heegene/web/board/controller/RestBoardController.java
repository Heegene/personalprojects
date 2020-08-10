package com.heegene.web.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heegene.web.board.dto.ReplyDto;
import com.heegene.web.board.service.BoardService;

@RestController
@RequestMapping(value = "/restBoard")
public class RestBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/getReplyList", method=RequestMethod.POST)
	public List<ReplyDto> getReplyList(@RequestParam("bid") int bid) throws Exception {
		return boardService.getReplyList(bid);
	}
	
	@RequestMapping(value = "/saveReply", method=RequestMethod.POST)
	public Map<String, Object> saveReply(@RequestBody ReplyDto replyDto) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		try {
			boardService.saveReply(replyDto);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}
		
		return result;
	}
	
	

}