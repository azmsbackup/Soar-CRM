package com.soarcrm.services;

import java.util.LinkedHashMap;
import java.util.List;

import com.soarcrm.domain.CRMStatus;

public interface CRMStatusService {

	public void insertStatus(CRMStatus status)throws Exception;

	public List<CRMStatus> getStatusList()throws Exception;

	public CRMStatus getStatus(String id)throws Exception;

	public void updateStatus(CRMStatus crmstatus)throws Exception;

	public CRMStatus getDashboardDetails(CRMStatus crmstatus)throws Exception;

	public CRMStatus getdashboardDataList(CRMStatus crmstatus) throws Exception;

	public CRMStatus checkStatusDescription(CRMStatus status)throws Exception;

	public CRMStatus checkEditDuplicateStatusDescription(CRMStatus status)throws Exception;

	public List<CRMStatus> getStatusList(String closed, String open)throws Exception;

	public CRMStatus getStatusChart(CRMStatus crmstatus)throws Exception;

	public LinkedHashMap<String, List<CRMStatus>> getUserHashmapList(CRMStatus crmstatus)throws Exception;

	//public CRMStatus getManagerViewdashboardDataList(CRMStatus crmstatus)throws Exception;

	public CRMStatus getDashboardDetailsformanager(CRMStatus crmstatus)throws Exception;

	public List<CRMStatus> getTargetStatusList()throws Exception;

	//public CRMStatus getUserDashboardDataList(CRMStatus crmstatus)throws Exception;

	public List<CRMStatus> getClientStatusList()throws Exception;

	public void inactiveStatus(CRMStatus status)throws Exception;

	public List<CRMStatus> getStatusListwithInactive()throws Exception;

	public List<CRMStatus> getStatusListExceptopen(String statusIdOpen)throws Exception;

}
