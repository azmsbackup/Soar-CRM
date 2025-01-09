package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMIP;
import com.soarcrm.domain.CRMServices;

public class CRMServicesRowMapper  implements RowMapper<CRMServices> 
{
	public CRMServices mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMServicesExtractor servicesExtractor = new CRMServicesExtractor();		
		return servicesExtractor.extractData(resultSet);
	}

}
