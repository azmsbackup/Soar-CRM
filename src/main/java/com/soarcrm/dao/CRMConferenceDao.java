package com.soarcrm.dao;

import java.util.List;

import com.soarcrm.domain.CRMConference;
import com.soarcrm.domain.CRMDepartment;

public interface CRMConferenceDao {

	public void insertEvent(CRMConference conference)throws Exception;

	public List<CRMDepartment> getDepartmentId()throws Exception;

	public List<CRMConference> getEventList()throws Exception;

	public CRMConference getEventDetails(String id)throws Exception;

	public void updateEvent(CRMConference conference)throws Exception;
}
