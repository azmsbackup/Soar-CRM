package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMCity;

public class CRMCityExtractor 
{
	public CRMCity extractData(ResultSet resultSet) throws SQLException
	{
		CRMCity city = new CRMCity();
		
		city.setId(resultSet.getString(1));
		city.setName(resultSet.getString(2));
		city.setStateId(resultSet.getString(3));
		return city;
	}

}
