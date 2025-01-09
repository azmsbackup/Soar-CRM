package com.soarcrm.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.soarcrm.domain.CRMStatus;
import com.soarcrm.domain.Client;

public interface CRMReportDao 
{

	public List<Client> getstatusReportList(Client client)throws Exception;

	public List<Client> getfollowupReportList(Client client)throws Exception;

	public LinkedHashMap<String, LinkedHashMap<String, String>> getsummaryReportList(CRMStatus status) throws Exception;

	public List<Client> getsourceReportList(Client client)throws Exception;

	public List<Client> getswapReportList(Client client)throws Exception;

	public List<Client> getopenDealsList(String id, String roleid, String userid)throws Exception;

	public List<Client> getoverdueDealsList(Client client)throws Exception;

	public List<Client> getlogDeatils(Client client)throws Exception;

	public List<Client> getoverdueList(String statusid , String userid)throws Exception;

	public List<Client> getDepartmentDeatils(Client client)throws Exception;

	//public List<CRMStatus> productivityTotal(CRMStatus crmstatus)throws Exception;

	public CRMStatus responseandLeads(CRMStatus crmstatus)throws Exception;

	public List<Client> getmanagerOpenDealsList(String statusid)throws Exception;

	public List<Client> getmanagerOverdueList(String statusid)throws Exception;

	public LinkedHashMap<String, String> getdeptHashMap(CRMStatus crmstatus)throws Exception;

	public LinkedHashMap<String, List<CRMStatus>> getproductivityList(CRMStatus crmstatus)throws Exception;

	public List<Client> getEventDetails(Client client)throws Exception;

	public LinkedHashMap<String, LinkedHashMap<String, String>> getProductivityListforUsers(CRMStatus crmstatus) throws Exception;

	public LinkedHashMap<String, String> getUserHashMap(CRMStatus crmstatus) throws Exception;
	
	public List<Client> getAllClientList(Client client) throws Exception;
	
	public List<Client> getduplicateReportList(Client client) throws Exception;

	public List<CRMStatus> getProductivityCount(CRMStatus crmstatus) throws Exception;

	public List<Client> getsubStatusReportList(Client client) throws Exception;

	public List<Client> findDuplicates(String searchKey)throws Exception;
}
