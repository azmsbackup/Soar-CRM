package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMCountry;

public class CRMCountryRowMapper implements RowMapper<CRMCountry>  
{
	public CRMCountry mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMCountryExtractor countryExtractor = new CRMCountryExtractor();		
		return countryExtractor.extractData(resultSet);
	}

}
