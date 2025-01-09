package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMState;

public class CRMStateRowMapper implements RowMapper<CRMState>
{
	public CRMState mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMStateExtractor stateExtractor = new CRMStateExtractor();		
		return stateExtractor.extractData(resultSet);
	}

}
