package kr.or.connect.guestbook.dao;

public class GuestDaoSqls {
	public static final String SELECT_PAGING = "SELECT id, name, content, regdate FROM guestbookmvc ORDER BY id DESC limit :start, :limit";
	
	// Oracle 버전 rownum으로 limit 걸기
	// public static final String SELECT_PAGING = "SELECT id, name, content, regdate FROM guestbook ORDER BY id DESC WHERE ROWNUM <= start :ROWNUM";
	// limit은 시작 값, 끝 값 설정 가능 (mysql query) 특정 부분만 select해올 수 있음 
	public static final String DELETE_BY_ID = "DELETE FROM guestbookmvc WHERE id = :id";
	public static final String SELECT_COUNT = "SELECT count(*) FROM guestbookmvc";
	
	
	
	
	

}
