package kr.or.connect.guestbook.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.service.GuestbookService;

@RestController
@RequestMapping(path="/guestbooks")
public class GuestbookApiController {
	// requestmapping을 이렇게 하면 guestbooks 안쪽으로 떨어지는 것들은 공통으로 사용 가능
	
	// 실행하면 dispatcherservlet이 json message converter를 내부적으로 사용해서 해당 map 객체를 json으로 변환해서 전송
	
	@Autowired
	GuestbookService guestbookService;
	
	// getmapping 뒤에 path가 없는 이유는 get방식으로 요청이 들어오면 list를 실행하게 될 것 
	
	@GetMapping
	public Map<String, Object> list(@RequestParam   (name="start",
												     required=false,
												     defaultValue = "0")
									int start) {
		List<Guestbook> list = guestbookService.getGuestbooks(start);
		
		int count = guestbookService.getCount();
		int pageCount = count / GuestbookService.LIMIT;
		
		if (count % GuestbookService.LIMIT > 0) {
			pageCount++;
		}
		
		List<Integer> pageStartList = new ArrayList<>();
		
		for (int i = 0; i < pageCount; i++) {
			pageStartList.add(i * GuestbookService.LIMIT);
		}
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("list", list);
		map.put("count", count);
		map.put("pageStartList", pageStartList);
		
		
		return map;
		
	}
	
	// post 방식의 요청을 처리하기 위한 postmapping
	@PostMapping
	public Guestbook write(@RequestBody Guestbook guestbook,
										HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		// id가 입력된 guestbook이 반환됨
		
		Guestbook resultGuestbook = guestbookService.addGuestbook(guestbook, clientIp);
		return resultGuestbook;
	}
	
	// guestbooks 뒤에 아이디 
	@DeleteMapping("/{id}")
	public Map<String, String> delete(@PathVariable (name="id") Long id,
																HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		int deleteCount = guestbookService.deleteGuestbook(id, clientIp);
		
		return Collections.singletonMap("success", deleteCount > 0 ? "true" : "false");
	}
	
	
	

}
