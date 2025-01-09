package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMSource;

public class CRMSourceRowMapper implements RowMapper<CRMSource>  
{
	public CRMSource mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMSourceExtractor sourceExtractor = new CRMSourceExtractor();		
		return sourceExtractor.extractData(resultSet);
	}

}
