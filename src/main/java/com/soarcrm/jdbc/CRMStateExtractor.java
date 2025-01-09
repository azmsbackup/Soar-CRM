package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMState;

public class CRMStateExtractor 
{
	public CRMState extractData(ResultSet resultSet) throws SQLException
	{
		CRMState state = new CRMState();
		
		state.setId(resultSet.getString(1));
		state.setName(resultSet.getString(2));
		state.setCountryId(resultSet.getString(3));
		state.setPhoneCode(resultSet.getString(4));
	
		return state;
	}

}
