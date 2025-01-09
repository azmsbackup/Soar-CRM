package com.soarcrm.dao;

import java.util.List;

import com.soarcrm.domain.CRMSource;
import com.soarcrm.domain.CRMTarget;

public interface CRMTargetDao 
{

	public void insertTargets(CRMTarget target)throws Exception;

	public List<CRMTarget> getTargetList(String userid, String statusid)throws Exception;

	public CRMTarget getTargetDetails(String id)throws Exception;

	public void updateTarget(CRMTarget target)throws Exception;

	public CRMTarget checkTarget(CRMTarget target)throws Exception;

	public CRMTarget getUserStatus(String userId)throws Exception;

}
