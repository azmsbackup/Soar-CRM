package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMLead;

public class CRMLeadRowMapper  implements RowMapper<CRMLead>{
	public CRMLead mapRow(ResultSet resultSet, int line) throws SQLException{
		CRMLeadExtractor crmLeadExtractor = new CRMLeadExtractor();		
		return crmLeadExtractor.extractData(resultSet);
	}
}
