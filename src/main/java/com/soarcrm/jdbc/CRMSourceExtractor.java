package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMSource;

public class CRMSourceExtractor 
{
	public CRMSource extractData(ResultSet resultSet) throws SQLException
	{
		CRMSource source = new CRMSource();

		source.setSourceId(resultSet.getString(1));
		source.setSourceDescription(resultSet.getString(2));
		source.setSourceStatus(resultSet.getString(3));
		source.setCreatedBy(resultSet.getString(4));
		source.setCreatedDate(resultSet.getString(5));
		source.setModifiedBy(resultSet.getString(6));
		source.setModifiedDate(resultSet.getString(7));
		
		return source;
	}

}
