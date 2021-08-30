package com.acoustic.search.controller.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acoustic.search.controller.service.IDemandTechSearchControllerService;
import com.acoustic.search.dao.ISearchDAO;
import com.acoustic.search.dto.SearchDTO;
import com.acoustic.search.dto.SearchDTOWrapper;
import com.acoustic.search.entities.EsignASPMaster;
import com.acoustic.search.exception.InvalidSearchCriteria;
import com.acoustic.search.model.SearchModel;

/**
 * @author nikhil.wankhade
 *
 */
@Service
public class DemantechSearchControllerServiceImpl implements IDemandTechSearchControllerService {
	
	private static final Logger log = LoggerFactory.getLogger(DemantechSearchControllerServiceImpl.class);
	
	@Autowired
	ISearchDAO searchDao;

	@Override
	public List<SearchDTO> searchGet(String query)  {
		
		log.info("inside searchGet");

		return search(query);
		
	}

	@Override
	public List<SearchDTO> searchPost(String query) throws InvalidSearchCriteria {
		log.info("inside searchPost");

		return search(query);
	}
	
	@Override
	public List<SearchDTO> search(String query){
		log.info("inside search");

		List<SearchDTO> searcList = new ArrayList<>();
		
		try {
			if(query != null && !query.isEmpty()) {
				
				//Optional<EsignASPMaster> esp = searchDao.findById(239);
				
				List<EsignASPMaster> searchByCompanyName = searchDao.searchStartWithCompanyName(query.toLowerCase());
				
				//Function<EsignASPMaster, SearchDTO> fun = (EsignASPMaster e) -> new SearchDTO();
						
				searcList = searchByCompanyName.stream().map( (EsignASPMaster e) -> {	
						return new SearchDTO("OK", e.getFirstName() + " " + e.getLastName(), e.getId(), e.getCompanyName());			
				}).collect(toList());
				
				
			}else {
				throw new InvalidSearchCriteria("ERR101:please provide proper search input!");
			}
			
		} catch (InvalidSearchCriteria e) {
			searcList.add(new SearchDTO("FAIL", e.getMessage(), -1, query));
		}
		
		
		return searcList;
	}
	
	@Override
	public SearchDTOWrapper searchWithPaging(SearchModel search){
		log.info("inside searchWithPaging");

		List<SearchDTO> searcList = null;
		
		SearchDTOWrapper w = null;
		
		int limit = 0;
		int offset = 0;
		String query = "";
		int totalRec = 0;
		List<EsignASPMaster> searchByCompanyName;
		
		try {
			query = search.getQ();
			limit = search.getPageSize(); //10 items per page
			offset = search.getPageNumber() * search.getPageSize(); // 1 * pagePerSize
			
			log.debug("query {}", query);
			log.debug("pageNumber {} pageSize {}", search.getPageNumber(), search.getPageSize());
			log.debug("query {} limit {} offset {}", query, limit, offset);
			
			Optional<String> q = Optional.ofNullable(query);
			
			log.debug("q.isPresent() {}", q.isPresent());
			//log.debug("q.isEmpty() {}", q.get().isEmpty());
						
			if(q.isPresent()) {
				if(query.isEmpty()) throw new InvalidSearchCriteria("ERR101:search input can't be empty string!");
			} else {
				throw new InvalidSearchCriteria("ERR102:please provide proper search input!");
			}
			
			log.debug("query length:{}", query.length());
			
			searcList = new ArrayList<>();
			
			searchByCompanyName = searchDao.searchStartWithCompanyNamePaginate(query.toLowerCase(), limit, offset);
			
			if(searchByCompanyName != null & searchByCompanyName.size() > 0 ) {
				totalRec = searchDao.searchTotalRecordsByCompanyName(query.toLowerCase(), limit, offset);
				log.debug("total records {}", totalRec);
			}
			
			searcList = searchByCompanyName.stream().map( (EsignASPMaster e) -> {	
					return new SearchDTO("OK", e.getFirstName() + " " + e.getLastName(), e.getId(), e.getCompanyName());			
			}).collect(toList());
			
			w = new SearchDTOWrapper(searcList, totalRec, 1);
			
		} catch (InvalidSearchCriteria e) {
			searcList = new ArrayList<>();
			searcList.add(new SearchDTO("FAIL", e.getMessage(), -1, query));
			w = new SearchDTOWrapper(searcList, totalRec, 1);
		}
		
		
		return w;
	}
	
	

}
