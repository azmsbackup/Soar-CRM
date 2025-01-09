package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMNotes;

public class CRMNotesRowMapper implements RowMapper<CRMNotes>
{
	public CRMNotes mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMNotesExtractor notesExtractor = new CRMNotesExtractor();		
		return notesExtractor.extractData(resultSet);
	}

}
