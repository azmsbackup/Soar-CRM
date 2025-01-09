package com.soarcrm.dao;

import java.util.List;

import com.soarcrm.domain.CRMSource;

public interface CRMSourceDao 
{
	public void insertSource(CRMSource source)throws Exception;

	public List<CRMSource> getSourceList()throws Exception;

	public CRMSource getSource(String id)throws Exception;

	public void updateSource(CRMSource crmsource)throws Exception;

	public CRMSource checkSourceDescription(CRMSource source)throws Exception;

	public CRMSource checkEditSourceDescription(CRMSource crmsource)throws Exception;

	public void inactiveSource(CRMSource crmsource)throws Exception;

	public List<CRMSource> getSourceListforevent()throws Exception;

	public List<CRMSource> getActiveSourceList() throws Exception;


}
