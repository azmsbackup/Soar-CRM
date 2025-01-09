package com.soarcrm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.soarcrm.domain.CRMSource;
import com.soarcrm.jdbc.CRMSourceRowMapper;
import com.soarcrm.util.AllzoneCRMUtil;

public class CRMSourceDaoImpl implements CRMSourceDao 
{
	@Autowired
	DataSource dataSource;
	
	public void insertSource(CRMSource source) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		String status="A";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		
		String sql = "INSERT INTO azc_source "
		    + "(source_id, Source_Desc, Status, Created_By, Created_Dt) "
		    + "VALUES (?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, new Object[] { source.getSourceId(), source.getSourceDescription(), status,   
				 source.getLoginName(), currentDate});
		
	}

	public List<CRMSource> getSourceList() throws Exception 
	{
		List<CRMSource> sourcelist = new ArrayList<CRMSource>();
		
		String sql = "select * from azc_source where Source_Desc != 'All' ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		sourcelist = jdbcTemplate.query(sql, new CRMSourceRowMapper());

		return sourcelist;
	}

	public CRMSource getSource(String id) throws Exception 
	{
		List<CRMSource> statuslist = new ArrayList<CRMSource>();		
		
		String sql = "select * from azc_source where source_id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		statuslist = jdbcTemplate.query(sql, new CRMSourceRowMapper());
	
		return statuslist.get(0);
	}

	public void updateSource(CRMSource crmsource) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		String status="A";
		
		String sql = "UPDATE azc_source set  Source_Desc = ?, Status = ?,  Modified_By = ?, Modified_Dt = ? where source_id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update( sql, new Object[] {crmsource.getSourceDescription(), status, crmsource.getLoginName(), currentDate, 
				crmsource.getSourceId()});
		
	}

	public CRMSource checkSourceDescription(CRMSource source) throws Exception 
	{
		String sourcename = source.getSourceDescription();
		
		List<CRMSource> sourceList = new ArrayList<CRMSource>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from azc_source  where Source_Desc = ?";

		sourceList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, sourcename);
			}
		}, new CRMSourceRowMapper());
		
		if (sourceList != null && sourceList.size() > 0) 
		{
			source.setFlag(1);
			return sourceList.get(0);
			
		} 
		else 
		{
			return null;
		}
			

	}

	public CRMSource checkEditSourceDescription(CRMSource crmsource) throws Exception 
	{		
		List<CRMSource> sourceList = new ArrayList<CRMSource>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from azc_source  where Source_Desc = ? and source_id != ?";

		sourceList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, crmsource.getSourceDescription());
				ps.setString(2, crmsource.getSourceId());
			}
		}, new CRMSourceRowMapper());
		
		if (sourceList != null && sourceList.size() > 0) 
		{
			crmsource.setFlag(1);
			return sourceList.get(0);
		} 
		else 
		{
			return null;
		}
	}

	public void inactiveSource(CRMSource crmsource) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sqlselect = "SELECT Status FROM azc_source where source_id='"+crmsource.getSourceId()+"' ";
			
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlselect);
		String status="";
		for (Map<String, Object> row : rows) 
		{
			status = row.get("Status").toString();
		}
			
		if(status.equals("A"))
		{
			status = "I";
		}
		else
		{
			status = "A";
		}
		
		String sql = "UPDATE azc_source set Status=?, "
			+" Modified_By = ?, Modified_Dt = ? where source_id = ? ";

		jdbcTemplate.update( sql, new Object[] {status, crmsource.getLoginName(), currentDate, crmsource.getSourceId()});
		
	}

	public List<CRMSource> getSourceListforevent() throws Exception 
	{
		List<CRMSource> sourcelist = new ArrayList<CRMSource>();
		
		String sql = "select * from azc_source where source_id in (2,4) ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		sourcelist = jdbcTemplate.query(sql, new CRMSourceRowMapper());

		return sourcelist;
	}

	@Override
	public List<CRMSource> getActiveSourceList() throws Exception {
        List<CRMSource> sourcelist = new ArrayList<CRMSource>();
		
		String sql = "select * from azc_source where Status = 'A' ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		sourcelist = jdbcTemplate.query(sql, new CRMSourceRowMapper());

		return sourcelist;
	}

}
