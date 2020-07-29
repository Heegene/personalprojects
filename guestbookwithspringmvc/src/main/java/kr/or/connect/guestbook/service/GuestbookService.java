package kr.or.connect.guestbook.service;

import java.util.List;


import kr.or.connect.guestbook.dto.Guestbook;

public interface GuestbookService {
	
	// 비즈니스가 어떤것이 있을 것인지 	형태를 잡음
	
	public static final Integer LIMIT = 5;
	public List<Guestbook> getGuestbooks(Integer start);
	public int deleteGuestbook (Long id, String ip);
	public Guestbook addGuestbook (Guestbook guestbook, String ip);
	public int getCount();
	

}
