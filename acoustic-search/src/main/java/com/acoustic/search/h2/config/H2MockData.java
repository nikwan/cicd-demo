package com.acoustic.search.h2.config;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.acoustic.search.controller.service.impl.DemantechSearchControllerServiceImpl;
import com.acoustic.search.dao.ISearchDAO;
import com.acoustic.search.dto.MockDataBean;
import com.acoustic.search.entities.EsignASPMaster;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author nikhil.wankhade
 *
 */
@Service
public class H2MockData {
	
	@Autowired
	ISearchDAO searchDao;
	
	private static final Logger log = LoggerFactory.getLogger(DemantechSearchControllerServiceImpl.class);
	
	@Bean
	public void loadInMemoryData(){
		log.info("inside loadInMemoryData");
		
		log.info("loading data....");
		
		ObjectMapper map = new ObjectMapper();
		
		try (InputStream is = getClass().getResourceAsStream("/MOCK_DATA.json")){
			
			//String f = getClass().getClassLoader().getResource("MOCK_DATA.json").getFile();
			//String f = getClass().getResourceAsStream("MOCK_DATA.json");
			
			//List<MockDataBean> mockList = map.readValue(new File(f), new TypeReference<List<MockDataBean>>(){});
			List<MockDataBean> mockList = map.readValue(is, new TypeReference<List<MockDataBean>>(){});
			
			log.debug("===total {} records loaded===", mockList.size());
			
			List<EsignASPMaster> entitiesList = mockList.stream().map( new Function<MockDataBean, EsignASPMaster>() {
				public EsignASPMaster apply(MockDataBean t) {
					
					return new EsignASPMaster(t.getId(), t.getFirstName(), t.getLastName(), t.getEmail(), t.getGender(), t.getIp(), t.getCompanyName());
					
				};
			}).collect(Collectors.toList());
		
			
			searchDao.saveAll(entitiesList);
			
			log.info("===data inserted in esp_master table===");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		log.info("loading data finished....");
		
	}

}
