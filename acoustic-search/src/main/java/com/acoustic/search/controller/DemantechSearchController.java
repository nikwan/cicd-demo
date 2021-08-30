package com.acoustic.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acoustic.search.controller.service.IDemandTechSearchControllerService;
import com.acoustic.search.dto.SearchDTO;
import com.acoustic.search.dto.SearchDTOWrapper;
import com.acoustic.search.exception.InvalidSearchCriteria;
import com.acoustic.search.model.SearchModel;

/**
 * @author nikhil.wankhade
 *
 */
@RestController
@RequestMapping("/search")
public class DemantechSearchController {
	
	private static final Logger log = LoggerFactory.getLogger(DemantechSearchController.class);
	
	@Autowired
	IDemandTechSearchControllerService service;
	
	
	@CrossOrigin(origins = "http://localhost:4200")	
	@RequestMapping(value = "/search-with-get", method = RequestMethod.GET)
	public List<SearchDTO> searchWithGet(@RequestParam String q) throws InterruptedException, InvalidSearchCriteria {
		log.info("inside searchWithGet");
		
		log.info("q: {}", q);
				
		return service.searchGet(q);
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")	
	@RequestMapping(value = "/search-with-post", method = RequestMethod.POST)
	public List<SearchDTO> searchWithPost(@RequestBody SearchDTO q) throws InvalidSearchCriteria {
		log.info("inside searchWithPost");
		
		log.info("q: {}", q);
		
		log.debug("name len: {}", q.getName().length());
		
		return service.searchPost(q.getName());
	}
	
	@CrossOrigin(origins = "http://localhost:4200")	
	@RequestMapping(value = "/search-with-paging", method = RequestMethod.POST)
	public SearchDTOWrapper searchWithPaging(@RequestBody SearchModel q) throws InvalidSearchCriteria {
		log.info("inside searchWithPaging");
		
		log.info("query: {}", q);
		
		//log.debug("name len: {}", q.getQ().length());
		
		return service.searchWithPaging(q);
	}
	
	
}
