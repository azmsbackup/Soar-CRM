package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMTarget;

public class CRMTargetRowMapper implements RowMapper<CRMTarget>  
{
	public CRMTarget mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMTargetExtractor targetExtractor = new CRMTargetExtractor();		
		return targetExtractor.extractData(resultSet);
	}

}
