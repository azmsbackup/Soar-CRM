package com.soarcrm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.soarcrm.domain.CRMConference;
import com.soarcrm.domain.CRMDepartment;
import com.soarcrm.jdbc.CRMConferenceRowMapper;
import com.soarcrm.util.AllzoneCRMUtil;

public class CRMConferenceDaoImpl implements CRMConferenceDao
{
	@Autowired
	DataSource dataSource;

	public void insertEvent(CRMConference conference) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");			
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		
		String sql = "INSERT INTO azc_events "
		    + "(event_id, Event_Code, Event_Name, Event_month, Description, Dept_Id,  Created_By, Created_Dt) "
		    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


		 jdbcTemplate.update(sql, new Object[] { conference.getEventId(), conference.getEventCode(), conference.getEventName(), conference.getEventMonth(),  
				conference.getDescription(), conference.getDeptId(), conference.getLoginName(), currentDate});
	}

	public List<CRMDepartment> getDepartmentId() throws Exception 
	{
		List<CRMDepartment> deptidlist = new ArrayList();
		
			String sql = "SELECT dept_id, Dept_Name from  azc_department where Status='A'";

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

			for (Map<String, Object> row : rows) 
			{
				CRMDepartment dept = new CRMDepartment();
				String deptid = row.get("dept_id").toString();
				String deptname = row.get("Dept_Name").toString();
				dept.setDepartmentId(deptid);
				dept.setDepartmentName(deptname);

				deptidlist.add(dept);
			}
		
		return deptidlist;
	}

	public List<CRMConference> getEventList() throws Exception 
	{
		List eventlist = new ArrayList();
		
		String sql = "select * from azc_events where Event_Name != 'ALL' ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		eventlist = jdbcTemplate.query(sql, new CRMConferenceRowMapper());

		return eventlist;
	}

	public CRMConference getEventDetails(String id) throws Exception 
	{
		List<CRMConference> eventlist = new ArrayList<CRMConference>();		
		
		String sql = "select * from azc_events where event_id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		eventlist = jdbcTemplate.query(sql, new CRMConferenceRowMapper());
	
		return eventlist.get(0);
		
	}

	public void updateEvent(CRMConference conference) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");			
		
		String sql = "UPDATE azc_events set  Event_Code = ?, Event_Name = ?, Event_month=?, Description=?, Dept_Id=?, "
				+" Modified_By = ?, Modified_Dt = ? where event_id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update( sql, new Object[] {conference.getEventCode(), conference.getEventName(), conference.getEventMonth(), conference.getDescription(),
				conference.getDeptId(),	conference.getLoginName(), currentDate, conference.getEventId()});
	}

}
