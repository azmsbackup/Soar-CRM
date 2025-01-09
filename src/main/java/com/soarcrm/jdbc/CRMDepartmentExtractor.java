package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMDepartment;

public class CRMDepartmentExtractor 
{
	public CRMDepartment extractData(ResultSet resultSet) throws SQLException
	{
		CRMDepartment department = new CRMDepartment();

		department.setDepartmentId(resultSet.getString(1));
		department.setDepartmentCode(resultSet.getString(2));
		department.setDepartmentName(resultSet.getString(3));
		department.setStatus(resultSet.getString(4));
		department.setCreatedBy(resultSet.getString(5));
		department.setCreatedDate(resultSet.getString(6));
		department.setModifiedBy(resultSet.getString(7));
		department.setModifiedDate(resultSet.getString(8));
		
		return department;
	}

}
