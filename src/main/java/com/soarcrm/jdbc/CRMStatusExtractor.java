package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMStatus;

public class CRMStatusExtractor
{
	public CRMStatus extractData(ResultSet resultSet) throws SQLException
	{
		CRMStatus status = new CRMStatus();
		
		status.setStatusId(resultSet.getString(1));
		status.setStatusDescription(resultSet.getString(2));
		status.setCrmstatus(resultSet.getString(3));
		status.setCreatedBy(resultSet.getString(4));
		status.setCreatedDate(resultSet.getString(5));
		status.setModifiedBy(resultSet.getString(6));
		status.setModifiedDate(resultSet.getString(7));
		
		return status;
		
	}

}
