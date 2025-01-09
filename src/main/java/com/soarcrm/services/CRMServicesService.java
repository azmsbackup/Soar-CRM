package com.soarcrm.services;

import java.util.List;

import com.soarcrm.domain.CRMServices;
import com.soarcrm.domain.CRMStatus;

public interface CRMServicesService 
{

	public List<CRMServices> getServicesList() throws Exception;

	public void insertServices(CRMServices crmServices) throws Exception;

	public CRMServices getServices(String id) throws Exception;

	public void updateServices(CRMServices crmServices) throws Exception;

	public CRMServices checkServicesDescription(CRMServices crmServices) throws Exception;

	public CRMServices checkEditServicesDescription(CRMServices crmServices) throws Exception;

}
