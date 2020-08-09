package com.heegene.common;

public class Search extends Pagination {
	private String searchType;
	private String keyword;
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	// keyword와 searchType을 boardMapper에서 사용
	// boardmapper에서는 dynamic query로 조건에 따른 검색을 적용하여 반환
	
}
