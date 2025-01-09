package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMCountry;

public class CRMCountryExtractor 
{
	public CRMCountry extractData(ResultSet resultSet) throws SQLException
	{
		CRMCountry country = new CRMCountry();
		
		country.setId(resultSet.getString(1));
		country.setSortname(resultSet.getString(2));
		country.setName(resultSet.getString(3));
		country.setPhonecode(resultSet.getString(4));
		
		return country;
	}
}
