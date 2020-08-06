package com.heegene.common;

public class Pagination {
	private int listSize = 10; // 목록 개수 10개로 설정
	private int rangeSize = 10; // 페이지 범위(보여줄 페이지의 범위) 10으로 설정
	private int page;
	private int range; 
	private int listCnt;
	private int pageCnt;
	private int startPage;
	private int startList;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	
	
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public int getRangeSize() {
		return rangeSize;
	}
	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getListCnt() {
		return listCnt;
	}
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getStartList() {
		return startList;
	}
	public void setStartList(int startList) {
		this.startList = startList;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	// 3개의 파라미터 현재 페이지 정보, 현재 페이지 범위 정보, 게시물의 총 개수를 인자로 받음
	
	public void pageInfo(int page, int range, int listCnt) {
		this.page = page;
		this.range = range;
		this.listCnt = listCnt;
		
		// 전체 페이지 수
		this.pageCnt = (int) Math.ceil(listCnt/listSize); // 게시글이 11개여도 2페이지가 나와야 하므로 ceil
		
		// 시작 페이지
		this.startPage = (range - 1)* rangeSize + 1; // (페이지 범위 -1) * 10   + 1  == 1이면 1 2면 2..
		// 각 페이지 범위의 시작 번호를 구함 
		
		// 끝 페이지
		this.endPage = range * rangeSize; // 1페이지면 10페이지까지 보여주도록 *10 (각 페이지 범위의 끝번호) 
		
		// 게시판 시작 번호
		this.startList = (page - 1) * listSize; // 현재 페이지가 2페이지면 20번부터 게시판 글이 시작되도록 함
		
		// 이전 버튼 상태
		this.prev = range == 1 ? false : true; // range(페이지 범위)가 1이면 false, 1이 아니면 true
		// 1페이지 이후라면 활성화되어 있는 상태임
		
		// 다음 버튼 상태
		this.next = endPage > pageCnt ? false : true; // 현재 페이지보다 남아있는 페이지가 클 경우 활성화
		
		// 페이지가 123개만 필요한데 130페이지가 생성되지 않도록 마지막 번호가 총 개수보다 크면
		// 마지막 번호로 세팅되도록 함 
		if (this.endPage > this.pageCnt) { 
			this.endPage = this.pageCnt; 
			this.next = false;
		}
		
		
		
		
	}

}
