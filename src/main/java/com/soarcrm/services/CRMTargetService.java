package com.soarcrm.services;

import java.util.List;

import com.soarcrm.domain.CRMTarget;

public interface CRMTargetService 
{

	public void insertTargets(CRMTarget target)throws Exception;

	public List<CRMTarget> getTargetList(String userid, String c)throws Exception;

	public CRMTarget getTargetDetails(String id)throws Exception;

	public void updateTarget(CRMTarget target)throws Exception;

	public CRMTarget checkTarget(CRMTarget target)throws Exception;

	public CRMTarget getUserStatus(String userId)throws Exception;
}
