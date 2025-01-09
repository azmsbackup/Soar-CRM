package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMServices;

public class CRMServicesExtractor 
{
	public CRMServices extractData(ResultSet resultSet) throws SQLException
	{
		CRMServices services = new CRMServices();
		services.setServicesId(resultSet.getString(1));
		services.setServicesDescription(resultSet.getString(2));
		services.setCreatedBy(resultSet.getString(3));
		services.setCreatedDate(resultSet.getString(4));
		services.setModifiedBy(resultSet.getString(5));
		services.setModifiedDate(resultSet.getString(6));
	
		return services;
	}
}
