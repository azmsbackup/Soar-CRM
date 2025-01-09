package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMCity;

public class CRMCityRowMapper implements RowMapper<CRMCity>  
{
	public CRMCity mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMCityExtractor conferenceExtractor = new CRMCityExtractor();		
		return conferenceExtractor.extractData(resultSet);
	}
}
