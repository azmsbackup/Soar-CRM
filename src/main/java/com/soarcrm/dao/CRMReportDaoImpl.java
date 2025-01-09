 package com.soarcrm.dao;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.soarcrm.domain.CRMStatus;
import com.soarcrm.domain.Client;
import com.soarcrm.jdbc.CRMClientRowMapper;
import com.soarcrm.jdbc.CRMReportRowMapper;
import com.soarcrm.util.AllzoneCRMConstants;
import com.soarcrm.util.AllzoneCRMUtil;

public class CRMReportDaoImpl implements CRMReportDao
{
	@Autowired
	DataSource dataSource;
	
	public List<Client> getstatusReportList(Client client) throws Exception 
	{
		String sql="";
		List<Client> StatusReportList = new ArrayList<Client>();
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 sql ="SELECT azc_client.*,Notes,NotesDt,azc_countries.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
				"azc_status.Status_Desc,azc_buckets.bucket_name, azc_user_master.First_Name, azc_events.Event_Name, azc_services.services_desc FROM azc_client left JOIN azc_countries " + 
				"ON azc_client.countries_id=azc_countries.id  left join azc_department on azc_client.Dept_Id = azc_department.dept_id" + 
				" left join azc_source on azc_client.Source_Id = azc_source.source_id left join azc_status on azc_client.Status_Id = azc_status.status_id"
				+ " left join azc_buckets on azc_client.bucket_id = azc_buckets.bucket_id" + 
				" left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_client.event_id = azc_events.event_id" +
				" left join azc_services on azc_client.services_id = azc_services.services_id where ";
		
		if (sql.endsWith("where ")) 
		{
			if(client.getStatusId() != null && !client.getStatusId().equals("") )
			{
				sql=sql + " azc_client.status_id = '"+client.getStatusId()+"' ";
			}
		}
		else
		{
			if(client.getStatusId() != null && !client.getStatusId().equals("") )
			{
				 sql=sql + " and azc_client.status_id ='"+client.getStatusId()+"' ";

			}
		}
		
		if (sql.endsWith("where ")) 
		{
			if(client.getBucketId() != null && !client.getBucketId().equals("") )
			{
				sql=sql + " azc_client.bucket_id = '"+client.getBucketId()+"' ";
			}
		}
		else
		{
			if(client.getBucketId() != null && !client.getBucketId().equals("") )
			{
				 sql=sql + " and azc_client.bucket_id ='"+client.getBucketId()+"' ";

			}
		}
		
		
		if (sql.endsWith("where ")) 
		{
			if(client.getRoleid() != null && client.getRoleid().equals("4") )
			{
				sql=sql + " azc_client.User_id ='"+client.getUserId()+"' ";
			}
		}
		else
		{
			if(client.getRoleid() != null &&  client.getRoleid().equals("4"))
			{
				 sql=sql + " and azc_client.User_id ='"+client.getUserId()+"' ";

			}
		}
		
		
		
		
		/*if (sql.endsWith("where ")) 
		{
			if(client.getEventId() != null && !client.getEventId().equals("") )
			{
				sql=sql + " azc_client.event_id='"+client.getEventId()+"' ";
			}
		}
		else
		{
			if(client.getEventId() != null && !client.getEventId().equals("") )
			{
				 sql=sql + " and azc_client.event_id='"+client.getEventId()+"' ";

			}
		}*/
		if (sql.endsWith("where ")) 
		{
			if(client.getCreatedDate() != null && !client.getCreatedDate().equals("")  && client.getModifiedDate() != null && !client.getModifiedDate().equals("")  )
			{
				Date fromdt = null;
				String fromddate = "";					
				fromdt = originalFormat.parse(client.getCreatedDate());
				fromddate = targetFormat.format(fromdt);
				
				Date todt = null;
				String todate = "";					
				todt = originalFormat.parse(client.getModifiedDate());
				todate = targetFormat.format(todt);
					
				 sql=sql + " azc_client.Modified_Dt between '"+fromddate+"' and '"+todate+"' ";
			}
		}
		else
		{
			if(client.getCreatedDate() != null && !client.getCreatedDate().equals("")  && client.getModifiedDate() != null && !client.getModifiedDate().equals("")  )
			{
				Date fromdt = null;
				String fromddate = "";					
				fromdt = originalFormat.parse(client.getCreatedDate());
				fromddate = targetFormat.format(fromdt);
				
				Date todt = null;
				String todate = "";					
				todt = originalFormat.parse(client.getModifiedDate());
				todate = targetFormat.format(todt);
			
				 sql=sql + " and azc_client.Modified_Dt between '"+fromddate+"' and '"+todate+"' ";
			}	
		}
		
		System.out.println("getstatusReportList :"+sql);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StatusReportList = jdbcTemplate.query(sql,new Object[] { }, new CRMReportRowMapper());

		return StatusReportList;
	}
	
	public List<Client> getsubStatusReportList(Client client) throws Exception 
	{
		String sql="";
		List<Client> subStatusReportList = new ArrayList<Client>();
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 sql ="SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
				"azc_status.Status_Desc,azc_buckets.bucket_name, azc_user_master.First_Name, azc_events.Event_Name, azc_services.services_desc  FROM azc_client left JOIN azc_countries " + 
				"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
				"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
				"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id "
				+ " left join azc_buckets on azc_buckets.bucket_id = azc_client.bucket_id " + 
				"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id " +
				"left join azc_services on azc_services.services_id = azc_client.services_id where ";
		
		if (sql.endsWith("where ")) 
		{
			if(client.getStatusId() != null && !client.getStatusId().equals("") )
			{
				sql=sql + " azc_client.status_id = '"+client.getStatusId()+"' ";
				sql = sql + "and azc_client.bucket_id ='"+client.getBucketId()+"' ";
			}
		}
		else
		{
			if(client.getStatusId() != null && !client.getStatusId().equals("") )
			{
				 sql=sql + " and azc_client.status_id ='"+client.getStatusId()+"' ";
				 sql = sql + "and azc_client.bucket_id ='"+client.getBucketId()+"' ";
			}
		}
		
		
		if (sql.endsWith("where ")) 
		{
			if(client.getRoleid() != null && client.getRoleid().equals("4") )
			{
				sql=sql + " azc_client.User_id ='"+client.getUserId()+"' ";
			}
		}
		else
		{
			if(client.getRoleid() != null &&  client.getRoleid().equals("4"))
			{
				 sql=sql + " and azc_client.User_id ='"+client.getUserId()+"' ";

			}
		}
		
		
		
		
		/*if (sql.endsWith("where ")) 
		{
			if(client.getEventId() != null && !client.getEventId().equals("") )
			{
				sql=sql + " azc_client.event_id='"+client.getEventId()+"' ";
			}
		}
		else
		{
			if(client.getEventId() != null && !client.getEventId().equals("") )
			{
				 sql=sql + " and azc_client.event_id='"+client.getEventId()+"' ";

			}
		}*/
		if (sql.endsWith("where ")) 
		{
			if(client.getCreatedDate() != null && !client.getCreatedDate().equals("")  && client.getModifiedDate() != null && !client.getModifiedDate().equals("")  )
			{
				Date fromdt = null;
				String fromddate = "";					
				fromdt = originalFormat.parse(client.getCreatedDate());
				fromddate = targetFormat.format(fromdt);
				
				Date todt = null;
				String todate = "";					
				todt = originalFormat.parse(client.getModifiedDate());
				todate = targetFormat.format(todt);
					
				 sql=sql + " azc_client.Modified_Dt between '"+fromddate+"' and '"+todate+"' ";
			}
		}
		else
		{
			if(client.getCreatedDate() != null && !client.getCreatedDate().equals("")  && client.getModifiedDate() != null && !client.getModifiedDate().equals("")  )
			{
				Date fromdt = null;
				String fromddate = "";					
				fromdt = originalFormat.parse(client.getCreatedDate());
				fromddate = targetFormat.format(fromdt);
				
				Date todt = null;
				String todate = "";					
				todt = originalFormat.parse(client.getModifiedDate());
				todate = targetFormat.format(todt);
			
				 sql=sql + " and azc_client.Modified_Dt between '"+fromddate+"' and '"+todate+"' ";
			}	
		}
		
		System.out.println("subStatusReportList :"+sql);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		subStatusReportList = jdbcTemplate.query(sql,new Object[] { }, new CRMReportRowMapper());

		return subStatusReportList;
	}

