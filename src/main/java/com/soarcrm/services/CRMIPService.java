package com.soarcrm.services;

import java.util.List;

import com.soarcrm.domain.CRMIP;

public interface CRMIPService 
{

	public void insertIP(CRMIP crmIPNo)throws Exception;

	public List<CRMIP> getIPList()throws Exception;

	public CRMIP getIP(String id)throws Exception;

	public void updateIP(CRMIP crmIPNo)throws Exception;

	public CRMIP checkIPNo(CRMIP crmIPNo)throws Exception;

	public CRMIP checkEditIPNo(CRMIP crmIP)throws Exception;

	public void deleteIP(String id)throws Exception;

	public void inactiveIp(CRMIP crmIPNo)throws Exception;

	public List<CRMIP> getIPListInactive()throws Exception;

}
