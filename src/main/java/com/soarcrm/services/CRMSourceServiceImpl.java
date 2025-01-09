package com.soarcrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMSourceDao;
import com.soarcrm.domain.CRMSource;

public class CRMSourceServiceImpl implements CRMSourceService 
{
	@Autowired 
	CRMSourceDao CRMsourceDao;
	
	public void insertSource(CRMSource source) throws Exception 
	{
		CRMsourceDao.insertSource(source);
	}

	public List<CRMSource> getSourceList() throws Exception 
	{
		return CRMsourceDao.getSourceList();
	}

	public CRMSource getSource(String id) throws Exception
	{
		return CRMsourceDao.getSource(id);
	}

	public void updateSource(CRMSource crmsource) throws Exception 
	{
		CRMsourceDao.updateSource(crmsource);
		
	}

	public CRMSource checkSourceDescription(CRMSource source) throws Exception 
	{
		return CRMsourceDao.checkSourceDescription(source);
	}

	public CRMSource checkEditSourceDescription(CRMSource crmsource) throws Exception 
	{
		return CRMsourceDao.checkEditSourceDescription(crmsource);
	}

	public void inactiveSource(CRMSource crmsource) throws Exception 
	{
		CRMsourceDao.inactiveSource(crmsource);		
	}

	public List<CRMSource> getSourceListforevent() throws Exception 
	{
		return CRMsourceDao.getSourceListforevent();		
	}

	@Override
	public List<CRMSource> getActiveSourceList() throws Exception {
		return CRMsourceDao.getActiveSourceList();
	}

}
