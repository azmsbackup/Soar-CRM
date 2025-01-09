package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMDepartment;

public class CRMDepartmentRowMapper implements RowMapper<CRMDepartment> 
{
	public CRMDepartment mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMDepartmentExtractor departmentExtractor = new CRMDepartmentExtractor();		
		return departmentExtractor.extractData(resultSet);
	}

}
