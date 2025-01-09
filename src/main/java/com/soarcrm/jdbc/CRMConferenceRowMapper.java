package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMConference;

public class CRMConferenceRowMapper  implements RowMapper<CRMConference> 
{
	public CRMConference mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMConferenceExtractor conferenceExtractor = new CRMConferenceExtractor();		
		return conferenceExtractor.extractData(resultSet);
	}
}
