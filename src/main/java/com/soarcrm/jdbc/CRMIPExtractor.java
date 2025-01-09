package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMIP;

public class CRMIPExtractor 
{
	public CRMIP extractData(ResultSet resultSet) throws SQLException
	{
		CRMIP IP = new CRMIP();
		IP.setIpId(resultSet.getString(1));
		IP.setIP(resultSet.getString(2));
		IP.setIPstatus(resultSet.getString(3));
		IP.setCreatedBy(resultSet.getString(4));
		IP.setCreatedDate(resultSet.getString(5));
		IP.setModifiedBy(resultSet.getString(6));
		IP.setModifiedDate(resultSet.getString(7));
	
		return IP;
		
	}

}
