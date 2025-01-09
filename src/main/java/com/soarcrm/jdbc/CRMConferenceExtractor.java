package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMConference;

public class CRMConferenceExtractor 
{
	public CRMConference extractData(ResultSet resultSet) throws SQLException
	{
		CRMConference event = new CRMConference();

		event.setEventId(resultSet.getString(1));
		event.setEventCode(resultSet.getString(2));
		event.setEventName(resultSet.getString(3));
		event.setEventMonth(resultSet.getString(4));
		event.setDescription(resultSet.getString(5));
		event.setDeptId(resultSet.getString(6));
		event.setCreatedBy(resultSet.getString(7));
		event.setCreatedDate(resultSet.getString(8));
		event.setModifiedBy(resultSet.getString(9));
		event.setModifiedDate(resultSet.getString(10));
		
		return event;
	}

}
