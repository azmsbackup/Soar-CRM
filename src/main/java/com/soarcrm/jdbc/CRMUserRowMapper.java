package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMUser;

public class CRMUserRowMapper implements RowMapper<CRMUser>
{
	public CRMUser mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMUserExtractor userExtractor = new CRMUserExtractor();		
		return userExtractor.extractData(resultSet);
	}
}
