package com.soarcrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMTargetDao;
import com.soarcrm.domain.CRMTarget;

public class CRMTargetServiceImpl implements CRMTargetService
{
	@Autowired
	CRMTargetDao CRMtargetDao;

	public void insertTargets(CRMTarget target) throws Exception 
	{
		CRMtargetDao.insertTargets(target);
	}

	public List<CRMTarget> getTargetList(String userid, String statusid) throws Exception 
	{
		return CRMtargetDao.getTargetList(userid, statusid);
	}

	public CRMTarget getTargetDetails(String id) throws Exception 
	{
		return CRMtargetDao.getTargetDetails(id);
	}

	public void updateTarget(CRMTarget target) throws Exception 
	{
		CRMtargetDao.updateTarget(target);
	}

	public CRMTarget checkTarget(CRMTarget target) throws Exception 
	{
		return CRMtargetDao.checkTarget(target);
	}

	public CRMTarget getUserStatus(String userId) throws Exception 
	{
		return CRMtargetDao.getUserStatus(userId);
	}

}
