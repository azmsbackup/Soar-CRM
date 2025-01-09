package com.soarcrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMConferenceDao;
import com.soarcrm.domain.CRMConference;
import com.soarcrm.domain.CRMDepartment;

public class CRMConferenceServiceImpl implements CRMConferenceService 
{
	@Autowired
	CRMConferenceDao CRMconferenceDao;
	
	public void insertEvent(CRMConference conference) throws Exception 
	{
		CRMconferenceDao.insertEvent(conference);
	}

	public List<CRMDepartment> getDepartmentId() throws Exception 
	{
		return CRMconferenceDao.getDepartmentId();
	}

	public List<CRMConference> getEventList() throws Exception 
	{
		return CRMconferenceDao.getEventList();
	}

	public CRMConference getEventDetails(String id) throws Exception 
	{
		return CRMconferenceDao.getEventDetails(id);
	}

	public void updateEvent(CRMConference conference) throws Exception 
	{
		 CRMconferenceDao.updateEvent(conference);
	}

}
