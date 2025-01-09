package com.soarcrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMCountryStateCityDao;
import com.soarcrm.domain.CRMCity;
import com.soarcrm.domain.CRMCountry;
import com.soarcrm.domain.CRMState;

public class CRMCountryStateCityServiceImpl implements CRMCountryStateCityService
{
	@Autowired 
	CRMCountryStateCityDao CRMcountryStateCityDao;
	
	public List<CRMCountry> getCountryList() throws Exception
	{
		return CRMcountryStateCityDao.getCountryList();
	}

	public List<CRMState> getStateList(String id) throws Exception 
	{
		return CRMcountryStateCityDao.getStateList(id);
	}

	public List<CRMCity> getCityList(String id) throws Exception 
	{
		return CRMcountryStateCityDao.getCityList(id);
	}

	public List<CRMState> getStateList() throws Exception 
	{
		return CRMcountryStateCityDao.getStateList();		
	}

}
