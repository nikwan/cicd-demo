package com.acoustic.search.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acoustic.search.entities.EsignASPMaster;

/**
 * @author nikhil.wankhade
 *
 */
@Repository("searchDao")
public interface ISearchDAO extends JpaRepository<EsignASPMaster, Integer>{
	
	@Query("SELECT e FROM EsignASPMaster e where lower(e.companyName) like %?1%")
	List<EsignASPMaster> searchWithContainCompanyName(String query);
	
	@Query("SELECT e FROM EsignASPMaster e where lower(e.companyName) like ?1%")
	List<EsignASPMaster> searchStartWithCompanyName(String query);
	
	@Query(nativeQuery = true, value =  "SELECT * FROM esp_master  where lower(company_Name) like ?% limit ? offset ?")
	List<EsignASPMaster> searchStartWithCompanyNamePaginate(String query, int limit, int offset);
	
	@Query(nativeQuery = true, value =  "SELECT count(1) over() as totalRec FROM esp_master  where lower(company_Name) like ?% limit 1")
	Integer searchTotalRecordsByCompanyName(String query, int limit, int offset);
	

}
