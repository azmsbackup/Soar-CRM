package com.soarcrm.dao;

import java.util.List;

import com.soarcrm.domain.CRMCity;
import com.soarcrm.domain.CRMCountry;
import com.soarcrm.domain.CRMState;

public interface CRMCountryStateCityDao 
{
	public List<CRMCountry> getCountryList()throws Exception;

	public List<CRMState> getStateList(String id)throws Exception;

	public List<CRMCity> getCityList(String id)throws Exception;

	public List<CRMState> getStateList()throws Exception;
}
