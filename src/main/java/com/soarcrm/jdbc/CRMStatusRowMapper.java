package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMStatus;

public class CRMStatusRowMapper  implements RowMapper<CRMStatus> 
{
	public CRMStatus mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMStatusExtractor statusExtractor = new CRMStatusExtractor();		
		return statusExtractor.extractData(resultSet);
	}

}
