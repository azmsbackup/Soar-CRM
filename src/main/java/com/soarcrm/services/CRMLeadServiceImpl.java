package com.soarcrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMLeadDao;
import com.soarcrm.domain.CRMLead;

public class CRMLeadServiceImpl implements CRMLeadService{
	@Autowired
	CRMLeadDao leadDao;
	
	public void insertLeadData(CRMLead lead) throws Exception {
		leadDao.insertLeadData(lead);
	}
	public void updateLeadData(CRMLead lead) throws Exception {
		leadDao.updateLeadData(lead);
	}
	public List<CRMLead> getLeadList() throws Exception {
		return leadDao.getLeadList();
	}
	public List<CRMLead> geteditLead(String id) throws Exception {
		return leadDao.geteditLead(id);
	}
	public int checkLead(CRMLead lead) throws Exception {
		return leadDao.checkLead(lead);
	}
	public void updateLeadStatus(String id,String traceNo) throws Exception {
		leadDao.updateLeadStatus(id,traceNo);
	}
	public List<CRMLead> getLeadReport(CRMLead lead) throws Exception{
		return leadDao.getLeadReport(lead);
	}

}
