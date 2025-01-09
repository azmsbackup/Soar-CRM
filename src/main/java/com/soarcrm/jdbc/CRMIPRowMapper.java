package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMIP;

public class CRMIPRowMapper implements RowMapper<CRMIP>  
{
	public CRMIP mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMIPExtractor IPExtractor = new CRMIPExtractor();		
		return IPExtractor.extractData(resultSet);
	}

}
