package com.soarcrm.services;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMReportDao;
import com.soarcrm.domain.CRMStatus;
import com.soarcrm.domain.Client;

public class CRMReportServiceImpl implements CRMReportService
{
	@Autowired
	CRMReportDao CRMreportDao;

	public List<Client> getstatusReportList(Client client) throws Exception 
	{
		return CRMreportDao.getstatusReportList(client);
	}

	public List<Client> getfollowupReportList(Client client) throws Exception 
	{
		return CRMreportDao.getfollowupReportList(client);
	}

	public LinkedHashMap<String, LinkedHashMap<String, String>> getsummaryReportList(CRMStatus status) throws Exception 
	{
		return CRMreportDao.getsummaryReportList(status);
	}

	public List<Client> getsourceReportList(Client client) throws Exception 
	{
		return CRMreportDao.getsourceReportList(client);
	}

	public List<Client> getswapReportList(Client client) throws Exception 
	{
		return CRMreportDao.getswapReportList(client);
	}

	public List<Client> getopenDealsList(String id, String roleid, String userid) throws Exception 
	{
		return CRMreportDao.getopenDealsList(id, roleid, userid);
	}

	public List<Client> getoverdueDealsList(Client client) throws Exception 
	{
		return CRMreportDao.getoverdueDealsList(client);
	}

	public List<Client> getlogDeatils(Client client) throws Exception
	{
		return CRMreportDao.getlogDeatils(client);
	}

	public List<Client> getoverdueList(String statusid , String userid) throws Exception 
	{
		return CRMreportDao.getoverdueList(statusid, userid);
	}
	public List<Client> getDepartmentDeatils(Client client) throws Exception 
	{
		return CRMreportDao.getDepartmentDeatils(client);
	}
	/*public List<CRMStatus> productivityTotal(CRMStatus crmstatus) throws Exception 
	{
		return CRMreportDao.productivityTotal(crmstatus);
	}*/
	public CRMStatus responseandLeads(CRMStatus crmstatus) throws Exception 
	{
		return CRMreportDao.responseandLeads(crmstatus);
	}

	public List<Client> getmanagerOpenDealsList(String statusid) throws Exception 
	{
		return CRMreportDao.getmanagerOpenDealsList(statusid);
	}

	public List<Client> getmanagerOverdueList(String statusid) throws Exception 
	{
		return CRMreportDao.getmanagerOverdueList(statusid);
	}

	public LinkedHashMap<String, String> getdeptHashMap(CRMStatus crmstatus) throws Exception 
	{
		return CRMreportDao.getdeptHashMap(crmstatus);
	}

	public LinkedHashMap<String, List<CRMStatus>> getproductivityList(CRMStatus crmstatus) throws Exception 
	{
		return CRMreportDao.getproductivityList(crmstatus);
	}

	@Override
	public List<Client> getEventDetails(Client client) throws Exception 
	{
		return CRMreportDao.getEventDetails(client);
	}

	@Override
	public LinkedHashMap<String, LinkedHashMap<String, String>> getProductivityListforUsers(CRMStatus crmstatus)
			throws Exception 
	{
		return CRMreportDao.getProductivityListforUsers(crmstatus);
	}

	public LinkedHashMap<String, String> getUserHashMap(CRMStatus crmstatus) throws Exception 
	{
		return CRMreportDao.getUserHashMap(crmstatus);
	}
	
	public List<Client> getAllClientList(Client client) throws Exception
	{
		return CRMreportDao.getAllClientList(client);
	}
	
	public List<Client> getduplicateReportList(Client client) throws Exception{
		
		return CRMreportDao.getduplicateReportList(client);
	}
	
	public List<CRMStatus> getProductivityCount(CRMStatus crmstatus) throws Exception {
		return CRMreportDao.getProductivityCount(crmstatus);
	}

	@Override
	public List<Client> getsubStatusReportList(Client client) throws Exception {
		return CRMreportDao.getsubStatusReportList(client);
	}

	@Override
	public List<Client> findDuplicates(String searchKey) throws Exception {
		
		return  CRMreportDao.findDuplicates(searchKey);
	}

}
