package com.soarcrm.services;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMStatusDao;
import com.soarcrm.domain.CRMStatus;

public class CRMStatusServiceImpl implements CRMStatusService 
{
	@Autowired
	CRMStatusDao CRMstatusDao;

	public void insertStatus(CRMStatus status) throws Exception 
	{
		CRMstatusDao.insertStatus(status);
	}

	public List<CRMStatus> getStatusList() throws Exception 
	{
		return CRMstatusDao.getStatusList();
	}

	public CRMStatus getStatus(String id) throws Exception 
	{
		return CRMstatusDao.getStatus(id);
	}

	public void updateStatus(CRMStatus crmstatus) throws Exception 
	{
		 CRMstatusDao.updateStatus(crmstatus);
		
	}

	public CRMStatus getDashboardDetails(CRMStatus crmstatus) throws Exception 
	{
		return CRMstatusDao.getDashboardDetails(crmstatus);
	}

	public CRMStatus getdashboardDataList(CRMStatus crmstatus) throws Exception
	{
		return CRMstatusDao.getdashboardDataList(crmstatus);
	}

	public CRMStatus checkStatusDescription(CRMStatus status) throws Exception 
	{
		return CRMstatusDao.checkStatusDescription(status);
	}

	public CRMStatus checkEditDuplicateStatusDescription(CRMStatus status) throws Exception 
	{
		return CRMstatusDao.checkEditDuplicateStatusDescription(status);
	}

	public List<CRMStatus> getStatusList(String closed, String open) throws Exception 
	{
		return CRMstatusDao.getStatusList(closed, open);
	}

	public CRMStatus getStatusChart(CRMStatus crmstatus) throws Exception 
	{
		return CRMstatusDao.getStatusChart(crmstatus);
	}

	public  LinkedHashMap<String, List<CRMStatus>> getUserHashmapList(CRMStatus crmstatus) throws Exception 
	{
		return CRMstatusDao.getUserHashmapList(crmstatus);
	}

	/*public CRMStatus getManagerViewdashboardDataList(CRMStatus crmstatus) throws Exception
	{
		return CRMstatusDao.getManagerViewdashboardDataList(crmstatus);
	}*/

	public CRMStatus getDashboardDetailsformanager(CRMStatus crmstatus) throws Exception 
	{
		return CRMstatusDao.getDashboardDetailsformanager(crmstatus);
	}

	public List<CRMStatus> getTargetStatusList() throws Exception 
	{
		return CRMstatusDao.getTargetStatusList();
	}

	/*public CRMStatus getUserDashboardDataList(CRMStatus crmstatus) throws Exception 
	{
		return CRMstatusDao.getUserDashboardDataList(crmstatus);
	}*/

	public List<CRMStatus> getClientStatusList() throws Exception 
	{
		return CRMstatusDao.getClientStatusList();
	}

	public void inactiveStatus(CRMStatus status) throws Exception 
	{
		CRMstatusDao.inactiveStatus(status);
		
	}
	
	public List<CRMStatus> getStatusListwithInactive() throws Exception 
	{
		return CRMstatusDao.getStatusListwithInactive();
	}

	public List<CRMStatus> getStatusListExceptopen(String statusIdOpen) throws Exception 
	{
		return CRMstatusDao.getStatusListExceptopen(statusIdOpen);
	}
}
