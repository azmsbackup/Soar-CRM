package com.soarcrm.domain;

import java.util.List;

public class CRMState 
{
	private String id;
	private String name;
	private String countryId;
	private List<CRMState> statelist;
	private String phoneCode;
	private String countryName;
	
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getCountryId() 
	{
		return countryId;
	}
	public void setCountryId(String countryId) 
	{
		this.countryId = countryId;
	}
	public List<CRMState> getStatelist() 
	{
		return statelist;
	}
	public void setStatelist(List<CRMState> statelist) 
	{
		this.statelist = statelist;
	}
	public String getPhoneCode() 
	{
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) 
	{
		this.phoneCode = phoneCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
