package com.acoustic.search.dto;

import java.util.List;

public class SearchDTOWrapper {
	
	List<SearchDTO> searchResult;
	int totalRecords;
	int pageindex;
	public List<SearchDTO> getSearchResult() {
		return searchResult;
	}
	
	public void setSearchResult(List<SearchDTO> searchResult) {
		this.searchResult = searchResult;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getPageindex() {
		return pageindex;
	}
	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public SearchDTOWrapper(List<SearchDTO> searchResult, int totalRecords, int pageindex) {
		super();
		this.searchResult = searchResult;
		this.totalRecords = totalRecords;
		this.pageindex = pageindex;
	}
	
	

}
