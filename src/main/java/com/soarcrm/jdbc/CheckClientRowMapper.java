package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.Client;

public class CheckClientRowMapper implements RowMapper<Client>  
{
	public Client mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CheckClientExtractor checkclientExtractor = new CheckClientExtractor();		
		return checkclientExtractor.extractData(resultSet);
	}
}