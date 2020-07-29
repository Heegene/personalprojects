package kr.or.connect.guestbook.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.service.GuestbookService;

@Controller
public class GuestbookController {
	
	
	@Autowired
	GuestbookService guestbookService;
	
	
	// path가 /list로 들어왔을 때 처리할 부분 
	@GetMapping(path="/list")
	public String list(@RequestParam (
										name = "start", // start 라는 값을 꺼내서 사용 
										required= false,
										defaultValue = "0" // 값이 없는 경우 0이라는 디폴트 값을 줌(0부터 시작하므로)
										)
						int start,
						ModelMap model) {
		// start로 시작하는 방명록 목록 구하기
		List<Guestbook> list = guestbookService.getGuestbooks(start); // start를 인자로 넣어 전체 페이지 수 구함
		
		// 전체 방명록 수를 구한 후 페이지 수 구하기 
		int count = guestbookService.getCount(); // 전체 방명록의 수 불러옴
		
		int pageCount = count / GuestbookService.LIMIT; // 전체 수를 한 페이지당 보여줄 guestbook 수로 나눔(int이므로 소숫점 버려짐)
		
		if (count % GuestbookService.LIMIT > 0 ) {
			pageCount++; // 소숫점이 버려지나 글이 6개여도 2페이지로 보여져야 하므로
			// count/limit의 나머지가 발생하는 경우 페이지 수에 1을 더해 줌 
		}
		
		// 페이지의 수 만큼 start의 값을 리스트로 저장
		// 예시: 페이지수가 3이면 0, 5, 10 이렇게 저장됨
		// list?star=0, list?start=5, list?start=10 으로 링크가 걸림
		List<Integer> pageStartList = new ArrayList<>();
		for (int i = 0; i < pageCount; i++) {
			pageStartList.add(i * GuestbookService.LIMIT);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		
		return "list";
		
	}
			

}
