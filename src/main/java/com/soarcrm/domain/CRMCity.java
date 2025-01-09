package com.soarcrm.domain;

import java.util.List;

public class CRMCity 
{
	private String id;
	private String name;
	private String stateId;
	private String countryId;
	private String back;
	private String stateName;
	private List<CRMCity> citylist;
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
	public String getStateId() 
	{
		return stateId;
	}
	public void setStateId(String stateId) 
	{
		this.stateId = stateId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getBack() 
	{
		return back;
	}
	public void setBack(String back) 
	{
		this.back = back;
	}
	public List<CRMCity> getCitylist() 
	{
		return citylist;
	}
	public void setCitylist(List<CRMCity> citylist) 
	{
		this.citylist = citylist;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
