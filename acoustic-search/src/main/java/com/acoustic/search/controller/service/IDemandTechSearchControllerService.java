package com.acoustic.search.controller.service;

import java.util.List;

import com.acoustic.search.dto.SearchDTO;
import com.acoustic.search.dto.SearchDTOWrapper;
import com.acoustic.search.exception.InvalidSearchCriteria;
import com.acoustic.search.model.SearchModel;


/**
 * @author nikhil.wankhade
 *
 */
public interface IDemandTechSearchControllerService {
	

	public List<SearchDTO> searchGet(String query) throws InvalidSearchCriteria;
	
	public List<SearchDTO> searchPost(String query) throws InvalidSearchCriteria;

	List<SearchDTO> search(String query);

	public SearchDTOWrapper searchWithPaging(SearchModel q);

}
