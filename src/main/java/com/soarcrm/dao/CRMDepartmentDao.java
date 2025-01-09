package com.soarcrm.dao;

import java.util.ArrayList;
import java.util.List;

import com.soarcrm.domain.CRMDepartment;

public interface CRMDepartmentDao 
{

	public void insertDepartment(CRMDepartment department)throws Exception;

	public List<CRMDepartment> getDepartmentList()throws Exception;

	public CRMDepartment getDepartment(String id)throws Exception;
	
	public void updateDepartment(CRMDepartment department)throws Exception;

	public CRMDepartment checkDepartmentname(CRMDepartment department)throws Exception;

	public CRMDepartment checkEditDepartmentname(CRMDepartment department)throws Exception;

	public void insertUserDeptMap(CRMDepartment department)throws Exception;

	public List<CRMDepartment> getUserDepartmentList(String userid)throws Exception;

	public ArrayList<CRMDepartment> getUsermappingList(String id)throws Exception;

	public ArrayList<CRMDepartment> getUsermappingListforuser(String id)throws Exception;

	public void inactiveDepartment(CRMDepartment department)throws Exception;

	public List<CRMDepartment> getDepartmentListwithInactive()throws Exception;

}
