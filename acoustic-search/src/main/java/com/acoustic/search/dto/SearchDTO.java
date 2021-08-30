package com.acoustic.search.dto;

/**
 * @author nikhil.wankhade
 *
 */
public class SearchDTO {
	
	String sts;
	String name;
	int id;
	String companyName;
	
	public SearchDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public SearchDTO(String sts, String name, int id, String companyName) {
		super();
		this.sts = sts;
		this.name = name;
		this.id = id;
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "AngularTest [sts=" + sts + ", name=" + name + ", id=" + id + ", companyName=" + companyName + "]";
	}
	

}
