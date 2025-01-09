package com.soarcrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMIPDao;
import com.soarcrm.domain.CRMIP;

public class CRMIPServiceImpl implements CRMIPService 
{
	@Autowired
	CRMIPDao CRMipDao;

	public void insertIP(CRMIP crmIPNo) throws Exception 
	{
		CRMipDao.insertIP(crmIPNo);
	}

	public List<CRMIP> getIPList() throws Exception 
	{
		return CRMipDao.getIPList();
	}

	public CRMIP getIP(String id) throws Exception 
	{
		return CRMipDao.getIP(id);
	}

	public void updateIP(CRMIP crmIPNo) throws Exception {
		 CRMipDao.updateIP(crmIPNo);
		
	}

	public CRMIP checkIPNo(CRMIP crmIPNo) throws Exception 
	{
		return  CRMipDao.checkIPNo(crmIPNo);
	}

	public CRMIP checkEditIPNo(CRMIP crmIP) throws Exception
	{
		return  CRMipDao.checkEditIPNo(crmIP);
	}

	public void deleteIP(String id) throws Exception 
	{
		  CRMipDao.deleteIP(id);
	}

	public void inactiveIp(CRMIP crmIPNo) throws Exception 
	{
		 CRMipDao.inactiveIp(crmIPNo);		
	}

	public List<CRMIP> getIPListInactive() throws Exception 
	{
		return  CRMipDao.getIPListInactive();	
	}

}
