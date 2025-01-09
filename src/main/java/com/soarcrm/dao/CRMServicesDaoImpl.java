package com.soarcrm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.soarcrm.domain.CRMServices;
import com.soarcrm.domain.CRMSource;
import com.soarcrm.domain.CRMStatus;
import com.soarcrm.jdbc.CRMServicesRowMapper;
import com.soarcrm.jdbc.CRMSourceRowMapper;
import com.soarcrm.jdbc.CRMStatusRowMapper;
import com.soarcrm.util.AllzoneCRMUtil;

public class CRMServicesDaoImpl implements CRMServicesDao  
{
	@Autowired
	DataSource dataSource;
	@Override
	public List<CRMServices> getServicesList() throws Exception 
	{
		List<CRMServices> sourcelist = new ArrayList<CRMServices>();
		
		String sql = "select * from azc_services where services_desc != 'All' ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		sourcelist = jdbcTemplate.query(sql, new CRMServicesRowMapper());

		return sourcelist;
	}
	@Override
	public void insertServices(CRMServices crmServices) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		
		String sql = "INSERT INTO azc_services "
		    + "(services_desc, created_by, created_dt, modified_by, modified_dt) "
		    + "VALUES (?, ?, ?, ?, ?)";


		 jdbcTemplate.update(sql, new Object[] {  crmServices.getServicesDescription(),   
				 crmServices.getLoginName(), currentDate,  crmServices.getLoginName(), currentDate});
		
	}
	@Override
	public CRMServices getServices(String id) throws Exception 
	{
		List<CRMServices> servicesList = new ArrayList<CRMServices>();		
		
		String sql = "select * from azc_services where services_id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		servicesList = jdbcTemplate.query(sql, new CRMServicesRowMapper());
	
		return servicesList.get(0);
	}
	@Override
	public void updateServices(CRMServices crmServices) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		
		String sql = "UPDATE azc_services set  services_desc = ?,  modified_by = ?, modified_dt = ? where services_id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update( sql, new Object[] {crmServices.getServicesDescription(),  crmServices.getLoginName(), currentDate, 
				crmServices.getServicesId()});
		
	}
	@Override
	public CRMServices checkServicesDescription(CRMServices crmServices) throws Exception 
	{		
		List<CRMServices> servicesList = new ArrayList<CRMServices>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from azc_services  where services_desc = ?";

		servicesList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, crmServices.getServicesDescription());
			}
		}, new CRMServicesRowMapper());
		
		if (servicesList != null && servicesList.size() > 0) 
		{
			crmServices.setFlag(1);
			return servicesList.get(0);
			
		} 
		else 
		{
			return null;
		}
	}
	@Override
	public CRMServices checkEditServicesDescription(CRMServices crmServices) throws Exception 
	{
		List<CRMServices> servicesList = new ArrayList<CRMServices>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from azc_source  where Source_Desc = ? and source_id != ?";

		servicesList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, crmServices.getServicesDescription());
				ps.setString(2, crmServices.getServicesId());
			}
		}, new CRMServicesRowMapper());
		
		if (servicesList != null && servicesList.size() > 0) 
		{
			crmServices.setFlag(1);
			return servicesList.get(0);
		} 
		else 
		{
			return null;
		}
	}

}