	public List<Client> getfollowupReportList(Client client) throws Exception 
	{
		List<Client> followupReportList = new ArrayList<Client>();
		
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		
		Date fromdt = null;
		String fromddate = "";
		Date todt = null;
		String todate = "";	
		
		if(client.getCreatedDate() != null && !client.getCreatedDate().equals(""))
		{
			
			if(client.getCreatedDate() != null && !client.getCreatedDate().equals(""))
			{
			fromdt = originalFormat.parse(client.getCreatedDate());
			fromddate = targetFormat.format(fromdt);
			}
			
			
			if(client.getModifiedDate() != null && !client.getModifiedDate().equals(""))
			{
				todt = originalFormat.parse(client.getModifiedDate());
				todate = targetFormat.format(todt);
			}
		}
			
			String sql="SELECT azc_client.*,azc_countries.name as country , azc_client.State as state, azc_client.City as city ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name, azc_notes.Notes_Dt, Appointment_With, Followup_Dt, (Select azc_notes.Notes " + 
					"from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by " + 
					"Notes_Id desc limit 1) as notes from azc_client left JOIN azc_countries " + 
					"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id " + 
					"left join azc_notes on azc_notes.traceNo = azc_client.TraceNo " +
					"where azc_client.Status_Id ='"+3+"' and azc_notes.Followup_Dt between '"+fromddate+"' and '"+todate+"'";
			
			
			
		
		
			if(client.getRoleid() != null &&  client.getRoleid().equals("4"))
			{
				 sql=sql + " and azc_client.User_id ='"+client.getUserId()+"' ";

			}
			
			
			
			sql = sql + "order by azc_client.traceno desc limit 500";
		
		//System.out.println("getfollowupReportList :"+sql);
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) 
		{
			Client clien = new Client();
			
			if(row.get("TraceNo") != null && !row.get("TraceNo").equals(""))
			{
				 clien.setTraceNo(row.get("TraceNo").toString());
			}
			if(row.get("Client_Name") != null && !row.get("Client_Name").equals(""))
			{
				 clien.setClientName(row.get("Client_Name").toString());
			}
			if(row.get("country") != null && !row.get("country").equals(""))
			{
				clien.setCountryName(row.get("country").toString());
			}
			else
			{
				clien.setCountryName("-");
			}
			if(row.get("state") != null && !row.get("state").equals(""))
			{
				clien.setStateName(row.get("state").toString());
			}
			else
			{
				clien.setStateName("-");
			}
			if(row.get("city") != null && !row.get("city").equals(""))
			{
				clien.setCityName(row.get("city").toString());
			}
			else
			{
				clien.setCityName("-");
			}
			if(row.get("Dept_Name") != null && !row.get("Dept_Name").equals(""))
			{
				clien.setDepartmentName(row.get("Dept_Name").toString());
			}
			if(row.get("Notes_Dt") != null && !row.get("Notes_Dt").equals(""))
			{
				Date notesdt = null;
				String notesDate = "";
				if(row.get("Notes_Dt").toString() != null && !row.get("Notes_Dt").toString().equals(""))
				{
					notesdt = targetFormat.parse(row.get("Notes_Dt").toString());
					notesDate = originalFormat.format(notesdt);
				}
				
				clien.setNotesDate(notesDate);
			}
			if(row.get("notes") != null && !row.get("notes").equals(""))
			{
				clien.setNotes(row.get("notes").toString());
			}
			if(row.get("Followup_Dt") != null && !row.get("Followup_Dt").equals(""))
			{
				Date followupdt = null;
				String followupDate = "";
				if(row.get("Followup_Dt").toString() != null && !row.get("Followup_Dt").toString().equals(""))
				{
					followupdt = targetFormat.parse(row.get("Followup_Dt").toString());
					followupDate = originalFormat.format(followupdt);
				}
				clien.setFollowUpDate(followupDate);
			}
			if(row.get("Event_Name") != null && !row.get("Event_Name").equals(""))
			{
				clien.setEventName(row.get("Event_Name").toString());
			}
			else
			{
				clien.setEventName("-");
			}
			
			if(row.get("Appointment_With") != null && !row.get("Appointment_With").equals(""))
			{
				clien.setFollowupWith(row.get("Appointment_With").toString());
			}
			else
			{
				clien.setFollowupWith("-");
			}
			
			if(row.get("First_Name") != null && !row.get("First_Name").equals(""))
			{
				clien.setUsername(row.get("First_Name").toString());
			}
			else
			{
				clien.setUsername("-");
			}

			followupReportList.add(clien);
		}
		return followupReportList;
	}
	
	public LinkedHashMap<String, String> getdeptHashMap(CRMStatus crmstatus) throws Exception 
	{
		LinkedHashMap<String, String> statusHashmap = new LinkedHashMap<>();
		String sql = "Select Status_Desc from azc_status where status = 'A'";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) 
		{
			String statusdesc = row.get("Status_Desc").toString();
			statusHashmap.put(statusdesc, "0");
		}
		//deptHashmap.put("allstatus", statusHashmap);
		return statusHashmap;
	}

	/*public LinkedHashMap<String, LinkedHashMap<String, String>> getsummaryReportList(CRMStatus status) throws Exception 
	{
		LinkedHashMap<String, LinkedHashMap<String, String>> servicesHashmap = new LinkedHashMap<>();
	
		LinkedHashMap<String, String> statusHashmap = new LinkedHashMap<>();
	
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	
		Date fromdt = null;
		String fromddate = "";
		if(status.getCreatedDate() != null && !status.getCreatedDate().equals(""))
		{
		fromdt = originalFormat.parse(status.getCreatedDate());
		fromddate = targetFormat.format(fromdt);
		}
	
		Date todt = null;
		String todate = "";
		if(status.getModifiedDate() != null && !status.getModifiedDate().equals(""))
		{
		todt = originalFormat.parse(status.getModifiedDate());
		todate = targetFormat.format(todt);
		}

		String sql = "Select Status_Desc from azc_status where status = 'A'";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	
	
		//deptHashmap.put("allstatus", statusHashmap);
	
		String currentService = "";
		String previouService = "";
		String statusname ="";
		String count="";
	
	
		String sql1 = "SELECT azc_client.services_id, azc_client.status_id, count(azc_client.status_id) as count , azc_services.services_desc , azc_status.Status_Desc " +
		"FROM azc_client left join azc_services on azc_services.services_id = azc_client.services_id " +
		"left join azc_status on azc_status.status_id = azc_client.status_id " +
		"where azc_client.Modified_Dt between '"+fromddate+"' and '"+todate+"' and azc_client.services_id is not null " +
		" and azc_status.status = 'A' group by azc_client.services_id, azc_client.status_id order by azc_client.services_id ";
		
		System.out.println("sql1 is "+ sql1);
		
		String sql1 = "SELECT azc_client.User_id, azc_client.status_id, "
				+ "count(azc_client.status_id) as count , azc_user_master.First_Name , "
				+ "azc_user_master.Last_Name,azc_status.Status_Desc FROM azc_client "
				+ "left join azc_user_master on azc_user_master.User_id = azc_client.User_id "
				+ "left join azc_status on azc_status.status_id = azc_client.status_id "
				+ "where azc_client.Modified_Dt between '"+fromddate+"' and '"+todate+"' and "
				+ "azc_client.User_id is not null  and azc_status.status = 'A' "
				+ "group by azc_client.User_id, azc_client.status_id "
				+ "order by azc_client.User_id";
	
		List<Map<String, Object>> row1 = jdbcTemplate.queryForList(sql1);
		LinkedHashMap<String, String> deptservicesHashmap = new LinkedHashMap<>();
		
		int firstrow = 0;
		for (Map<String, Object> row : row1)
		{
			currentService = row.get("services_desc").toString();	
			statusname = row.get("Status_Desc").toString();
			count = row.get("count").toString();		
	
			if (currentService != null && !currentService.equals(previouService))
			{
				//System.out.println("firstrow in if is "+ firstrow);
				//if(firstrow != 0)
				//{
					servicesHashmap.put(previouService, deptservicesHashmap);
				//}
				deptservicesHashmap = statusHashmap;
				//System.out.println("statusname in if is "+ statusname);
				//System.out.println("count in if is "+ count);
			
				List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
				deptservicesHashmap = new LinkedHashMap<>();
			
				for (Map<String, Object> row2 : rows)
				{
					String statusdesc = row2.get("Status_Desc").toString();
			
					deptservicesHashmap.put(statusdesc, "0");
				}
		
				deptservicesHashmap.put(statusname, count);
		
			}
			else
			{
				//System.out.println("statusname in else is "+ statusname);
				//System.out.println("count in else is "+ count);
				deptservicesHashmap.put(statusname, count);
				
			}
			previouService = currentService;
	
			firstrow++;
		}
		
		if(servicesHashmap.size() > 0)
		{
			servicesHashmap.put(currentService, deptservicesHashmap);
		}
	
		return servicesHashmap;
	}*/
	
	public LinkedHashMap<String, LinkedHashMap<String, String>> getsummaryReportList(CRMStatus status) throws Exception 
	{
		LinkedHashMap<String, LinkedHashMap<String, String>> servicesHashmap = new LinkedHashMap<>();
	
		LinkedHashMap<String, String> statusHashmap = new LinkedHashMap<>();
	
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	
		Date fromdt = null;
		String fromddate = "";
		if(status.getCreatedDate() != null && !status.getCreatedDate().equals(""))
		{
		fromdt = originalFormat.parse(status.getCreatedDate());
		fromddate = targetFormat.format(fromdt);
		}
	
		Date todt = null;
		String todate = "";
		if(status.getModifiedDate() != null && !status.getModifiedDate().equals(""))
		{
		todt = originalFormat.parse(status.getModifiedDate());
		todate = targetFormat.format(todt);
		}

		String sql = "Select Status_Desc from azc_status where status = 'A'";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	
	
		//deptHashmap.put("allstatus", statusHashmap);
	
		//String currentService = "";
		//String previouService = "";
		
		String currentUser = "";
		String previousUser = "";
		
		String statusname ="";
		String count="";
	
	
		String sql1 = "SELECT azc_client.User_id, azc_client.status_id, "
				+ "count(azc_client.status_id) as count , azc_user_master.First_Name , "
				+ "azc_user_master.Last_Name, "
				+ "CONCAT(azc_user_master.First_Name, ' ', azc_user_master.Last_Name) AS username ,"
				+ "azc_status.Status_Desc FROM azc_client "
				+ "left join azc_user_master on azc_user_master.User_id = azc_client.User_id "
				+ "left join azc_status on azc_status.status_id = azc_client.status_id "
				+ "where azc_client.Modified_Dt between '"+fromddate+"' and '"+todate+"' and "
				+ "azc_client.User_id is not null  and azc_status.status = 'A' "
				+ "and azc_user_master.role_id in (3,4) "
				+ "group by azc_client.User_id, azc_client.status_id "
				+ "order by azc_client.User_id";
		
		//System.out.println("sql1 is "+ sql1);
		
	
		List<Map<String, Object>> row1 = jdbcTemplate.queryForList(sql1);
		LinkedHashMap<String, String> deptservicesHashmap = new LinkedHashMap<>();
		
		int firstrow = 0;
		for (Map<String, Object> row : row1)
		{
			currentUser = row.get("username").toString();	
			statusname = row.get("Status_Desc").toString();
			count = row.get("count").toString();		
	
			if (currentUser != null && !currentUser.equals(previousUser))
			{
				//System.out.println("firstrow in if is "+ firstrow);
				//if(firstrow != 0)
				//{
					servicesHashmap.put(previousUser, deptservicesHashmap);
				//}
				deptservicesHashmap = statusHashmap;
				//System.out.println("statusname in if is "+ statusname);
				//System.out.println("count in if is "+ count);
			
				List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
				deptservicesHashmap = new LinkedHashMap<>();
			
				for (Map<String, Object> row2 : rows)
				{
					String statusdesc = row2.get("Status_Desc").toString();
			
					deptservicesHashmap.put(statusdesc, "0");
				}
		
				deptservicesHashmap.put(statusname, count);
		
			}
			else
			{
				//System.out.println("statusname in else is "+ statusname);
				//System.out.println("count in else is "+ count);
				deptservicesHashmap.put(statusname, count);
				
			}
			previousUser = currentUser;
	
			firstrow++;
		}
		
		if(servicesHashmap.size() > 0)
		{
			servicesHashmap.put(currentUser, deptservicesHashmap);
		}
	
		return servicesHashmap;
	}


	
	private ArrayList<CRMStatus> setDepartmentList(CRMStatus status) {

		List<CRMStatus> departmentList = new ArrayList<CRMStatus>();

		CRMStatus crmStatus = new CRMStatus();
		
		crmStatus.setCount(status.getCount());
		crmStatus.setDepartmentName(status.getDepartmentName());
		crmStatus.setCrmstatus(status.getStatusName());
		
		departmentList.add(crmStatus);

		return (ArrayList<CRMStatus>) departmentList;
	}
	
	public List<Client> getsourceReportList(Client client) throws Exception 
	{
		List<Client> sourceReportList = new ArrayList<Client>();
		
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		
		Date fromdt = null;
		String fromddate = "";
		if(client.getCreatedDate() != null && !client.getCreatedDate().equals(""))
		{
		fromdt = originalFormat.parse(client.getCreatedDate());
		fromddate = targetFormat.format(fromdt);
		}
		
		Date todt = null;
		String todate = "";	
		if(client.getModifiedDate() != null && !client.getModifiedDate().equals(""))
		{
			todt = originalFormat.parse(client.getModifiedDate());
			todate = targetFormat.format(todt);
		}		
		
		String sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
				"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name, azc_services.services_desc, azc_buckets.bucket_name  FROM azc_client left JOIN azc_countries " + 
				"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
				"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
				"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
				"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id "
				+ "left join azc_services on azc_services.services_id = azc_client.services_id " +	
				" left join azc_buckets on azc_buckets.bucket_id = azc_client.bucket_id " +
				"where azc_client.Source_Id=? and azc_client.Modified_Dt between ? and ?  ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		sourceReportList = jdbcTemplate.query(sql,new Object[] {client.getSourceId(), 
				fromddate, todate}, new CRMReportRowMapper());

		return sourceReportList;
	}

	public List<Client> getswapReportList(Client client) throws Exception 
	{
		List<Client> swapReportList = new ArrayList<Client>();
		
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fromdt = null;
		String fromddate = "";
		if(client.getCreatedDate() != null && !client.getCreatedDate().equals(""))
		{
		fromdt = originalFormat.parse(client.getCreatedDate());
		fromddate = targetFormat.format(fromdt);
		}
		
		Date todt = null;
		String todate = "";	
		if(client.getModifiedDate() != null && !client.getModifiedDate().equals(""))
		{
			todt = originalFormat.parse(client.getModifiedDate());
			todate = targetFormat.format(todt);
		}	
		
		String sql = "SELECT azc_client_assignment.*, azc_client.Client_Name, a.First_Name as fromname ,b.First_Name as toname " + 
				"FROM  azc_client_assignment left JOIN azc_client on azc_client_assignment.TraceNo = azc_client.TraceNo left join  "
				+ "azc_user_master as a ON azc_client_assignment.from_user_id=a.User_id " + 
				"left JOIN  azc_user_master as b " + 
				"ON b.User_id=azc_client_assignment.to_user_id where Start_Dt between '"+fromddate+"' and '"+todate+"' " ;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) 
		{	
			Client clien = new Client();
			if(row.get("Client_Name") != null && !row.get("Client_Name").equals(""))
			{
				clien.setClientName(row.get("Client_Name").toString());
			}
			if(row.get("TraceNo") != null && !row.get("TraceNo").equals(""))
			{
				clien.setTraceNo(row.get("TraceNo").toString());
			}
			if(row.get("from_user_id") != null && !row.get("from_user_id").equals(""))
			{
				clien.setHiddenuserid(row.get("from_user_id").toString());
			}
			if(row.get("fromname") != null && !row.get("fromname").equals(""))
			{
				clien.setFromname(row.get("fromname").toString());
			}
			if(row.get("toname") != null && !row.get("toname").equals(""))
			{
				clien.setToname(row.get("toname").toString());
			}
			if(row.get("Start_Dt") != null && !row.get("Start_Dt").equals(""))
			{				
				Date startdt = null;
				String startdate = "";
				if(row.get("Start_Dt")  != null && !row.get("Start_Dt").equals(""))
				{					startdt = targetFormat.parse(row.get("Start_Dt").toString());					startdate = originalFormat.format(startdt);
				}
				clien.setFromDate(startdate);
			}
			swapReportList.add(clien);
		}

		return swapReportList;
		
	}

	public List<Client> getopenDealsList(String id, String roleid, String userid) throws Exception 
	{
		List<Client> openDealsList = new ArrayList<Client>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar currentdt = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     currentdt.add(Calendar.YEAR,-1);
	     String endDate = f.format(currentdt.getTime());
		
	/*	if(id.equals("3"))
		{
			id = "3'," + "'17";
		} */
		
		String sql="SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
				"(Select azc_notes.Followup_Dt from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by Notes_Id desc limit 1) as followupdate, " + 
					"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name, azc_buckets.bucket_name FROM azc_client left JOIN azc_countries " + 
					"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_buckets on azc_buckets.bucket_id = azc_client.bucket_id  left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id " + 

					"where azc_client.Status_Id in ('"+id+"') and azc_client.User_id = '"+userid+"' and azc_client.Modified_Dt BETWEEN '"+endDate+"' AND '"+currentDate+"' order by azc_client.traceno desc"; // limit 500 ";
			

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			for (Map<String, Object> row : rows) 
			{	
				Client clien = new Client();
				if(row.get("Client_Name") != null && !row.get("Client_Name").equals(""))
				{
					clien.setClientName(row.get("Client_Name").toString());
				}
				if(row.get("TraceNo") != null && !row.get("TraceNo").equals(""))
				{
					clien.setTraceNo(row.get("TraceNo").toString());
				}
				if(row.get("City") != null && !row.get("City").equals(""))
				{
					clien.setCityName(row.get("City").toString());
				}
				if(row.get("State") != null && !row.get("State").equals(""))
				{
					clien.setStateName(row.get("State").toString());
				}
				if(row.get("e_mail") != null && !row.get("e_mail").equals(""))
				{
					clien.setEmail(row.get("e_mail").toString());
				}
				if(row.get("phone_no") != null && !row.get("phone_no").equals(""))
				{
					clien.setPhoneNumber(row.get("phone_no").toString());
				}
				if(row.get("Dept_Name") != null && !row.get("Dept_Name").equals(""))
				{
					clien.setDepartmentName(row.get("Dept_Name").toString());
				}
				if(row.get("Status_Desc") != null && !row.get("Status_Desc").equals(""))
				{
					clien.setStatusName(row.get("Status_Desc").toString());
				}
				
				if(row.get("bucket_name") != null && !row.get("bucket_name").equals(""))
				{
					clien.setBucketName(row.get("bucket_name").toString());
				}
				
				if(row.get("First_Name") != null && !row.get("First_Name").equals(""))
				{
					clien.setUsername(row.get("First_Name").toString());
				}
				
				if(row.get("User_id") != null && !row.get("User_id").equals(""))
				{
					clien.setUserId(row.get("User_id").toString());
				}
				
				if(row.get("followupdate") != null && !row.get("followupdate").equals(""))
				{
					Date followupdt = null;
					String followupdate = "";
					if(row.get("followupdate") != null && !row.get("followupdate").equals(""))
					{
						followupdt = targetFormat.parse(row.get("followupdate").toString());
						followupdate = originalFormat.format(followupdt);
					}
					clien.setFollowUpDate(followupdate);
				}
				else
				{
					clien.setFollowUpDate("-");
				}
				
				if(row.get("Modified_Dt") != null && !row.get("Modified_Dt").equals(""))
				{
					Date modifiedDt = null;
					String formatedModifiedDt = "";
					if(row.get("Modified_Dt") != null && !row.get("Modified_Dt").equals(""))
					{
						modifiedDt = targetFormat.parse(row.get("Modified_Dt").toString());
						formatedModifiedDt = originalFormat.format(modifiedDt);
					}
					clien.setModifiedDate(formatedModifiedDt);
				}
				openDealsList.add(clien);
			}

		return openDealsList;
	}

	public List<Client> getoverdueDealsList(Client client) throws Exception 
	{
		List<Client> overdueDealsList = new ArrayList<Client>();
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		
		LocalDate date = LocalDate.now().minusDays(3);
		
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar currentdt = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     currentdt.add(Calendar.YEAR,-1);
	     String startDate = f.format(currentdt.getTime());
		
		String sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
				"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name, azc_services.services_desc  FROM azc_client left JOIN azc_countries " + 
				"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
				"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
				"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
				"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id "
				+ "left join azc_services on azc_services.services_id = azc_client.services_id " +
				"where azc_client.Status_Id ='"+6+"'and azc_client.Modified_Dt <='"+date.toString()+"' "
				+ "and azc_client.Modified_Dt BETWEEN '"+startDate+"' AND '"+currentDate+"' order by azc_client.traceno desc limit 500";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		overdueDealsList = jdbcTemplate.query(sql,new Object[] {}, new CRMReportRowMapper());

		return overdueDealsList;
	}

	public List<Client> getlogDeatils(Client client) throws Exception 
	{	
		List<Client> logList = new ArrayList<Client>();
		
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fromdt = null;
		String fromddate = "";
		if(client.getCreatedDate() != null && !client.getCreatedDate().equals(""))
		{
			fromdt = originalFormat.parse(client.getCreatedDate());
			fromddate = targetFormat.format(fromdt);
		}
		
		Date todt = null;
		String todate = "";	
		if(client.getModifiedDate() != null && !client.getModifiedDate().equals(""))
		{
			todt = originalFormat.parse(client.getModifiedDate());
			todate = targetFormat.format(todt);
		}
		
		String sql = "select azc_log.*, azc_user_master.First_Name from azc_log " + 
				"left join azc_user_master on azc_user_master.User_id = azc_log.User_Id " + 
				"where Upload_Dt between  '"+fromddate+"' and '"+todate+"' " ;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) 
		{	
			Client clien = new Client();
			if(row.get("First_Name") != null && !row.get("First_Name").equals(""))
			{
				clien.setUsername(row.get("First_Name").toString());
			}
			/*if(row.get("TraceNo") != null && !row.get("TraceNo").equals(""))
			{
				clien.setTraceNo(row.get("TraceNo").toString());
			}*/
			if(row.get("Description") != null && !row.get("Description").equals(""))
			{
				clien.setDescription(row.get("Description").toString());
			}
			if(row.get("Upload_Dt") != null && !row.get("Upload_Dt").equals(""))
			{				
				Date uploadeddt = null;
				String uploadeddate = "";
				if(row.get("Upload_Dt") != null && !row.get("Upload_Dt").equals(""))
				{
					uploadeddt = targetFormat.parse(row.get("Upload_Dt").toString());
					uploadeddate = originalFormat.format(uploadeddt);
				}
				clien.setCreatedDate(uploadeddate);
			}
			/*if(row.get("Client_Name") != null && !row.get("Client_Name").equals(""))
			{
				clien.setClientName(row.get("Client_Name").toString());
			}*/
			
			logList.add(clien);
		}
		return logList;
		
	}

	public List<Client> getoverdueList(String statusid , String userid) throws Exception 
	{
		List<Client> overdueFollowupList = new ArrayList<Client>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		LocalDate date = LocalDate.now().minusDays(3);
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar currentdt = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     currentdt.add(Calendar.YEAR,-1);
	     String startDate = f.format(currentdt.getTime());
		
		String sql ="";
		
			sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name, azc_services.services_desc,azc_buckets.bucket_name  FROM azc_client left JOIN azc_countries " + 
					"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id "
					+ "left join azc_services on azc_services.services_id = azc_client.services_id left join azc_buckets on azc_client.bucket_id = azc_buckets.bucket_id " + 
					"where azc_client.Status_Id ='"+statusid+"'and azc_client.Modified_Dt <'"+date.toString()+"' and azc_client.User_id = '"+userid+"'"
							+ "and azc_client.Modified_Dt BETWEEN '"+startDate+"' AND '"+currentDate+"'  ";
			
			if(statusid.equals("6"))
			{
				sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
						"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name, azc_services.services_desc,azc_buckets.bucket_name  FROM azc_client left JOIN azc_countries " + 
						"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
						"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
						"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
						"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id "
						+ "left join azc_services on azc_services.services_id = azc_client.services_id left join azc_buckets on azc_client.bucket_id = azc_buckets.bucket_id " + 
						"where azc_client.Status_Id ='"+statusid+"'"
								+ "and azc_client.Modified_Dt BETWEEN '"+startDate+"' AND '"+currentDate+"'  ";
				
				sql = sql + "and azc_client.e_mail != '' order by azc_client.traceno desc"; // limit 500" ;
			}
			else
			{
				sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
						"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name, azc_services.services_desc,azc_buckets.bucket_name  FROM azc_client left JOIN azc_countries " + 
						"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
						"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
						"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
						"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id "
						+ "left join azc_services on azc_services.services_id = azc_client.services_id left join azc_buckets on azc_client.bucket_id = azc_buckets.bucket_id " + 
						"where azc_client.Status_Id ='"+statusid+"'and azc_client.Modified_Dt <'"+date.toString()+"' and azc_client.User_id = '"+userid+"'"
								+ "and azc_client.Modified_Dt BETWEEN '"+startDate+"' AND '"+currentDate+"'  ";
				sql = sql + " order by azc_client.traceno desc"; // limit 500";
			}
		
//System.out.println("sql in getoverdueList for status "+ statusid + " is "+ sql);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		overdueFollowupList = jdbcTemplate.query(sql,new Object[] {}, new CRMReportRowMapper());

		return overdueFollowupList;
	}

	public List<Client> getDepartmentDeatils(Client client) throws Exception
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		List<Client> departmentList = new ArrayList<Client>();		
		String sql ="";
		if(client.getRoleid() != null && !client.getRoleid().equals("4"))
		{
			sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name, azc_services.services_desc,azc_buckets.bucket_name  FROM azc_client left JOIN azc_countries " + 
					"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id " +
					"left join azc_services on azc_services.services_id = azc_client.services_id left join azc_buckets on azc_buckets.bucket_id = azc_client.bucket_id  where";
		}
		else
		{
			sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name, azc_services.services_desc,azc_buckets.bucket_name  FROM azc_client left JOIN azc_countries " + 
					"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id " + 
					"left join azc_services on azc_services.services_id = azc_client.services_id left join azc_buckets on azc_buckets.bucket_id = azc_client.bucket_id  where User_id='"+client.getUserId()+"'   ";
		}
		if(client.getDeptId() != null && !client.getDeptId().equals(""))
		{
			
			if(sql.endsWith("where"))
			{
				sql = sql + " azc_client.Dept_Id='"+client.getDeptId()+"' ";
			}
			else
			{
				sql = sql + " and azc_client.Dept_Id='"+client.getDeptId()+"' ";
			}
			
		}
		if(client.getStatusId() != null && !client.getStatusId().equals(""))
		{
			if(sql.endsWith("where"))
			{
				sql = sql + " azc_client.status_id='"+client.getStatusId()+"' ";
			}
			else
			{
				sql = sql + " and azc_client.status_id='"+client.getStatusId()+"' ";
			}
		}
		if(client.getServicesId() != null && !client.getServicesId().equals(""))
		{
			if(sql.endsWith("where"))
			{
				sql = sql + " azc_client.services_id='"+client.getServicesId()+"' ";
			}
			else
			{
				sql = sql + " and azc_client.services_id='"+client.getServicesId()+"' ";
			}
		}	
		if((client.getStatusId() == null || client.getStatusId().equals("")) && (client.getDeptId() == null || client.getDeptId().equals("")))
		{
			if(sql.endsWith("where"))
			{
				sql = sql + " year(azc_client.Modified_Dt) ='"+year+"' ";
			}
			else
			{
				sql = sql + " and year(azc_client.Modified_Dt) ='"+year+"' ";
			}
		}
		if(client.getFromDate() != null && !client.getFromDate().equals("") && client.getToDate() != null && !client.getToDate().equals(""))
		{
			DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");			
			
			Date fromdt = null;
			String fromddate = "";
			if(client.getFromDate() != null && !client.getFromDate().equals(""))
			{
			fromdt = originalFormat.parse(client.getFromDate());
			fromddate = targetFormat.format(fromdt);
			}
			
			Date todt = null;
			String todate = "";	
			if(client.getToDate() != null && !client.getToDate().equals(""))
			{
				todt = originalFormat.parse(client.getToDate());
				todate = targetFormat.format(todt);
			}
			if(sql.endsWith("where"))
			{
				sql = sql + " azc_client.Modified_Dt between '"+fromddate+"' and '"+todate+"' ";
			}
			else
			{
				sql = sql + " and azc_client.Modified_Dt between '"+fromddate+"' and '"+todate+"'";
			}
			
		}
		//System.out.println("sql "+sql);
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		departmentList = jdbcTemplate.query(sql,new Object[] {}, new CRMReportRowMapper());

		return departmentList;
	}

	/*public List<CRMStatus> productivityTotal(CRMStatus crmstatus) throws Exception 
	{
		
		List ProductivitytotalList = new ArrayList();	
		
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");		
		String weekDate = AllzoneCRMUtil.getDate7DaysBefore("yyyy-MM-dd");		
		String monthDate = AllzoneCRMUtil.getmonthDatetime("yyyy-MM-dd");
		
		if(crmstatus.getCrmstatus() != null && crmstatus.getCrmstatus().equals("Data Collection"))
		  {
			  crmstatus.setStatusId("6");
		  }
		  if(crmstatus.getCrmstatus() != null && crmstatus.getCrmstatus().equals("Email Sent"))
		  {
			  crmstatus.setStatusId("4");
		  }
		  if(crmstatus.getCrmstatus() != null && crmstatus.getCrmstatus().equals("Calls"))
		  {
			  crmstatus.setStatusId("3");
		  }
		  if(crmstatus.getCrmstatus() != null && crmstatus.getCrmstatus().equals("Appt Fixed"))
		  {
			  crmstatus.setStatusId("7");
		  }
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		
		
		String actualdaily = "select count(*) from azc_client where User_id = ? and status_id=? and Created_Dt=? ";
		int actualdailycount = jdbcTemplate.queryForObject(actualdaily, new Object[] {crmstatus.getUserid(), crmstatus.getStatusId(), currentDate}, Integer.class);
		crmstatus.setOpendealsdaily(String.valueOf(actualdailycount));
		
		String actualweekly = "select count(*) from azc_client where User_id = ? and status_id=? and Created_Dt BETWEEN '"+weekDate+"' AND '"+currentDate+"' ";
		int actualweeklycount = jdbcTemplate.queryForObject(actualweekly, new Object[] {crmstatus.getUserid(),  crmstatus.getStatusId()}, Integer.class);
		crmstatus.setOpendealsweekly(String.valueOf(actualweeklycount));
		  
		String actualmonthly = "select count(*) from azc_client where User_id = ? and Status_Id=? and Created_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' ";
		int actualmonthlycount = jdbcTemplate.queryForObject(actualmonthly, new Object[] {crmstatus.getUserid(), crmstatus.getStatusId()}, Integer.class);
		crmstatus.setOpendealsmonthly(String.valueOf(actualmonthlycount));
		
		String targettoday = " select sum(Daily_Target) as todaytarget from azc_targets where User_id='"+crmstatus.getUserid()+"' and "
	  			+ "Activity_Desc = '"+crmstatus.getCrmstatus()+"' and Created_Dt='"+currentDate+"'";
		  List<Map<String, Object>> sqlrows = jdbcTemplate.queryForList(targettoday);
		  for (Map<String, Object> row1 : sqlrows) 
	      {
			  if(row1.get("todaytarget") != null && !row1.get("todaytarget").equals(""))
			  {
				  crmstatus.setTargetopendaily(row1.get("todaytarget").toString());
			  }
			  else
			  {
				  crmstatus.setTargetopendaily("0");
			  }
	      }
		  
		  String targetweekly = "select sum(Daily_Target) as weeklytarget from azc_targets where User_id='"+crmstatus.getUserid()+"' and "
  		  		+ "Activity_Desc = '"+crmstatus.getCrmstatus()+"' and Created_Dt BETWEEN '"+weekDate+"' AND '"+currentDate+"' ";
	    		  List<Map<String, Object>> rows11 = jdbcTemplate.queryForList(targetweekly);
	    		  for (Map<String, Object> row2 : rows11) 
	  	    	  {
	    			  if(row2.get("weeklytarget") != null && !row2.get("weeklytarget").equals(""))
	    			  {
	    				  crmstatus.setTargetopenweekly(row2.get("weeklytarget").toString());
	    			  }
	    			  else
	    			  {
	    				  crmstatus.setTargetopenweekly("0");
	    			  }
	    			 
	  	    	  }
	    		  
    		  String targetmonthly = " select sum(Daily_Target) as monthlytarget from azc_targets where User_id='"+crmstatus.getUserid()+"' and "
    		  		+ "Activity_Desc = '"+crmstatus.getCrmstatus()+"' and Created_Dt BETWEEN '"+monthDate+"' AND '"+currentDate+"' ";
    		  
    		  List<Map<String, Object>> rows12 = jdbcTemplate.queryForList(targetmonthly);
    		  for (Map<String, Object> rows3 : rows12) 
  	    	  {
    			  if(rows3.get("monthlytarget") != null && !rows3.get("monthlytarget").equals(""))
    			  {
    				  crmstatus.setTargetopenmonthly(rows3.get("monthlytarget").toString());
    			  }
    			  else
    			  {
    				  crmstatus.setTargetopenmonthly("0");
    			  }
    			 
  	    	 }
		
		ProductivitytotalList.add(crmstatus);
		
		return ProductivitytotalList;
	}*/

	public CRMStatus responseandLeads(CRMStatus crmstatus) throws Exception 
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		
		String response_year = "select count(*) from azc_client where Status_Id in('3','4', '6', '8') and year(Modified_Dt)='"+year+"' "
				+ "and User_id='"+crmstatus.getUserid()+"' ";		
		int year_count = jdbcTemplate.queryForObject(response_year, new Object[] {}, Integer.class);
		crmstatus.setResponse_year(String.valueOf(year_count));    			
		
		String response_month = "select count(*) from azc_client where Status_Id in('3','4', '6', '8') and year(Modified_Dt)='"+year+"' "
				+ "and month(Created_Dt)='"+month+"' and User_id='"+crmstatus.getUserid()+"'  ";		
		int month_count = jdbcTemplate.queryForObject(response_month, new Object[] {}, Integer.class);
		crmstatus.setResponse_month(String.valueOf(month_count));	
		
		String closed_year = "select count(*) from azc_client where Status_Id = ? and year(Modified_Dt)='"+year+"' and User_id='"+crmstatus.getUserid()+"'  ";		
		int closed_count = jdbcTemplate.queryForObject(closed_year, new Object[] {14}, Integer.class);
		crmstatus.setClosed_year(String.valueOf(closed_count));
		
		String closed_month = "select count(*) from azc_client where Status_Id = ? and year(Modified_Dt)='"+year+"' and month(Modified_Dt)='"+month+"' "
				+ "and User_id='"+crmstatus.getUserid()+"' ";		
		int month_closed = jdbcTemplate.queryForObject(closed_month, new Object[] {14}, Integer.class);
		crmstatus.setClosed_month(String.valueOf(month_closed));
		 
		return crmstatus;
	}

	public List<Client> getmanagerOpenDealsList(String statusid) throws Exception 
	{
		List<Client> openDealsList = new ArrayList<Client>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar currentdt = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     currentdt.add(Calendar.YEAR,-1);
	     String startDate = f.format(currentdt.getTime());
		
		/*if(statusid.equals("3"))
		{
			statusid = "3'," + "'17";
		}*/
		
		//System.out.println("statusid "+statusid);
		
		String sql="SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
				"(Select azc_notes.Followup_Dt from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by Notes_Id desc limit 1) as followupdate, " + 
					"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name,azc_buckets.bucket_name FROM azc_client left JOIN azc_countries " + 
					"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_buckets on azc_buckets.bucket_id = azc_client.bucket_id left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id " + 
					"where azc_client.Status_Id in ('"+statusid+"') and azc_client.Modified_Dt BETWEEN '"+startDate+"' and '"+currentDate+"' order by azc_client.traceno desc"; // limit 500 ";
			System.out.println("sql"+sql);
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			for (Map<String, Object> row : rows) 
			{	
				Client clien = new Client();
				if(row.get("Client_Name") != null && !row.get("Client_Name").equals(""))
				{
					clien.setClientName(row.get("Client_Name").toString());
				}
				if(row.get("TraceNo") != null && !row.get("TraceNo").equals(""))
				{
					clien.setTraceNo(row.get("TraceNo").toString());
				}
				if(row.get("City") != null && !row.get("City").equals(""))
				{
					clien.setCityName(row.get("City").toString());
				}
				if(row.get("State") != null && !row.get("State").equals(""))
				{
					clien.setStateName(row.get("State").toString());
				}
				if(row.get("e_mail") != null && !row.get("e_mail").equals(""))
				{
					clien.setEmail(row.get("e_mail").toString());
				}
				if(row.get("phone_no") != null && !row.get("phone_no").equals(""))
				{
					clien.setPhoneNumber(row.get("phone_no").toString());
				}
				if(row.get("Dept_Name") != null && !row.get("Dept_Name").equals(""))
				{
					clien.setDepartmentName(row.get("Dept_Name").toString());
				}
				if(row.get("Status_Desc") != null && !row.get("Status_Desc").equals(""))
				{
					clien.setStatusName(row.get("Status_Desc").toString());
				}
				
				if(row.get("bucket_name") != null && !row.get("bucket_name").equals(""))
				{
					clien.setBucketName(row.get("bucket_name").toString());
				}
				
				if(row.get("time_zone") != null && !row.get("time_zone").equals(""))
				{
					clien.setTimezone(row.get("time_zone").toString());
				}
				
				if(row.get("First_Name") != null && !row.get("First_Name").equals(""))
				{
					clien.setUsername(row.get("First_Name").toString());
				}
				
				if(row.get("Source_Desc") != null && !row.get("Source_Desc").equals(""))
				{
					clien.setSourceName(row.get("Source_Desc").toString());
				}
				
				if(row.get("User_id") != null && !row.get("User_id").equals(""))
				{
					clien.setUserId(row.get("User_id").toString());
				}
				
				if(row.get("followupdate") != null && !row.get("followupdate").equals(""))
				{
					Date followupdt = null;
					String followupdate = "";
					if(row.get("followupdate") != null && !row.get("followupdate").equals(""))
					{
						followupdt = targetFormat.parse(row.get("followupdate").toString());
						followupdate = originalFormat.format(followupdt);
					}
					clien.setFollowUpDate(followupdate);
				}
				else
				{
					clien.setFollowUpDate("-");
				}
				
				if(row.get("Modified_Dt") != null && !row.get("Modified_Dt").equals(""))
				{
					Date modifiedDt = null;
					String formatedModifiedDt = "";
					if(row.get("Modified_Dt") != null && !row.get("Modified_Dt").equals(""))
					{
						modifiedDt = targetFormat.parse(row.get("Modified_Dt").toString());
						formatedModifiedDt = originalFormat.format(modifiedDt);
					}
					clien.setModifiedDate(formatedModifiedDt);
				}
				
				//System.out.println("clien status name "+clien.getStatusName());
				openDealsList.add(clien);
			}

		return openDealsList;
	}

	public List<Client> getmanagerOverdueList(String statusid) throws Exception 
	{
		List<Client> overdueFollowupList = new ArrayList<Client>();	
		int year = Calendar.getInstance().get(Calendar.YEAR);
		LocalDate date = LocalDate.now().minusDays(3);
		//System.out.println("date"+date);
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar currentdt = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     currentdt.add(Calendar.YEAR,-1);
	     String startDate = f.format(currentdt.getTime());
		
		String sql ="";
		
			sql ="SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name,azc_services.services_desc,azc_buckets.bucket_name  FROM azc_client left JOIN azc_countries " + 
					"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id "
					+ "left join azc_services on azc_services.services_id = azc_client.services_id left join azc_buckets on azc_buckets.bucket_id = azc_client.bucket_id " + 
					"where azc_client.Status_Id ='"+statusid+"'and azc_client.Modified_Dt <'"+date.toString()+"' and azc_client.Modified_Dt BETWEEN '"+startDate+"' and '"+currentDate+"' ";
		
		if(statusid.equals("6"))
		{
			sql = sql + " and azc_client.e_mail != '' order by azc_client.traceno desc"; // desc limit 500 ";
			
			
		}
		else
		{
			sql = sql + " order by azc_client.traceno desc"; // limit 500 ";
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		overdueFollowupList = jdbcTemplate.query(sql,new Object[] {}, new CRMReportRowMapper());

		return overdueFollowupList;
	}
	
	public List<CRMStatus> getProductivityCount(CRMStatus crmstatus) throws Exception
	{
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fromdt = null;
		String fromddate = "";					
		fromdt = originalFormat.parse(crmstatus.getFromDate());
		fromddate = targetFormat.format(fromdt);
		
		Date todt = null;
		String todate = "";					
		todt = originalFormat.parse(crmstatus.getToDate());
		todate = targetFormat.format(todt);
		
		List<CRMStatus> productivityList= new ArrayList<CRMStatus>();	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		 /*String sql="select a.User_id, a.First_Name, a.Last_Name, "
		 		+ "count(if(b.status_id=6,1,null)) as opencount,"
		 		+ "count(if(b.status_id=4,1,null)) as emailcount, "
		 		+ "count(if(b.status_id in(3, 9, 14, 5, 10, 11, 18),1,null)) as followupcount "
		 		+ "from azc_user_master a left outer join azc_notes b on "
		 		+ "a.User_id = b.user_id and "
		 		+ "b.Modified_Dt between ? and ? group by a.User_id "
		 		+ "order by a.First_Name";*/
		 
		 String sql = "select role_id, qry.uid, qry.fname,qry.lname, qry.opencount, qry.emailcount,qry.followupcount,qry.partialcount    \r\n" + 
		 		"from  azc_user_master um,\r\n" + 
		 		"  ( select a.User_id uid, a.First_Name fname, a.Last_Name lname, a.role_id rid,\r\n" + 
		 		"count(if(b.status_id=6,1,null)) as opencount,\r\n" + 
		 		"count(if(b.status_id=4,1,null)) as emailcount,\r\n" + 
		 		"count(if(b.status_id in(3, 9, 14, 5, 10, 11, 18),1,null)) as followupcount,\r\n" + 
		 		"count(if(b.status_id in(32),1,null)) as partialcount\r\n" + 
		 		"from azc_user_master a left outer join azc_notes b on\r\n" + 
		 		"a.User_id = b.user_id and\r\n" + 
		 		"b.Notes_Dt between ? and ? group by a.User_id) qry\r\n" + 
		 		"where um.user_id = qry.uid\r\n" + 
		 		"and um.role_id in (3,4) and um.user_status = 'A' " + 
		 		"order by fname";
		 
		 //System.out.println("sql "+ sql);
		 
		 List<Map<String, Object>> row1 = jdbcTemplate.queryForList(sql, new Object[]{fromddate,todate});
		 for (Map<String, Object> row : row1)
		 {
			String userName = row.get("fname").toString() + " " + row.get("lname").toString();
			String opencount = row.get("opencount").toString();
			String emailcount = row.get("emailcount").toString();
			String followupcount = row.get("followupcount").toString();
			String partialcount = row.get("partialcount").toString();
			
			CRMStatus status = new CRMStatus();
			status.setUserName(userName);
			status.setOpendeals(opencount);
			status.setEmailsent(emailcount);
			status.setFollowup(followupcount);
			status.setPartialdata(partialcount);
			productivityList.add(status);
		 }
		 return productivityList;
	}
	
	public LinkedHashMap<String, List<CRMStatus>> getproductivityList(CRMStatus crmstatus) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");		
		String weekDate = AllzoneCRMUtil.getDate7DaysBefore("yyyy-MM-dd");		
		String monthDate = AllzoneCRMUtil.getmonthDatetime("yyyy-MM-dd");
		
		Calendar c = Calendar.getInstance(  ); 
	
		//int weeksOfYear = c.getActualMaximum(Calendar.WEEK_OF_YEAR);
		int year = c.get(Calendar.YEAR);
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		
		int numOfWeeksInMonth = (int)AllzoneCRMUtil.getNoofweekforMonth(month, year);
		
		long weeksforFirstMonth = AllzoneCRMUtil.getNoofweekforMonth(1, year);
		long weeksforSecondMonth = AllzoneCRMUtil.getNoofweekforMonth(2, year);
		long weeksforThirdMonth = AllzoneCRMUtil.getNoofweekforMonth(3, year);
		long weeksforFourthMonth = AllzoneCRMUtil.getNoofweekforMonth(4, year);
		long weeksforFifthMonth= AllzoneCRMUtil.getNoofweekforMonth(5, year);
		long weeksforSixthMonth = AllzoneCRMUtil.getNoofweekforMonth(6, year);
		long weeksforSeventhMonth = AllzoneCRMUtil.getNoofweekforMonth(7, year);
		long weeksforEighthMonth = AllzoneCRMUtil.getNoofweekforMonth(8, year);
		long weeksforNinethMonth = AllzoneCRMUtil.getNoofweekforMonth(9, year);
		long weeksforTenthMonth = AllzoneCRMUtil.getNoofweekforMonth(10, year);
		long weeksforEleventh = AllzoneCRMUtil.getNoofweekforMonth(11, year);
		long weeksforTwelvethMonth = AllzoneCRMUtil.getNoofweekforMonth(12, year);		
		
		
		if(crmstatus.getRoleid() != null && crmstatus.getRoleid().equals("4"))
		{
			crmstatus.setUserid(crmstatus.getUserId());
		}
		
		List<CRMStatus> productivityList= new ArrayList<CRMStatus>();		
		 LinkedHashMap<String, List<CRMStatus>> statusHashmap = new LinkedHashMap<String, List<CRMStatus>>();
		 
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		// try 
		 //   {
		    	String userStatus = "";
	            String previousStatus = "";
	            int i = 0;
	            String userId=crmstatus.getUserid(); 
	            CRMStatus istatus = new CRMStatus();
	            
		         statusHashmap.put("Data Collection", productivityList);
		         statusHashmap.put("Partial Data", productivityList);
		   		 statusHashmap.put("Email", productivityList);
		   		 statusHashmap.put("Follow-up(calls)", productivityList);
		   		 statusHashmap.put("New Leads(Response)", productivityList);
		   		 statusHashmap.put("Closed", productivityList);
		   		statusHashmap.put("Not Interested", productivityList);
		   		statusHashmap.put("Not Reachable", productivityList);
		   		statusHashmap.put("Do Not Call", productivityList);
		   		statusHashmap.put("Disqualified", productivityList);
		   		 
		   		 String sql="select status_id, Status_Desc from azc_status where status_id in "
		   				+ "("+ AllzoneCRMConstants.PRODUCTIVITY_STATUS +") order by Status_Desc";
		   		 		//+ "('3','4','5','6','9','10','11','18','14') order by Status_Desc";
		   		 
		   		 List<Map<String, Object>> row = jdbcTemplate.queryForList(sql);
		   		 
		   		 for (Map<String, Object> rows : row) 
		   		 {
		   			int janTarget = 0;
		   			int febTarget = 0;
		   			int marTarget = 0;
		   			int aprTarget = 0;
		   			int mayTarget = 0;
		   			int junTarget = 0;
		   			int julyTarget = 0;
		   			int augTarget = 0;
		   			int sepTarget = 0;
		   			int octTarget = 0;
		   			int novTarget = 0;
		   			int decTarget = 0;
		   			
		    		userStatus = rows.get("Status_Desc").toString();
		    		String strStatusId = rows.get("status_id").toString();
		    		crmstatus.setStatusId(strStatusId);
		    		
		    		if(userStatus != null && userStatus.equals("Open"))
		    		{
		    			userStatus = "Data Collection";
		    		}
		    		
		    		/*if(userStatus != null && userStatus.equals("Appt Fixed"))
		    		{
		    			crmstatus.setStatusId("16");
		    			userStatus = "Response";
		    		}
		    		if(userStatus != null && userStatus.equals("Follow-up(calls)"))
		    		{
		    			String calls = "2'";		    			
		    			calls = calls + ",'3', '5', '7', '9', '11' '14', '16' '17";
		    			crmstatus.setStatusId(calls);		    			
		    			userStatus = "Calls";
		    		}
		    		if(userStatus != null && userStatus.equals("Open"))
		    		{
		    			crmstatus.setStatusId("6");
		    			userStatus = "Data Collection";
		    		}
		    		if(userStatus != null && userStatus.equals("Email Sent"))
		    		{
		    			crmstatus.setStatusId("4");
		    			userStatus ="Email";
		    		}
		    		if(userStatus != null && userStatus.equals("Closed"))
		    		{
		    			crmstatus.setStatusId("14");
		    			userStatus ="Closed";
		    		}*/
		    		
		    		if (i == 0) 
		    		{
		    			istatus = new CRMStatus();	    			
		    			previousStatus = userStatus;	    			
		    			productivityList = new ArrayList<CRMStatus>();
		    		}		    		
		    		
		    		if (userStatus != null && !userStatus.equals(previousStatus)) 
		    		{
		    			productivityList.add(istatus);	
		    			statusHashmap.put(previousStatus, productivityList);
		    			
		    			istatus = new CRMStatus();
		   
		    			productivityList = new ArrayList<CRMStatus>();  
		    			
		    			String targettoday = "select  sum(Daily_Target) as todaytarget from azc_targets where User_id ='"+userId+"' and "
		    			   		+ "status_id in ('"+crmstatus.getStatusId()+"')  and tmonth = '"+month+"' and tyear = '"+year+"'";
		    			
		    			//System.out.println(""+targettoday);
		    			   
			  	    		  List<Map<String, Object>> sqlrows = jdbcTemplate.queryForList(targettoday);
			  	    		  for (Map<String, Object> row1 : sqlrows) 
			  	    		  {	  	    			 
			  	    			  if(row1.get("todaytarget") != null && !row1.get("todaytarget").equals(""))
			  	    			  {
			  	    				istatus.setTargetopendaily(row1.get("todaytarget").toString());
			  	    			  }
			  	    			  else
			  	    			  {
			  	    				istatus.setTargetopendaily("0") ;
			  	    			  }
			  	    		  }
			  	    		  
		  	    		int targetopenstatusweekly  = Integer.valueOf(istatus.getTargetopendaily()) * 5 ;		
		  	    		istatus.setTargetopenweekly(String.valueOf(targetopenstatusweekly));			
		  				
		  				//int targetopenstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(istatus.getTargetopendaily());	
		  	    		int targetopenstatusmonthly  = 22 * Integer.valueOf(istatus.getTargetopendaily());	
		  				istatus.setTargetopenmonthly(String.valueOf(targetopenstatusmonthly));
		  				
		  				for(int m=1 ; m <= 12; m++)
		  				{
		  					String targetopenFirstMonth = "select  sum(Daily_Target) as target from azc_targets where User_id ='"+userId+"' and "
			     			   		+ "status_id in ('"+crmstatus.getStatusId()+"') and tyear = '"+year+"' and tmonth = '"+m+"' ";
			     			   
			 	  	    		  List<Map<String, Object>> qfrows = jdbcTemplate.queryForList(targetopenFirstMonth);
			 	  	    		  for (Map<String, Object> row1 : qfrows) 
			 	  	    		  {	  	    			 
			 	  	    			  if(row1.get("target") != null && !row1.get("target").equals(""))
			 	  	    			  {
			 	  	    				 if(m == 1)
			 	  	    				 {
			 	  	    					janTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				 if(m == 2)
			 	  	    				 {
			 	  	    					febTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 3)
			 	  	    				 {
			 	  	    					marTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 4)
			 	  	    				 {
			 	  	    					aprTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 5)
			 	  	    				 {
			 	  	    					mayTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 6)
			 	  	    				 {
			 	  	    					junTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 7)
			 	  	    				 {
			 	  	    					julyTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 8)
			 	  	    				 {
			 	  	    					augTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 9)
			 	  	    				 {
			 	  	    					sepTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 10)
			 	  	    				 {
			 	  	    					octTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 11)
			 	  	    				 {
			 	  	    					novTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 12)
			 	  	    				 {
			 	  	    					decTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    			  }
			 	  	    		  }
		  				}
		  				
		  				 long targetyearly = 0;
		  				 long targetopensfirstHalfyearly = 0;
		  				 long targetopenssecondHalfyearly = 0;
		  				 long targetopensfirstQuarterly = 0;
		  				 long targetopenssecondQuarterly = 0;
		  				 long targetopensthirdQuarterly = 0;
		  				 long targetopensfourthQuarterly = 0;
		  				 
		  				 //System.out.println("month "+month);
		  				 
		  				if(month == 1)
		  				 {
		  					targetyearly = (22 * janTarget);
		  					targetopensfirstHalfyearly =  (22 * janTarget);
		  					targetopensfirstQuarterly =  (22 * janTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  				 }
		  				 if(month == 2)
		  				 {
		  					targetyearly = (22 * janTarget) +  (22 * febTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) + (22 * febTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) + (22 * febTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  				 }
		  				 if(month == 3)
		  				 {
		  					targetyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  				 }
		  				 if(month == 4)
		  				 {
		  					targetyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +
		  							(22* marTarget) + (22 * aprTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					targetopenssecondQuarterly =   (22 * aprTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));	
		  					
		  					
		  				 }
		  				if(month == 5)
		  				{
		  					targetyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					targetopenssecondQuarterly = (22 * aprTarget) + (22 * mayTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));	
		  				}
		  				if(month == 6)
		  				{
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					targetopenssecondQuarterly = (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  				}
		  				if(month == 7)
		  				{
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +  
		  							(22 * julyTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly = (22 * julyTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					targetopenssecondQuarterly =  (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly = (22 * julyTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  				}
		  				if(month == 8)
		  				{
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +
		  							(22 * julyTarget) + (22 * augTarget) ;
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly = 	(22 * julyTarget) +  (22 * augTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					targetopenssecondQuarterly =  (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly = (22 * julyTarget) + (22 * augTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  				}
		  				if(month == 9)
		  				{
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +
		  							(22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly =(22 * julyTarget) + (22 * augTarget) + (22 * sepTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					targetopenssecondQuarterly =  (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly =	(22 * julyTarget) + (22 * augTarget) + (22 * sepTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  				}
		  				if(month == 10)
		  				{
		  					targetyearly =   (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +
		  							(22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) + 
		  							(22 * octTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly = (22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) +
		  							(22 * octTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					targetopenssecondQuarterly =  (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly = (22 * julyTarget) + (22 * augTarget) + (22 * sepTarget);
		  					targetopensfourthQuarterly =  (22 * octTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  	    			istatus.setTargetFourthQuarterlyDeals(String.valueOf(targetopensfourthQuarterly));	
		  				}
		  				if(month == 11)
		  				{
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +
		  							(22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) + 
		  							(22 * octTarget) + (22 * novTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly = (22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) + 
		  							(22 * octTarget) + (22 * novTarget); 
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					targetopenssecondQuarterly =  	(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly =( 22 * julyTarget) + (22 * augTarget) + (22 * sepTarget);
		  					targetopensfourthQuarterly = (22 * octTarget) + (22 * novTarget); 
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  	    			istatus.setTargetFourthQuarterlyDeals(String.valueOf(targetopensfourthQuarterly));	
		  				}
		  				if(month == 12)
		  				{
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +
		  							(22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) + 
		  							(22 * octTarget) + (22 * novTarget) + (22 * decTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22* marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly = (22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) + 
		  							(22 * octTarget) + (22 * novTarget) + (22 * decTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22* marTarget) ;
		  					targetopenssecondQuarterly = (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly = (22 * julyTarget) + (22 * augTarget) + (22 * sepTarget);
		  					targetopensfourthQuarterly = (22 * octTarget) + (22 * novTarget) + (22 * decTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  	    			istatus.setTargetFourthQuarterlyDeals(String.valueOf(targetopensfourthQuarterly));	
		  				}
	  	    			
	  	    			String actualdaily = "select count(*) as actualtoday from azc_notes where User_id ='"+userId+"' and status_id  in ('"+crmstatus.getStatusId()+"') "
		  	    				+ "and Notes_Dt='"+currentDate+"' ";
		  	    		  
	  	    	    		  List<Map<String, Object>> rows1 = jdbcTemplate.queryForList(actualdaily);
	  	    	    		  for (Map<String, Object> row2 : rows1) 
	  	    	  	    	  {
	  	    	    			  if(row2.get("actualtoday") != null && !row2.get("actualtoday").equals(""))
	  	    	    			  {
	  	    	    				istatus.setOpendealsdaily(row2.get("actualtoday").toString());
	  	    	    			  }
	  	    	    			  else
	  	    	    			  {
	  	    	    				istatus.setOpendealsdaily("0");
	  	    	    			  } 
	  	    	  	    	  }
	  	    	    		  
	  	    	    		String actualweekly = "select count(*) as actualweek from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
	  		  	    				+ "and Notes_Dt BETWEEN '"+weekDate+"' AND '"+currentDate+"' ";
	  		  	    		  
	  	  	    	    		  List<Map<String, Object>> rowsi = jdbcTemplate.queryForList(actualweekly);
	  	  	    	    		  for (Map<String, Object> row2 : rowsi) 
	  	  	    	  	    	  {
	  	  	    	    			  if(row2.get("actualweek") != null && !row2.get("actualweek").equals(""))
	  	  	    	    			  {
	  	  	    	    				istatus.setOpendealsweekly(row2.get("actualweek").toString());
	  	  	    	    			  }
	  	  	    	    			  else
	  	  	    	    			  {
	  	  	    	    				istatus.setOpendealsweekly("0");
	  	  	    	    			  }  	  	    	    			
	  		  	    	  	    }
	  	    	    		
		  	    	    		  
	    	    		  String actualmonth = "select count(*) as actualmonth from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
			  	    				+ "and Notes_Dt BETWEEN '"+monthDate+"' AND '"+currentDate+"'";
	    	    		  
	 	    	    		  List<Map<String, Object>> rows2 = jdbcTemplate.queryForList(actualmonth);
	 	    	    		  for (Map<String, Object> rows3 : rows2) 
	 	    	  	    	  {
	 	    	    			  if(rows3.get("actualmonth") != null && !rows3.get("actualmonth").equals(""))
	 	    	    			  {
	 	    	    				
	 	    	    				 istatus.setOpendealsmonthly(rows3.get("actualmonth").toString());
	 	    	    			  }
	 	    	    			  else
	 	    	    			  {
	 	    	    				 istatus.setOpendealsmonthly("0");
	 	    	    			  }
	 	    	  	    	 }
	 	    	    		  
	 	    	    		 String actualyearly = "select count(*) as actualyear from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
	 		  	    				+ "and year(Notes_Dt) ='"+year+"' ";
	     	    		  
	  	    	    		  List<Map<String, Object>> rows4 = jdbcTemplate.queryForList(actualyearly);
	  	    	    		  for (Map<String, Object> rows3 : rows4) 
	  	    	  	    	  {
	  	    	    			  if(rows3.get("actualyear") != null && !rows3.get("actualyear").equals(""))
	  	    	    			  {
	  	    	    				
	  	    	    				 istatus.setYearlyDeals(rows3.get("actualyear").toString());
	  	    	    			  }
	  	    	    			  else
	  	    	    			  {
	  	    	    				 istatus.setYearlyDeals("0");
	  	    	    			  }
	  	    	  	    	 } 	    	    		  
	  	    	    		
	  	    	    		String actualfirstHalfyearly = "select count(*) as actualfirstHalfyear from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
	 		  	    				+ "and month(Notes_Dt) BETWEEN '1' AND '6' and year(Notes_Dt)='"+year+"' ";
	     	    		  
	  	    	    		  List<Map<String, Object>> rows5 = jdbcTemplate.queryForList(actualfirstHalfyearly);
	  	    	    		  for (Map<String, Object> rows3 : rows5) 
	  	    	  	    	  {
	  	    	    			  if(rows3.get("actualfirstHalfyear") != null && !rows3.get("actualfirstHalfyear").equals(""))
	  	    	    			  {
	  	    	    				
	  	    	    				 istatus.setFirsthalfYearlyDeals(rows3.get("actualfirstHalfyear").toString());
	  	    	    			  }
	  	    	    			  else
	  	    	    			  {
	  	    	    				 istatus.setFirsthalfYearlyDeals("0");
	  	    	    			  }
	  	    	  	    	 }
	  	    	    		  
	  	    	    		
		  	    	    		String actualsecondHalfyearly = "select count(*) as actualsecondHalfyear from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		 		  	    				+ "and month(Notes_Dt) BETWEEN '7' AND '12'  and year(Notes_Dt)='"+year+"' ";
		     	    		  
		  	    	    		  List<Map<String, Object>> rows6 = jdbcTemplate.queryForList(actualsecondHalfyearly);
		  	    	    		  for (Map<String, Object> rows3 : rows6) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("actualsecondHalfyear") != null && !rows3.get("actualsecondHalfyear").equals(""))
		  	    	    			  {
		  	    	    				
		  	    	    				 istatus.setSecondhalfYearlyDeals(rows3.get("actualsecondHalfyear").toString());
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setSecondhalfYearlyDeals("0");
		  	    	    			  }
		  	    	  	    	 }
	  	    	    		
		  	    	    		String actualFirstQuarter = "select count(*) as actualFirstquarter from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		 		  	    				+ "and month(Notes_Dt) BETWEEN '1' AND '3' and year(Notes_Dt)='"+year+"' ";
		     	    		  
		  	    	    		  List<Map<String, Object>> rows7 = jdbcTemplate.queryForList(actualFirstQuarter);
		  	    	    		  for (Map<String, Object> rows3 : rows7) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("actualFirstquarter") != null && !rows3.get("actualFirstquarter").equals(""))
		  	    	    			  {
		  	    	    				
		  	    	    				 istatus.setFirstQuarterlyDeals(rows3.get("actualFirstquarter").toString());
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setFirstQuarterlyDeals("0");
		  	    	    			  }
		  	    	  	    	 }
		  	    	    		
		  	    	    		String actualSecondQuarter = "select count(*) as actualSecondquarter from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		 		  	    				+ "and month(Notes_Dt) BETWEEN '4' AND '6' and year(Notes_Dt)='"+year+"'  ";
		     	    		  
		  	    	    		  List<Map<String, Object>> rows8 = jdbcTemplate.queryForList(actualSecondQuarter);
		  	    	    		  for (Map<String, Object> rows3 : rows8) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("actualSecondquarter") != null && !rows3.get("actualSecondquarter").equals(""))
		  	    	    			  {
		  	    	    				
		  	    	    				 istatus.setSecondQuarterlyDeals(rows3.get("actualSecondquarter").toString());
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setSecondQuarterlyDeals("0");
		  	    	    			  }
		  	    	  	    	 }
		  	    	    		  
		  	    	    		String actualThirdQuarter = "select count(*) as actualThirdquarter from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		 		  	    				+ "and month(Notes_Dt) BETWEEN '7' AND '9' and year(Notes_Dt)='"+year+"'";
		     	    		  
		  	    	    		  List<Map<String, Object>> rows9 = jdbcTemplate.queryForList(actualThirdQuarter);
		  	    	    		  for (Map<String, Object> rows3 : rows9) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("actualThirdquarter") != null && !rows3.get("actualThirdquarter").equals(""))
		  	    	    			  {
		  	    	    				
		  	    	    				 istatus.setThirdQuarterlyDeals(rows3.get("actualThirdquarter").toString());
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setThirdQuarterlyDeals("0");
		  	    	    			  }
		  	    	  	    	 }
		  	    	    		
		  	    	    		String actualFourthQuarter = "select count(*) as actualFourthquarter from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		 		  	    				+ "and month(Notes_Dt) BETWEEN '10' AND '12' and year(Notes_Dt)='"+year+"' ";
		     	    		  
		  	    	    		  List<Map<String, Object>> rows10 = jdbcTemplate.queryForList(actualFourthQuarter);
		  	    	    		  for (Map<String, Object> rows3 : rows10) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("actualFourthquarter") != null && !rows3.get("actualFourthquarter").equals(""))
		  	    	    			  {
		  	    	    				
		  	    	    				 istatus.setFourthQuarterlyDeals(rows3.get("actualFourthquarter").toString());
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setFourthQuarterlyDeals("0");
		  	    	    			  }
		  	    	  	    	 }
		  	    	    		  
		  	    	    istatus.setMonth(String.valueOf(month));
		  	    	    
		  	    	    if(crmstatus.getFromDate() != null && !crmstatus.getFromDate().equals(""))
		  	    	    {
		  	    	    	DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		  	    			DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		  	    			
		  	    			Date fromdt = null;
		  					String fromddate = "";					
		  					fromdt = originalFormat.parse(crmstatus.getFromDate());
		  					fromddate = targetFormat.format(fromdt);
		  					
		  					Date todt = null;
		  					String todate = "";					
		  					todt = originalFormat.parse(crmstatus.getToDate());
		  					todate = targetFormat.format(todt);
		  	    			
		  	    			
		  	    	    	String actualDate = "select count(*) as dailyTarget from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
	 		  	    				+ "and Notes_Dt BETWEEN '"+fromddate+"' AND '"+todate+"' ";
	     	    		  
	  	    	    		  List<Map<String, Object>> rows0 = jdbcTemplate.queryForList(actualDate);
	  	    	    		  for (Map<String, Object> rows3 : rows0) 
	  	    	  	    	  {
	  	    	    			  if(rows3.get("dailyTarget") != null && !rows3.get("dailyTarget").equals(""))
	  	    	    			  {
	  	    	    				
	  	    	    				 istatus.setActualDateuser(rows3.get("dailyTarget").toString());
	  	    	    			  }
	  	    	    			  else
	  	    	    			  {
	  	    	    				 istatus.setActualDateuser("0");
	  	    	    			  }
	  	    	  	    	 }
	  	    	    		  
	  	    	    		  String[] frommonthsplit = crmstatus.getFromDate().split("/");
		  	    	    	  String[] tomonthsplit = crmstatus.getToDate().split("/"); 
		  	    	    		
		  	    	    		int fromyear = Integer.valueOf(frommonthsplit[2]);
		  	    	    		int toyear = Integer.valueOf(tomonthsplit[2]);
		  	    	    		
		  	    	    		int frommonth = Integer.valueOf(frommonthsplit[0]);
		  	    	    		int tomonth = Integer.valueOf(frommonthsplit[0]);
		  	    	    		
		  	    	    		int fromdate = Integer.valueOf(frommonthsplit[1]);
		  	    	    		int weektodate = Integer.valueOf(frommonthsplit[1]);
		  	    	    		
		  	    	    		long noofweeks =  AllzoneCRMUtil.getNoofweekbetweenMonths(fromyear, toyear, frommonth, tomonth, fromdate, weektodate);
		  	    	    		
		  	    	    		String targetDate = "select  sum(Daily_Target) as dailyTarget from azc_targets where User_id ='"+userId+"' and " + 
		  	    	    				"status_id in ('"+crmstatus.getStatusId()+"') and tmonth between '"+frommonth+"' and '"+tomonth+"'";
		  	    	    		
		  	    	    		  List<Map<String, Object>> rows11 = jdbcTemplate.queryForList(targetDate);
		  	    	    		  for (Map<String, Object> rows3 : rows11) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("dailyTarget") != null && !rows3.get("dailyTarget").equals(""))
		  	    	    			  {
		  	    	    				 long targetdate = (Integer.valueOf(rows3.get("dailyTarget").toString())) * noofweeks * 5;
		  	    	    				 istatus.setTargetDateuser(String.valueOf(targetdate));
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setTargetDateuser("0");
		  	    	    			  }
		  	    	  	    	 }
		  	    	    }
	 	  	    		
		    		}
		    		else
		    		{
		    			 istatus = new CRMStatus(); 
		    			 
		    			String targettoday = "select  sum(Daily_Target) as todaytarget from azc_targets where User_id ='"+userId+"' and "
		    			   		+ "status_id in ('"+crmstatus.getStatusId()+"')  and tmonth = '"+month+"' and tyear = '"+year+"';";
		    			   
			  	    		  List<Map<String, Object>> sqlrows = jdbcTemplate.queryForList(targettoday);
			  	    		  for (Map<String, Object> row1 : sqlrows) 
			  	    		  {	  	    			 
			  	    			  if(row1.get("todaytarget") != null && !row1.get("todaytarget").equals(""))
			  	    			  {
			  	    				istatus.setTargetopendaily(row1.get("todaytarget").toString());
			  	    			  }
			  	    			  else
			  	    			  {
			  	    				istatus.setTargetopendaily("0") ;
			  	    			  }
			  	    		  }
			  	    		  
		  	    		int targetopenstatusweekly  = Integer.valueOf(istatus.getTargetopendaily()) * 5 ;		
		  	    		istatus.setTargetopenweekly(String.valueOf(targetopenstatusweekly));			
		  				
		  				//int targetopenstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(istatus.getTargetopendaily());	
		  	    		int targetopenstatusmonthly  = 22 * Integer.valueOf(istatus.getTargetopendaily());	
		  				istatus.setTargetopenmonthly(String.valueOf(targetopenstatusmonthly));
		  				
		  				for(int m=1 ; m <= 12; m++)
		  				{
		  					String targetopenFirstMonth = "select  sum(Daily_Target) as target from azc_targets where User_id ='"+userId+"' and "
			     			   		+ "status_id in ('"+crmstatus.getStatusId()+"') and tyear = '"+year+"' and tmonth = '"+m+"' ";
			     			   
			 	  	    		  List<Map<String, Object>> qfrows = jdbcTemplate.queryForList(targetopenFirstMonth);
			 	  	    		  for (Map<String, Object> row1 : qfrows) 
			 	  	    		  {	  	    			 
			 	  	    			  if(row1.get("target") != null && !row1.get("target").equals(""))
			 	  	    			  {
			 	  	    				 if(m == 1)
			 	  	    				 {
			 	  	    					janTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				 if(m == 2)
			 	  	    				 {
			 	  	    					febTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 3)
			 	  	    				 {
			 	  	    					marTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 4)
			 	  	    				 {
			 	  	    					aprTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 5)
			 	  	    				 {
			 	  	    					mayTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 6)
			 	  	    				 {
			 	  	    					junTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 7)
			 	  	    				 {
			 	  	    					julyTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 8)
			 	  	    				 {
			 	  	    					augTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 9)
			 	  	    				 {
			 	  	    					sepTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 10)
			 	  	    				 {
			 	  	    					octTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 11)
			 	  	    				 {
			 	  	    					novTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    				if(m == 12)
			 	  	    				 {
			 	  	    					decTarget = Integer.valueOf(row1.get("target").toString());
			 	  	    				 }
			 	  	    			  }
			 	  	    		  }
		  				}
		  				
		  				 long targetyearly = 0;
		  				 long targetopensfirstHalfyearly = 0;
		  				 long targetopenssecondHalfyearly = 0;
		  				 long targetopensfirstQuarterly = 0;
		  				 long targetopenssecondQuarterly = 0;
		  				 long targetopensthirdQuarterly = 0;
		  				 long targetopensfourthQuarterly = 0;
		  				 
		  				// System.out.println("month "+month);
		  				 
		  				if(month == 1)
		  				 {
		  					//targetyearly = (weeksforFirstMonth * 5 * janTarget);
		  					//targetopensfirstHalfyearly =  (weeksforFirstMonth * 5 * janTarget);
		  					//targetopensfirstQuarterly =  (weeksforFirstMonth * 5 * janTarget);
		  					
		  					targetyearly = (22 * janTarget);
		  					targetopensfirstHalfyearly =  (22 * janTarget);
		  					targetopensfirstQuarterly =  (22 * janTarget);
		  					
		  					System.out.println("targetyearly "+ targetyearly);
		  					System.out.println("targetopensfirstHalfyearly "+ targetopensfirstHalfyearly);
		  					System.out.println("targetopensfirstQuarterly "+ targetopensfirstQuarterly);
		  					
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  				 }
		  				 if(month == 2)
		  				 {
		  					//targetyearly = (weeksforFirstMonth * 5 * janTarget) +  (22 * febTarget);
		  					//targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) + (weeksforSecondMonth * 5 * febTarget);
		  					//targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) + (weeksforSecondMonth * 5 * febTarget);
		  					
		  					targetyearly = (22 * janTarget) +  (22 * febTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) + (22 * febTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) + (22 * febTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  				 }
		  				 if(month == 3)
		  				 {
		  					//targetyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget);
		  					//targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					//targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					 
		  					targetyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  				 }
		  				 if(month == 4)
		  				 {
		  					/*targetyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget);
		  					targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +
		  							(weeksforThirdMonth * 5* marTarget) + (weeksforFourthMonth * 5 * aprTarget);
		  					targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					targetopenssecondQuarterly =   (weeksforFourthMonth * 5 * aprTarget);*/
		  					
		  					targetyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +
		  							(22 * marTarget) + (22 * aprTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					targetopenssecondQuarterly =   (22 * aprTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));	
		  					
		  					
		  				 }
		  				if(month == 5)
		  				{
		  					/*targetyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget);
		  					targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget);
		  					targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					targetopenssecondQuarterly = (weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget);*/
		  					
		  					targetyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					targetopenssecondQuarterly = (22 * aprTarget) + (22 * mayTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));	
		  				}
		  				if(month == 6)
		  				{
		  					/*targetyearly =  (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					targetopenssecondQuarterly = (weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);*/
		  					
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					targetopenssecondQuarterly = (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);

		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 	    			
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  				}
		  				if(month == 7)
		  				{
		  					/*targetyearly =  (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget) +  
		  							(weeksforSeventhMonth * 5 * julyTarget);
		  					targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopenssecondHalfyearly = (weeksforSeventhMonth * 5 * julyTarget);
		  					targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					targetopenssecondQuarterly =  (weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopensthirdQuarterly = (weeksforSeventhMonth * 5 * julyTarget);*/
		  					
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +  
		  							(22 * julyTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly = (22 * julyTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					targetopenssecondQuarterly =  (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly = (22 * julyTarget);
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  				}
		  				if(month == 8)
		  				{
		  					/*targetyearly =  (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget) +
		  							(weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) ;
		  					targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopenssecondHalfyearly = 	(weeksforSeventhMonth * 5 * julyTarget) +  (weeksforEighthMonth * 5 * augTarget);
		  					targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					targetopenssecondQuarterly =  (weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopensthirdQuarterly = (weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget); */
		  					
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +
		  							(22 * julyTarget) + (22 * augTarget) ;
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly = 	(22 * julyTarget) +  (22 * augTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					targetopenssecondQuarterly =  (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly = (22 * julyTarget) + (22 * augTarget);

		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  				}
		  				if(month == 9)
		  				{
		  					/*targetyearly =  (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget) +
		  							(weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) +  (weeksforNinethMonth * 5 * sepTarget);
		  					targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopenssecondHalfyearly =(weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) + (weeksforNinethMonth * 5 * sepTarget);
		  					targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					targetopenssecondQuarterly =  (weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopensthirdQuarterly =	(weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) + (weeksforNinethMonth * 5 * sepTarget);*/
		  					
		  					
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +
		  							(22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly =(22 * julyTarget) + (22 * augTarget) + (22 * sepTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					targetopenssecondQuarterly =  (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly =	(22 * julyTarget) + (22 * augTarget) + (22 * sepTarget);

		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  				}
		  				if(month == 10)
		  				{
		  					/*targetyearly =   (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget) +
		  							(weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) +  (weeksforNinethMonth * 5 * sepTarget) + 
		  							(weeksforTenthMonth * 5 * octTarget);
		  					targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopenssecondHalfyearly = (weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) +  (weeksforNinethMonth * 5 * sepTarget) +
		  							(weeksforTenthMonth * 5 * octTarget);
		  					targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					targetopenssecondQuarterly =  (weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopensthirdQuarterly = (weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) + (weeksforNinethMonth * 5 * sepTarget);
		  					targetopensfourthQuarterly =  (weeksforTenthMonth * 5 * octTarget);*/
		  					
		  					targetyearly =   (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +
		  							(22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) + 
		  							(22 * octTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly = (22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) +
		  							(22 * octTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					targetopenssecondQuarterly =  (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly = (22 * julyTarget) + (22 * augTarget) + (22 * sepTarget);
		  					targetopensfourthQuarterly =  (22 * octTarget);
		  					
		  					
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  	    			istatus.setTargetFourthQuarterlyDeals(String.valueOf(targetopensfourthQuarterly));	
		  				}
		  				if(month == 11)
		  				{
		  					/*targetyearly =  (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget) +
		  							(weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) +  (weeksforNinethMonth * 5 * sepTarget) + 
		  							(weeksforTenthMonth * 5 * octTarget) + (weeksforEleventh * 5 * novTarget);
		  					targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopenssecondHalfyearly = (weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) +  (weeksforNinethMonth * 5 * sepTarget) + 
		  							(weeksforTenthMonth * 5 * octTarget) + (weeksforEleventh * 5 * novTarget); 
		  					targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					targetopenssecondQuarterly =  	(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopensthirdQuarterly =( weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) + (weeksforNinethMonth * 5 * sepTarget);
		  					targetopensfourthQuarterly = (weeksforTenthMonth * 5 * octTarget) + (weeksforEleventh * 5 * novTarget); */
		  					
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +
		  							(22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) + 
		  							(22 * octTarget) + (22 * novTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly = (22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) + 
		  							(22 * octTarget) + (22 * novTarget); 
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					targetopenssecondQuarterly =  	(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly =( 22 * julyTarget) + (22 * augTarget) + (22 * sepTarget);
		  					targetopensfourthQuarterly = (22 * octTarget) + (22 * novTarget); 

		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  	    			istatus.setTargetFourthQuarterlyDeals(String.valueOf(targetopensfourthQuarterly));	
		  				}
		  				if(month == 12)
		  				{
		  					/*targetyearly =  (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget) +
		  							(weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) +  (weeksforNinethMonth * 5 * sepTarget) + 
		  							(weeksforTenthMonth * 5 * octTarget) + (weeksforEleventh * 5 * novTarget) + (weeksforTwelvethMonth * 5 * decTarget);
		  					targetopensfirstHalfyearly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) +  (weeksforThirdMonth * 5* marTarget) + 
		  							(weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopenssecondHalfyearly = (weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) +  (weeksforNinethMonth * 5 * sepTarget) + 
		  							(weeksforTenthMonth * 5 * octTarget) + (weeksforEleventh * 5 * novTarget) + (weeksforTwelvethMonth * 5 * decTarget);
		  					targetopensfirstQuarterly = (weeksforFirstMonth * 5 * janTarget) +  (weeksforSecondMonth * 5 * febTarget) + (weeksforThirdMonth * 5* marTarget) ;
		  					targetopenssecondQuarterly = (weeksforFourthMonth * 5 * aprTarget) + (weeksforFifthMonth * 5 * mayTarget) + (weeksforSixthMonth * 5 * junTarget);
		  					targetopensthirdQuarterly = (weeksforSeventhMonth * 5 * julyTarget) + (weeksforEighthMonth * 5 * augTarget) + (weeksforNinethMonth * 5 * sepTarget);
		  					targetopensfourthQuarterly = (weeksforTenthMonth * 5 * octTarget) + (weeksforEleventh * 5 * novTarget) + (weeksforTwelvethMonth * 5 * decTarget);*/
		  					
		  					targetyearly =  (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget) +
		  							(22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) + 
		  							(22 * octTarget) + (22 * novTarget) + (22 * decTarget);
		  					targetopensfirstHalfyearly = (22 * janTarget) +  (22 * febTarget) +  (22 * marTarget) + 
		  							(22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopenssecondHalfyearly = (22 * julyTarget) + (22 * augTarget) +  (22 * sepTarget) + 
		  							(22 * octTarget) + (22 * novTarget) + (22 * decTarget);
		  					targetopensfirstQuarterly = (22 * janTarget) +  (22 * febTarget) + (22 * marTarget) ;
		  					targetopenssecondQuarterly = (22 * aprTarget) + (22 * mayTarget) + (22 * junTarget);
		  					targetopensthirdQuarterly = (22 * julyTarget) + (22 * augTarget) + (22 * sepTarget);
		  					targetopensfourthQuarterly = (22 * octTarget) + (22 * novTarget) + (22 * decTarget);
		  		
		  					
		  					istatus.setTargetYearlyDeals(String.valueOf(targetyearly));	  				
		  	    			istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly)); 
		  	    			istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopenssecondHalfyearly));
		  	    			istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		  	    			istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
		  	    			istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		  	    			istatus.setTargetFourthQuarterlyDeals(String.valueOf(targetopensfourthQuarterly));	
		  				}
	  	    			
	  	    			String actualdaily = "select count(*) as actualtoday from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		  	    				+ "and Notes_Dt='"+currentDate+"' ";
		  	    		  
	  	    	    		  List<Map<String, Object>> rows1 = jdbcTemplate.queryForList(actualdaily);
	  	    	    		  for (Map<String, Object> row2 : rows1) 
	  	    	  	    	  {
	  	    	    			  if(row2.get("actualtoday") != null && !row2.get("actualtoday").equals(""))
	  	    	    			  {
	  	    	    				istatus.setOpendealsdaily(row2.get("actualtoday").toString());
	  	    	    			  }
	  	    	    			  else
	  	    	    			  {
	  	    	    				istatus.setOpendealsdaily("0");
	  	    	    			  } 
	  	    	  	    	  }
	  	    	    		  
	  	    	    		String actualweekly = "select count(*) as actualweek from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
	  		  	    				+ "and Notes_Dt BETWEEN '"+weekDate+"' AND '"+currentDate+"' ";
	  		  	    		  
	  	  	    	    		  List<Map<String, Object>> rowsi = jdbcTemplate.queryForList(actualweekly);
	  	  	    	    		  for (Map<String, Object> row2 : rowsi) 
	  	  	    	  	    	  {
	  	  	    	    			  if(row2.get("actualweek") != null && !row2.get("actualweek").equals(""))
	  	  	    	    			  {
	  	  	    	    				istatus.setOpendealsweekly(row2.get("actualweek").toString());
	  	  	    	    			  }
	  	  	    	    			  else
	  	  	    	    			  {
	  	  	    	    				istatus.setOpendealsweekly("0");
	  	  	    	    			  }  	  	    	    			
	  		  	    	  	    }
	  	    	    		
		  	    	    		  
	    	    		  String actualmonth = "select count(*) as actualmonth from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
			  	    				+ "and Notes_Dt BETWEEN '"+monthDate+"' AND '"+currentDate+"'";
	    	    		  
	 	    	    		  List<Map<String, Object>> rows2 = jdbcTemplate.queryForList(actualmonth);
	 	    	    		  for (Map<String, Object> rows3 : rows2) 
	 	    	  	    	  {
	 	    	    			  if(rows3.get("actualmonth") != null && !rows3.get("actualmonth").equals(""))
	 	    	    			  {
	 	    	    				
	 	    	    				 istatus.setOpendealsmonthly(rows3.get("actualmonth").toString());
	 	    	    			  }
	 	    	    			  else
	 	    	    			  {
	 	    	    				 istatus.setOpendealsmonthly("0");
	 	    	    			  }
	 	    	  	    	 }
	 	    	    		  
	 	    	    		 String actualyearly = "select count(*) as actualyear from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
	 		  	    				+ "and year(Notes_Dt) ='"+year+"' ";
	     	    		  
	  	    	    		  List<Map<String, Object>> rows4 = jdbcTemplate.queryForList(actualyearly);
	  	    	    		  for (Map<String, Object> rows3 : rows4) 
	  	    	  	    	  {
	  	    	    			  if(rows3.get("actualyear") != null && !rows3.get("actualyear").equals(""))
	  	    	    			  {
	  	    	    				
	  	    	    				 istatus.setYearlyDeals(rows3.get("actualyear").toString());
	  	    	    			  }
	  	    	    			  else
	  	    	    			  {
	  	    	    				 istatus.setYearlyDeals("0");
	  	    	    			  }
	  	    	  	    	 } 	    	    		  
	  	    	    		
	  	    	    		String actualfirstHalfyearly = "select count(*) as actualfirstHalfyear from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
	 		  	    				+ "and month(Notes_Dt) BETWEEN '1' AND '6' and year(Notes_Dt)='"+year+"' ";
	     	    		  
	  	    	    		  List<Map<String, Object>> rows5 = jdbcTemplate.queryForList(actualfirstHalfyearly);
	  	    	    		  for (Map<String, Object> rows3 : rows5) 
	  	    	  	    	  {
	  	    	    			  if(rows3.get("actualfirstHalfyear") != null && !rows3.get("actualfirstHalfyear").equals(""))
	  	    	    			  {
	  	    	    				
	  	    	    				 istatus.setFirsthalfYearlyDeals(rows3.get("actualfirstHalfyear").toString());
	  	    	    			  }
	  	    	    			  else
	  	    	    			  {
	  	    	    				 istatus.setFirsthalfYearlyDeals("0");
	  	    	    			  }
	  	    	  	    	 }
	  	    	    		  
	  	    	    		
		  	    	    		String actualsecondHalfyearly = "select count(*) as actualsecondHalfyear from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		 		  	    				+ "and month(Notes_Dt) BETWEEN '7' AND '12'  and year(Notes_Dt)='"+year+"' ";
		     	    		  
		  	    	    		  List<Map<String, Object>> rows6 = jdbcTemplate.queryForList(actualsecondHalfyearly);
		  	    	    		  for (Map<String, Object> rows3 : rows6) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("actualsecondHalfyear") != null && !rows3.get("actualsecondHalfyear").equals(""))
		  	    	    			  {
		  	    	    				
		  	    	    				 istatus.setSecondhalfYearlyDeals(rows3.get("actualsecondHalfyear").toString());
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setSecondhalfYearlyDeals("0");
		  	    	    			  }
		  	    	  	    	 }
	  	    	    		
		  	    	    		String actualFirstQuarter = "select count(*) as actualFirstquarter from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		 		  	    				+ "and month(Notes_Dt) BETWEEN '1' AND '3' and year(Notes_Dt)='"+year+"' ";
		     	    		  
		  	    	    		  List<Map<String, Object>> rows7 = jdbcTemplate.queryForList(actualFirstQuarter);
		  	    	    		  for (Map<String, Object> rows3 : rows7) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("actualFirstquarter") != null && !rows3.get("actualFirstquarter").equals(""))
		  	    	    			  {
		  	    	    				
		  	    	    				 istatus.setFirstQuarterlyDeals(rows3.get("actualFirstquarter").toString());
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setFirstQuarterlyDeals("0");
		  	    	    			  }
		  	    	  	    	 }
		  	    	    		
		  	    	    		String actualSecondQuarter = "select count(*) as actualSecondquarter from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		 		  	    				+ "and month(Notes_Dt) BETWEEN '4' AND '6' and year(Notes_Dt)='"+year+"'  ";
		     	    		  
		  	    	    		  List<Map<String, Object>> rows8 = jdbcTemplate.queryForList(actualSecondQuarter);
		  	    	    		  for (Map<String, Object> rows3 : rows8) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("actualSecondquarter") != null && !rows3.get("actualSecondquarter").equals(""))
		  	    	    			  {
		  	    	    				
		  	    	    				 istatus.setSecondQuarterlyDeals(rows3.get("actualSecondquarter").toString());
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setSecondQuarterlyDeals("0");
		  	    	    			  }
		  	    	  	    	 }
		  	    	    		  
		  	    	    		String actualThirdQuarter = "select count(*) as actualThirdquarter from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		 		  	    				+ "and month(Notes_Dt) BETWEEN '7' AND '9' and year(Notes_Dt)='"+year+"'";
		     	    		  
		  	    	    		  List<Map<String, Object>> rows9 = jdbcTemplate.queryForList(actualThirdQuarter);
		  	    	    		  for (Map<String, Object> rows3 : rows9) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("actualThirdquarter") != null && !rows3.get("actualThirdquarter").equals(""))
		  	    	    			  {
		  	    	    				
		  	    	    				 istatus.setThirdQuarterlyDeals(rows3.get("actualThirdquarter").toString());
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setThirdQuarterlyDeals("0");
		  	    	    			  }
		  	    	  	    	 }
		  	    	    		
		  	    	    		String actualFourthQuarter = "select count(*) as actualFourthquarter from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
		 		  	    				+ "and month(Notes_Dt) BETWEEN '10' AND '12' and year(Notes_Dt)='"+year+"' ";
		     	    		  
		  	    	    		  List<Map<String, Object>> rows10 = jdbcTemplate.queryForList(actualFourthQuarter);
		  	    	    		  for (Map<String, Object> rows3 : rows10) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("actualFourthquarter") != null && !rows3.get("actualFourthquarter").equals(""))
		  	    	    			  {
		  	    	    				
		  	    	    				 istatus.setFourthQuarterlyDeals(rows3.get("actualFourthquarter").toString());
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setFourthQuarterlyDeals("0");
		  	    	    			  }
		  	    	  	    	 }
		  	    	    		  
		  	    	    istatus.setMonth(String.valueOf(month));
		  	    	    
		  	    	    if(crmstatus.getFromDate() != null && !crmstatus.getFromDate().equals(""))
		  	    	    {
		  	    	    	DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		  	    			DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		  	    			
		  	    			Date fromdt = null;
		  					String fromddate = "";					
		  					fromdt = originalFormat.parse(crmstatus.getFromDate());
		  					fromddate = targetFormat.format(fromdt);
		  					
		  					Date todt = null;
		  					String todate = "";					
		  					todt = originalFormat.parse(crmstatus.getToDate());
		  					todate = targetFormat.format(todt);
		  	    			
		  	    			
		  	    	    	String actualDate = "select count(*) as dailyTarget from azc_notes where User_id ='"+userId+"' and status_id in ('"+crmstatus.getStatusId()+"') "
	 		  	    				+ "and Notes_Dt BETWEEN '"+fromddate+"' AND '"+todate+"' ";
	     	    		  
	  	    	    		  List<Map<String, Object>> rows0 = jdbcTemplate.queryForList(actualDate);
	  	    	    		  for (Map<String, Object> rows3 : rows0) 
	  	    	  	    	  {
	  	    	    			  if(rows3.get("dailyTarget") != null && !rows3.get("dailyTarget").equals(""))
	  	    	    			  {
	  	    	    				
	  	    	    				 istatus.setActualDateuser(rows3.get("dailyTarget").toString());
	  	    	    			  }
	  	    	    			  else
	  	    	    			  {
	  	    	    				 istatus.setActualDateuser("0");
	  	    	    			  }
	  	    	  	    	 }
	  	    	    		  
	  	    	    		  String[] frommonthsplit = crmstatus.getFromDate().split("/");
		  	    	    	  String[] tomonthsplit = crmstatus.getToDate().split("/"); 
		  	    	    		
		  	    	    		int fromyear = Integer.valueOf(frommonthsplit[2]);
		  	    	    		int toyear = Integer.valueOf(tomonthsplit[2]);
		  	    	    		
		  	    	    		int frommonth = Integer.valueOf(frommonthsplit[0]);
		  	    	    		int tomonth = Integer.valueOf(frommonthsplit[0]);
		  	    	    		
		  	    	    		int fromdate = Integer.valueOf(frommonthsplit[1]);
		  	    	    		int weektodate = Integer.valueOf(frommonthsplit[1]);
		  	    	    		
		  	    	    		long noofweeks =  AllzoneCRMUtil.getNoofweekbetweenMonths(fromyear, toyear, frommonth, tomonth, fromdate, weektodate);
		  	    	    		
		  	    	    		String targetDate = "select  sum(Daily_Target) as dailyTarget from azc_targets where User_id ='"+userId+"' and " + 
		  	    	    				"status_id in ('"+crmstatus.getStatusId()+"') and tmonth between '"+frommonth+"' and '"+tomonth+"'";
		  	    	    		
		  	    	    		  List<Map<String, Object>> rows11 = jdbcTemplate.queryForList(targetDate);
		  	    	    		  for (Map<String, Object> rows3 : rows11) 
		  	    	  	    	  {
		  	    	    			  if(rows3.get("dailyTarget") != null && !rows3.get("dailyTarget").equals(""))
		  	    	    			  {
		  	    	    				 long targetdate = (Integer.valueOf(rows3.get("dailyTarget").toString())) * noofweeks * 5;
		  	    	    				 istatus.setTargetDateuser(String.valueOf(targetdate));
		  	    	    			  }
		  	    	    			  else
		  	    	    			  {
		  	    	    				 istatus.setTargetDateuser("0");
		  	    	    			  }
		  	    	  	    	 }
		  	    	    }
		    		}
		    		 previousStatus = userStatus;
		    		  i++;
		   		 }
		   		productivityList.add(istatus);	
		    	statusHashmap.put(userStatus, productivityList); 
		    /*}
		   		 
		   		catch (Exception e) 
			    {
		            e.printStackTrace();
			    }*/
		
		return statusHashmap;
}

	public List<Client> getEventDetails(Client client) throws Exception
	{
		String sql="";
		List<Client> eventReportList = new ArrayList<Client>();
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 sql ="SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name ,azc_department.Dept_Name, azc_source.Source_Desc, " + 
				"azc_status.Status_Desc, azc_user_master.First_Name, azc_events.Event_Name, azc_services.services_desc,azc_buckets.bucket_name  FROM azc_client left JOIN azc_countries " + 
				"ON azc_client.countries_id=azc_countries.id left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
				"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
				"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
				"left join azc_user_master on azc_client.User_id = azc_user_master.User_id left join azc_events on azc_events.event_id = azc_client.event_id "
				+ "left join azc_services on azc_services.services_id = azc_client.services_id left join azc_buckets on azc_buckets.bucket_id = azc_client.bucket_id  " +
				"where azc_client.event_id='"+client.getEventId()+"' ";
		 
		 if(client.getCreatedDate() != null && !client.getCreatedDate().equals(""))
		 {
			 Date fromdt = null;
			 String fromddate = "";	
			 fromdt = originalFormat.parse(client.getCreatedDate());
			 fromddate = targetFormat.format(fromdt);
			 
			 Date todt = null;
			 String todate = "";			 
			 todt = originalFormat.parse(client.getModifiedDate());
			 todate = targetFormat.format(todt);
			 
			 sql = sql + "and  azc_client.Modified_Dt between '"+fromddate+"' and '"+todate+"' ";
		 }
		
		//System.out.println(""+sql);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		eventReportList = jdbcTemplate.query(sql,new Object[] { }, new CRMReportRowMapper());

		return eventReportList;
	}

	public LinkedHashMap<String, LinkedHashMap<String, String>> getProductivityListforUsers(CRMStatus crmstatus)
			throws Exception 
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		
		LinkedHashMap<String, LinkedHashMap<String, String>> userHashmap = new LinkedHashMap<>();
		
		LinkedHashMap<String, String> statusHashmap = new LinkedHashMap<>();

		//String sql = "Select Status_Desc from azc_status where status_id in ('3','4','6','9','14')";
		String sql = "Select Status_Desc from azc_status where status_id in ("+ AllzoneCRMConstants.PRODUCTIVITY_STATUS +")";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	
		String currentUser = "";
		String previouUser = "";
		String statusname ="";
		String count="";
		
	
		String sql1 = "  SELECT azc_user_master.User_id, azc_notes.status_id, count(azc_notes.status_id) as count," + 
				"azc_user_master.First_Name, azc_user_master.Last_Name, azc_status.Status_Desc " + 
				"FROM azc_notes left join azc_user_master on azc_user_master.User_id = azc_notes.User_id " + 
				"left join azc_status on azc_status.status_id = azc_notes.status_id where month(azc_notes.Notes_Dt) = '"+month+"' " + 
				"and year(azc_notes.Notes_Dt)='"+year+"' and azc_notes.status_id in ("+ AllzoneCRMConstants.PRODUCTIVITY_STATUS +") and azc_user_master.user_status = 'A' " + 
				"group by  azc_notes.status_id, azc_user_master.User_id order by azc_notes.Notes_Id ";
		
		//System.out.println("sql1 is "+ sql1);
		
		List<Map<String, Object>> row1 = jdbcTemplate.queryForList(sql1);
		LinkedHashMap<String, String> userStatusHashmap = new LinkedHashMap<>();
		
		//System.out.println("row1 is "+ row1.size());
		
		int firstrow = 0;
		for (Map<String, Object> row : row1)
		{
			currentUser = row.get("First_Name").toString() + " " + row.get("Last_Name").toString();
			statusname = row.get("Status_Desc").toString();
			count = row.get("count").toString();		
	
			if (currentUser != null && !currentUser.equals(previouUser))
			{
				if(firstrow != 0)
				{
					userHashmap.put(previouUser, userStatusHashmap);
				}
				userStatusHashmap = statusHashmap;
				//System.out.println("statusname in if is "+ statusname);
				//System.out.println("count in if is "+ count);
			
				List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
				userStatusHashmap = new LinkedHashMap<>();
			
				for (Map<String, Object> row2 : rows)
				{
					String statusdesc = row2.get("Status_Desc").toString();
				
					userStatusHashmap.put(statusdesc, "0");
				}
		
				userStatusHashmap.put(statusname, count);
		
			}
			else
			{
				//System.out.println("statusname in else is "+ statusname);
				//System.out.println("count in else is "+ count);
				
				
				userStatusHashmap.put(statusname, count);
				
			}
			previouUser = currentUser;
	
			firstrow++;
		}
		
		//System.out.println("userHashmap.size() is "+ userHashmap.size());

		
		//if(userHashmap.size() > 0)
		//{
			userHashmap.put(currentUser, userStatusHashmap);
		//}
	
		//System.out.println("userHashmap.size() after is "+ userHashmap.size());
		
		return userHashmap;
	}

	@Override
	public LinkedHashMap<String, String> getUserHashMap(CRMStatus crmstatus) throws Exception
	{
		LinkedHashMap<String, String> statusHashmap = new LinkedHashMap<>();
		String sql = "Select Status_Desc from azc_status where status_id in ('3','4', '6', '9', '14')";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) 
		{
			String statusdesc = row.get("Status_Desc").toString();
			statusHashmap.put(statusdesc, "0");
		}

		return statusHashmap;
	}
	
	
	public List<Client> getAllClientList(Client client) throws Exception 
	{
		List<Client> clientlist = new ArrayList<Client>();
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar date = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     date.add(Calendar.YEAR,-1);
	     String endDate = f.format(date.getTime());

	     //System.out.println("endDate is "+ endDate);
	    // System.out.println("currentDate is "+ currentDate);
		
		String sql="";
		String phoneNo = "";
		
		if(client.getPhoneNumber() != null && !client.getPhoneNumber().equals(""))
		{
			phoneNo = client.getPhoneNumber().replaceAll("-", "");
		}
		
		
		sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name, azc_department.Dept_Name, azc_source.Source_Desc, " + 
				"azc_status.Status_Desc, (Select azc_notes.Notes from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by " + 
				"Notes_Id desc limit 1) as notes, azc_user_master.First_Name,azc_buckets.bucket_name from azc_client left JOIN azc_countries ON azc_client.countries_id=azc_countries.id  " + 
				"left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
				"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
				"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
				"left join azc_user_master on azc_user_master.User_id = azc_client.User_id left join azc_buckets on azc_buckets.bucket_id =azc_client.bucket_id  where";
				
				if(client.getTraceNo() != null && !client.getTraceNo().equals(""))
				{
					sql = sql + " azc_client.traceno = '"+client.getTraceNo()+"'";
				}
				
				if(client.getClientName() != null && !client.getClientName().equals(""))
				{
					if(sql.endsWith("where"))
					{
						sql = sql + " azc_client.Client_Name LIKE '%"+client.getClientName()+"%'";
					}
					else
					{
						sql = sql + " and azc_client.Client_Name LIKE '%"+client.getClientName()+"%'";
					}
				}
				
				if(client.getPhoneNumber() != null && !client.getPhoneNumber().equals(""))
				{
					if(sql.endsWith("where"))
					{
						sql = sql + " azc_client.phone_no = '"+phoneNo+"'";
					}
					else
					{
						sql = sql + " and azc_client.phone_no = '"+phoneNo+"'";
					}
				}
				
				if(client.getEmail() != null && !client.getEmail().equals(""))
				{
					if(sql.endsWith("where"))
					{
						sql = sql + " azc_client.e_mail LIKE '%"+client.getEmail()+"%'";
					}
					else
					{
						sql = sql + " and azc_client.e_mail LIKE '%"+client.getEmail()+"%'";
					}
				}
				
				if(client.getContactPerson() != null && !client.getContactPerson().equals(""))
				{
					if(sql.endsWith("where"))
					{
						sql = sql + " azc_client.Contact_Person LIKE '%"+client.getContactPerson()+"%'";
					}
					else
					{
						sql = sql + " and azc_client.Contact_Person LIKE '%"+client.getContactPerson()+"%'";
					}
				}
				
				if(client.getWebsite() != null && !client.getWebsite().equals(""))
				{
					if(sql.endsWith("where"))
					{
						sql = sql + " azc_client.website LIKE '%"+client.getWebsite()+"%'";
					}
					else
					{
						sql = sql + " and azc_client.website LIKE '%"+client.getWebsite()+"%'";
					}
				}
				
				if(sql.endsWith("where"))
				{
					sql = sql.replace("where", "");
				}
		
			//System.out.println("sql in client search "+ sql);
				//"where azc_client.status_id in('3','4','6','9', '10', '14','18','19') order by azc_client.traceno desc";
				//"where azc_client.Modified_Dt BETWEEN '"+endDate+"' AND '"+currentDate+"'  and azc_client.status_id in('3','4','6','9', '10', '14','18','19') order by azc_client.traceno desc";
	

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		clientlist = jdbcTemplate.query(sql, new CRMClientRowMapper());

		return clientlist;
		
	}
	
	
	public List<Client> getduplicateReportList(Client client) throws Exception{
	   String sql="";
	   List<Client> duplicateReportList = new ArrayList<Client>();
		
			if(client.getStatusflag() != null && client.getStatusflag().equals("Email") ){
				sql="select TraceNo, first_name, Client_Name, e_mail, mobile_no, website, phone_no from azc_client, azc_user_master where azc_client.User_id = azc_user_master.User_id "
						+ "and  e_mail in (select  e_mail from azc_client group by e_mail having count(e_mail) > 1) ORDER BY `azc_client`.`e_mail`  DESC ";
				 }
			else if(client.getStatusflag() != null && client.getStatusflag().equals("Phone")){
				sql="  select TraceNo, first_name, Client_Name, e_mail, mobile_no, website, phone_no from azc_client, azc_user_master where azc_client.User_id = azc_user_master.User_id" + 
						" and  phone_no in (select  phone_no from azc_client group by phone_no having count(phone_no) > 1) ORDER BY `azc_client`.`phone_no`  DESC ";
				}
			else if(client.getStatusflag() != null && client.getStatusflag().equals("Client")){
				sql="  select TraceNo, first_name, Client_Name, e_mail, mobile_no, website, phone_no from azc_client, azc_user_master where azc_client.User_id = azc_user_master.User_id" + 
						" and  Client_Name in (select  Client_Name from azc_client group by Client_Name having count(Client_Name) > 1) ORDER BY `azc_client`.`Client_Name`  DESC ";
				}
			else if(client.getStatusflag() != null && client.getStatusflag().equals("Website")){
				sql="  select TraceNo, first_name, Client_Name, e_mail, mobile_no, website, phone_no from azc_client, azc_user_master where azc_client.User_id = azc_user_master.User_id" + 
						" and  website in (select  website from azc_client group by website having count(website) > 1) ORDER BY `azc_client`.`website`  DESC ";
				}
			
			else {
				return null;
			}
			System.out.println("sql"+sql);
	   /*if(client.getStatusflag() != null && client.getStatusflag().equals("Email") ){
			sql="select TraceNo, first_name, Client_Name, e_mail, mobile_no, website, phone_no from azc_client, azc_user_master where azc_client.User_id = azc_user_master.User_id "
					+ " ORDER BY `azc_client`.`e_mail`  DESC limit 100";
			 }
		else if(client.getStatusflag() != null && client.getStatusflag().equals("Phone")){
			sql="  select TraceNo, first_name, Client_Name, e_mail, mobile_no, website, phone_no from azc_client, azc_user_master where azc_client.User_id = azc_user_master.User_id" + 
					"  ORDER BY `azc_client`.`phone_no`  DESC limit 100";
			}else {
			return null;
		}*/
		
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) 
		{	
			Client clien = new Client();
			if(row.get("TraceNo") != null && !row.get("TraceNo").equals(""))
			{
				clien.setTraceNo(row.get("TraceNo").toString());
			}
			if(row.get("first_name") != null && !row.get("first_name").equals(""))
			{
				clien.setName(row.get("first_name").toString());
			}
			
			if(row.get("Client_Name") != null && !row.get("Client_Name").equals(""))
			{
				clien.setClientName(row.get("Client_Name").toString());
			}
			if(row.get("e_mail") != null && !row.get("e_mail").equals(""))
			{				
				clien.setEmail(row.get("e_mail").toString());
			}
			if(row.get("mobile_no") != null && !row.get("mobile_no").equals(""))
			{
				clien.setMobileNumber(row.get("mobile_no").toString());
			}
			if(row.get("website") != null && !row.get("website").equals(""))
			{
				clien.setWebsite(row.get("website").toString());
			}
			if(row.get("phone_no") != null && !row.get("phone_no").equals(""))
			{
				clien.setPhoneNumber(row.get("phone_no").toString());
			}
			
			duplicateReportList.add(clien);
		}
		return duplicateReportList;
	}
	
	
	
	
	public List<Client> findDuplicates(String searchKey) throws Exception{
		   String sql="";
		   List<Client> duplicateReportList = new ArrayList<Client>();
			
				if(searchKey.equals("Client_Name") ){
					sql="SELECT azc_client.TraceNo, azc_client.Client_Name, azc_client.phone_no , azc_client.e_mail \r\n" + 
							"FROM azc_client INNER JOIN (SELECT Client_Name FROM azc_client GROUP  BY Client_Name HAVING COUNT(Client_Name) > 1) dup \r\n" + 
							"ON azc_client.Client_Name = dup.Client_Name order by azc_client.Client_Name";
				 }
				else if(searchKey.equals("phone_no") ) {
					sql="SELECT azc_client.TraceNo, azc_client.Client_Name, azc_client.phone_no , azc_client.e_mail, azc_client.website\r\n" + 
							"FROM azc_client INNER JOIN (SELECT phone_no FROM   azc_client where phone_no != '' and phone_no != '-' \r\n" + 
							"GROUP  BY phone_no HAVING COUNT(phone_no) > 1) dup  ON azc_client.phone_no = dup.phone_no \r\n" + 
							"order by azc_client.phone_no";
				}
				else if(searchKey.equals("e_mail") ) {
					sql="SELECT azc_client.TraceNo, azc_client.Client_Name, azc_client.phone_no , azc_client.e_mail, azc_client.website\r\n" + 
							"FROM azc_client INNER JOIN (SELECT e_mail FROM   azc_client where azc_client.e_mail != '' and azc_client.e_mail != '-' \r\n" + 
							"GROUP  BY e_mail HAVING COUNT(e_mail) > 1) dup  ON azc_client.e_mail = dup.e_mail \r\n" + 
							"order by azc_client.e_mail";
				}
				else{
					sql="SELECT azc_client.TraceNo, azc_client.Client_Name, azc_client.phone_no , azc_client.e_mail, azc_client.website\r\n" + 
							"FROM azc_client INNER JOIN (SELECT website FROM   azc_client where azc_client.website != '' and azc_client.website != '-' \r\n" + 
							"GROUP  BY website HAVING COUNT(website) > 1) dup  ON azc_client.website = dup.website \r\n" + 
							"order by azc_client.website";
				}
		   
		  /* sql = "SELECT azc_client.TraceNo, azc_client.Client_Name, azc_client.phone_no , azc_client.e_mail, azc_client.website FROM azc_client INNER JOIN (SELECT "+ searchKey+ " FROM azc_client GROUP BY "+ searchKey+" HAVING COUNT("+searchKey+") > 1) dup "
		   		+ "ON azc_client."+searchKey+" = dup."+searchKey+" order by azc_client."+searchKey;*/
		   

			System.out.println("findDuplicates :"+sql);
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

			for (Map<String, Object> row : rows) 
			{	
				Client clien = new Client();
				if(row.get("TraceNo") != null && !row.get("TraceNo").equals(""))
				{
					clien.setTraceNo(row.get("TraceNo").toString());
				}
				if(row.get("first_name") != null && !row.get("first_name").equals(""))
				{
					clien.setName(row.get("first_name").toString());
				}
				
				if(row.get("Client_Name") != null && !row.get("Client_Name").equals(""))
				{
					clien.setClientName(row.get("Client_Name").toString());
				}
				if(row.get("e_mail") != null && !row.get("e_mail").equals(""))
				{				
					clien.setEmail(row.get("e_mail").toString());
				}
				if(row.get("mobile_no") != null && !row.get("mobile_no").equals(""))
				{
					clien.setMobileNumber(row.get("mobile_no").toString());
				}
				if(row.get("website") != null && !row.get("website").equals(""))
				{
					clien.setWebsite(row.get("website").toString());
				}
				if(row.get("phone_no") != null && !row.get("phone_no").equals(""))
				{
					clien.setPhoneNumber(row.get("phone_no").toString());
				}
				
				duplicateReportList.add(clien);
			}
			return duplicateReportList;
		}

}
