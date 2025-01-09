package com.soarcrm.dao;

import java.util.List;

import com.soarcrm.domain.CRMLead;

public interface CRMLeadDao {
	public void insertLeadData(CRMLead lead) throws Exception;
	public void updateLeadData(CRMLead lead) throws Exception;
	public List<CRMLead> getLeadList() throws Exception;
	public List<CRMLead> geteditLead(String id) throws Exception;
	public int checkLead(CRMLead lead) throws Exception;
	public void updateLeadStatus(String id,String traceNo) throws Exception;
	public List<CRMLead> getLeadReport(CRMLead lead) throws Exception;
}
