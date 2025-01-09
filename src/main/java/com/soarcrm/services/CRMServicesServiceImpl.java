package com.soarcrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMServicesDao;
import com.soarcrm.domain.CRMServices;
import com.soarcrm.domain.CRMStatus;

public class CRMServicesServiceImpl implements CRMServicesService 
{
	@Autowired 
	CRMServicesDao CRMservicesDao;
	
	@Override
	public List<CRMServices> getServicesList() throws Exception 
	{
		return CRMservicesDao.getServicesList();
	}

	@Override
	public void insertServices(CRMServices crmServices) throws Exception 
	{
		CRMservicesDao.insertServices(crmServices);	
	}

	@Override
	public CRMServices getServices(String id) throws Exception 
	{
		return CRMservicesDao.getServices(id);
	}

	@Override
	public void updateServices(CRMServices crmServices) throws Exception 
	{
		CRMservicesDao.updateServices(crmServices);	
	}

	@Override
	public CRMServices checkServicesDescription(CRMServices crmServices) throws Exception 
	{
		return CRMservicesDao.checkServicesDescription(crmServices);
	}

	@Override
	public CRMServices checkEditServicesDescription(CRMServices crmServices) throws Exception 
	{
		return CRMservicesDao.checkEditServicesDescription(crmServices);
	}
	


}
