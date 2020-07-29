package kr.or.connect.guestbook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.guestbook.dao.GuestbookDao;
import kr.or.connect.guestbook.dao.LogDao;
import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.dto.Log;
import kr.or.connect.guestbook.service.GuestbookService;

// dto에 repository annotation을 붙여 준 것처럼 service에는 service annotation을 붙임 
@Service
public class GuestbookServiceimpl implements GuestbookService {
	
	@Autowired
	GuestbookDao guestbookDao;
	
	// autowired annotation을 달면 알아서 객체생성을 도와줌
	@Autowired
	LogDao logDao;
	
	
	// method 구현 
	
	@Override
	@Transactional
	public List<Guestbook> getGuestbooks(Integer start) {
		// 페이징 처리되는것부터 몇개 이렇게 방명록 목록을 가져오도록 하는 method 
		List<Guestbook> list = guestbookDao.selectAll(start, GuestbookService.LIMIT); 
		
		// start의 경우 argument로 받고, LIMIT은 상수로 지정해 두었음
		// 이후 페이지에서 보여줄 방명록의 개수가 변경될 경우, GuestbookService 인터페이스에서 해당 상수값만 변경해주면 됨
		
		return list;
		
		// '읽기'만 하는 메서드는 transactional 이라는 annotation을 붙여 주면
		// 내부적으로 readOnly라는 형태로 connection을 사용하게 됨 
	}

	@Override
	@Transactional(readOnly=false)
	public int deleteGuestbook(Long id, String ip) {
		// 삭제를 한 후 Log에 delete action에 대한 로그를 남김 (id, ip, method, 삭제일자)
		int deleteCount = guestbookDao.deleteById(id);
		Log log = new Log();
		log.setId(id);
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegdate(new Date());
		logDao.insert(log); // set한 값을 바탕으로 log 생성
		
		return deleteCount;
	}

	@Override
	@Transactional(readOnly=false)
	public Guestbook addGuestbook(Guestbook guestbook, String ip) {
		guestbook.setRegdate(new Date());
		Long id = guestbookDao.insert(guestbook); // 자동으로 만들어진 id값을 얻어옴
		guestbook.setId(id); // id값도 채워져 guestbook이 완성됨 
		
		Log log = new Log();
		log.setId(id);
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegdate(new Date());
		logDao.insert(log);
		
		return guestbook;
	}

	@Override
	public int getCount() {
		// 페이징처리 등을 위해 전체 방명록의 개수를 가져오는 method
		return guestbookDao.selectCount();
	}
	
}
