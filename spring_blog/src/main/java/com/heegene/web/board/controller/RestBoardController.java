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
	
	// 댓글 조회
	@RequestMapping(value = "/getReplyList", method=RequestMethod.POST)
	public List<ReplyDto> getReplyList(@RequestParam("bid") int bid) throws Exception {
		return boardService.getReplyList(bid);
	}
	
	// 댓글 등록
	@RequestMapping(value = "/saveReply", method=RequestMethod.POST)
	public Map<String, Object> saveReply(@RequestBody ReplyDto replyDto) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		try {
			boardService.saveReply(replyDto);
			result.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}
		
		return result;
	}
	
	// 댓글 삭제
	@RequestMapping(value = "/deleteReply", method= {RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> deleteReply(@RequestParam("rid") int rid) throws Exception {
		Map<String, Object> result = new HashMap<>();
		try {
			boardService.deleteReply(rid);
			result.put("status", "OK");
		} catch (Exception e) {
			result.put("status", "False");
		}
		return result;
	}
	
	// 댓글 수정
	@RequestMapping(value = "/updateReply", method= {RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> updateReply(@RequestBody ReplyDto replyDto) throws Exception {
		Map<String, Object> result = new HashMap<>();
		try {
			boardService.updateReply(replyDto);
			result.put("status", "OK");
		} catch (Exception e) {
			result.put("status", "False");
			
		}
		
		return result;
	}
	

}
