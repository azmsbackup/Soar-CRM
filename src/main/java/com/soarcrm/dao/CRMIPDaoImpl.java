package com.soarcrm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.soarcrm.domain.CRMIP;
import com.soarcrm.jdbc.CRMIPRowMapper;
import com.soarcrm.util.AllzoneCRMUtil;

public class CRMIPDaoImpl implements CRMIPDao 
{
	@Autowired
	DataSource dataSource;

	public void insertIP(CRMIP crmIPNo) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		String Status="A";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		
		String sql = "INSERT INTO azc_ip "
		    + "(ip_id, IP, Status, Created_By, Created_Dt) "
		    + "VALUES (?, ?, ?, ?, ?)";

		 jdbcTemplate.update(sql, new Object[] { crmIPNo.getIpId(), crmIPNo.getIP(), Status,   
				 crmIPNo.getLoginName(), currentDate});	
	}

	public List<CRMIP> getIPList() throws Exception 
	{
		List<CRMIP> IPlist = new ArrayList<CRMIP>();
		
		String sql = "select * from azc_ip where Status = 'A' ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		IPlist = jdbcTemplate.query(sql, new CRMIPRowMapper());

		return IPlist;
	}

	public CRMIP getIP(String id) throws Exception 
	{
		List<CRMIP> iplist = new ArrayList<CRMIP>();		
		
		String sql = "select * from azc_ip where ip_id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		iplist = jdbcTemplate.query(sql, new CRMIPRowMapper());
	
		return iplist.get(0);
	}

	public void updateIP(CRMIP crmIPNo) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		String Status="A";
		
		String sql = "UPDATE azc_ip set  IP = ?, Status = ?,  Modified_By = ?, Modified_Dt = ? where ip_id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update( sql, new Object[] {crmIPNo.getIP(), Status, crmIPNo.getLoginName(), currentDate, 
				crmIPNo.getIpId()});
		
	}

	public CRMIP checkIPNo(CRMIP crmIPNo) throws Exception 
	{
		String IPNo = crmIPNo.getIP();
		
		List<CRMIP> IPList = new ArrayList<CRMIP>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from azc_ip  where IP = ?";

		IPList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, IPNo);
			}
		}, new CRMIPRowMapper());
		
		if (IPList != null && IPList.size() > 0) 
		{
			crmIPNo.setFlag(1);
			return IPList.get(0);
			
		} 
		else 
		{
			return null;
		}
	}

	public CRMIP checkEditIPNo(CRMIP crmIP) throws Exception 
	{		
		List<CRMIP> IPList = new ArrayList<CRMIP>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from azc_ip  where IP = ? and ip_id != ?";
		IPList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, crmIP.getIP());
				ps.setString(2, crmIP.getIpId());
			}
		}, new CRMIPRowMapper());
		
		if (IPList != null && IPList.size() > 0) 
		{
			crmIP.setFlag(1);
			return IPList.get(0);
		} 
		else 
		{
			return null;
		}
	}

	public void deleteIP(String id) throws Exception 
	{
		  String sql = "delete from azc_ip where ip_id=" + id;
		  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		  jdbcTemplate.update(sql);
	}

	public void inactiveIp(CRMIP crmIPNo) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		 String sqlselect = "SELECT Status FROM azc_ip where ip_id='"+crmIPNo.getIpId()+"' ";
			
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
		
		String sql = "UPDATE azc_ip set Status=?, "
			+" Modified_By = ?, Modified_Dt = ? where ip_id = ? ";

		jdbcTemplate.update( sql, new Object[] {status, crmIPNo.getLoginName(), currentDate, crmIPNo.getIpId()});
		
	}

	public List<CRMIP> getIPListInactive() throws Exception 
	{
		List<CRMIP> IPlist = new ArrayList<CRMIP>();
		
		String sql = "select * from azc_ip ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		IPlist = jdbcTemplate.query(sql, new CRMIPRowMapper());

		return IPlist;
	}

}
