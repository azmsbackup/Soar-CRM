package com.soarcrm.dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

import com.soarcrm.domain.CRMNotes;
import com.soarcrm.domain.Client;
import com.soarcrm.jdbc.CRMClientRowMapper;
import com.soarcrm.jdbc.CRMNotesRowMapper;
import com.soarcrm.jdbc.CheckClientRowMapper;
import com.soarcrm.util.AllzoneCRMConstants;
import com.soarcrm.util.AllzoneCRMUtil;

public class CRMClientDaoImpl implements CRMClientDao
{
	@Autowired
	DataSource dataSource;

	@Transactional(rollbackFor = Exception.class)
	public void insertClient(Client client) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		if(client.getAnnualRevenue() == null || client.getAnnualRevenue().equals(""))
		{
			client.setAnnualRevenue(null);
		}
		client.setExistingCustomer("N");
		
		if(client.getEventId() == null || client.getEventId().equals(""))
		{
			client.setEventId(null);
		}
		
		if(client.getCountriesId() != null && (client.getCountriesId().equals("231") || client.getCountriesId().equals("231.0")))
		{
			client.setCountryName("2");
		}
		if(client.getServicesId() == null || client.getServicesId().equals(""))
		{
			client.setServicesId(null);
		}
		
		String phoneNo = "";
		String mobileNo = "";
		String altMobileNo = "";
		if(client.getPhoneNumber() != null && !client.getPhoneNumber().equals(""))
		{
			phoneNo = client.getPhoneNumber().replaceAll("-", "");
		}
		if(client.getMobileNumber() != null && !client.getMobileNumber().equals(""))
		{
			mobileNo = client.getMobileNumber().replaceAll("-", "");
		}
		if(client.getAlternateMobileNumber() != null && !client.getAlternateMobileNumber().equals(""))
		{
			altMobileNo = client.getAlternateMobileNumber().replaceAll("-", "");
		}
		
		
		String city = client.getCityName().trim();
		String state = client.getStateName().trim();
		String sql = "INSERT INTO azc_client "
		    + "(Client_Name, Address, countries_id, State, City, Country, zip, Dept_Id, Contact_Person, designation, Phone_No, extn, mobile_no, "
		    + "alt_phone_no, Fax, e_Mail, time_zone, Status_Id, bucket_id, Website, Source_Id, event_id, Annual_Revenue, Description, Is_Existing_Customer, User_id, services_id,"
		    + " Created_By, Created_Dt, Modified_By, Modified_Dt,data_source,aggrement) "
		    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
		
		//if(client.getStatusId() == null || client.getStatusId().equals(""))
		//{
			//client.setStatusId("6");
			
		//}
		if(client.getAssignToUser() == null || client.getAssignToUser().equals(""))
		{
			client.setUserId(client.getUserId());
		}
		else
		{
			client.setUserId(client.getAssignToUser());
		}
	//	System.out.println("hi"+client.getServicesId());
		 jdbcTemplate.update(sql, new Object[] {client.getClientName(), client.getAddress(), client.getCountriesId(), state, city,
				client.getCountryName(), client.getZip(), client.getDeptId(), client.getContactPerson(), client.getDesignation(), phoneNo, client.getExtension(), 
				mobileNo,  altMobileNo, client.getFax(), client.getEmail(), client.getTimezone(), client.getStatusId(), client.getBucketId(),
				client.getWebsite(), client.getSourceId(), client.getEventId(), client.getAnnualRevenue(),  client.getDescription(),
				client.getExistingCustomer(), client.getUserId(), client.getServicesId(), client.getLoginName(), currentDate, client.getLoginName(), 
				currentDate, client.getDataSourceId(), client.getAggreement()});
		 
		 String sqlselect = "SELECT TraceNo FROM azc_client group by TraceNo desc LIMIT 1";
			
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlselect);
			String traceno="";
			for (Map<String, Object> row : rows) 
			{
				traceno = row.get("TraceNo").toString();
			}
			
			String strdatacollecteduser = client.getDataCollectedUser();
			String strassigneduser = client.getAssignToUser();
			
			//System.out.println("strdatacollecteduser "+ strdatacollecteduser);
			
			if(strassigneduser != null && !strassigneduser.equals(""))
			{
				client.setFromuserid(strassigneduser);
				client.setUserId(strassigneduser);
				
				String enddate  ="9999-12-31";
				String sql1 ="INSERT INTO azc_client_assignment "
				 		+ "(TraceNo, from_user_id,  to_user_id, Start_Dt, End_Dt, Created_By, Created_Dt)" 
						 +"VALUES(?, ?, ?, ?, ?, ?, ?)";
				 
				 
				 jdbcTemplate.update(sql1, new Object[] { traceno, client.getFromuserid(), client.getUserId(), currentDate, enddate, client.getLoginName(), currentDate});
			}
			else if(strdatacollecteduser != null && !strdatacollecteduser.equals(""))
			{
				client.setFromuserid(strdatacollecteduser);
				client.setUserId(strdatacollecteduser);
			}
			
			//System.out.println("client.getFromuserid() "+ client.getFromuserid());
			//System.out.println("client.getUserId() "+ client.getUserId());
			
			client.setTraceNo(traceno);
	 
		 String notes = "";
		 String notessql="";
		 
		 
		 notes = "Open Notes";
		 
		 notessql = "INSERT INTO azc_notes "
			 		+ "(TraceNo,  user_id, Notes, Notes_Dt, Status_Id, Created_By, Created_Dt )" 
					 +"VALUES(?, ?, ?, ?, ?, ?, ?)";
		 
		 jdbcTemplate.update(notessql, new Object[] { traceno, client.getDataCollectedUser(), notes, currentDate, client.getStatusId(), client.getLoginName(), 
				 currentDate});
		 /*if(client.getAssignToUser() == null || client.getAssignToUser().equals(""))
		 {
			 if(client.getDataCollectedUser() == null || client.getDataCollectedUser().equals(""))
			 {
				 client.setDataCollectedUser(client.getUserId());
			 }
			 	notes = "Open Notes";
			 
			 notessql = "INSERT INTO azc_notes "
				 		+ "(TraceNo,  user_id, Notes, Notes_Dt, Status_Id, Created_By, Created_Dt )" 
						 +"VALUES(?, ?, ?, ?, ?, ?, ?)";
			 
			 jdbcTemplate.update(notessql, new Object[] { traceno, client.getDataCollectedUser(), notes, currentDate, client.getStatusId(), client.getLoginName(), 
					 currentDate});
		 }
		 else
		 {
				String followupDate = "";
			 if(client.getStatusId().equals("3"))
			 {
				 DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
				 DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
					
					Date followupDt = null;
				
					if (client.getFollowUpDate() != null && !client.getFollowUpDate().equals("")) 
					{
						followupDt = originalFormat.parse(client.getFollowUpDate());
						followupDate = targetFormat.format(followupDt);
					}
				 notes = "Followup Notes";
				 
			 }
			 else if(client.getStatusId().equals("4"))
			 {
				 notes = "Email Sent notes";
				 followupDate =null;
			 }
			 notessql = "INSERT INTO azc_notes "
				 		+ "(TraceNo,  user_id, Notes, Notes_Dt, Status_Id, Created_By, Created_Dt )" 
						 +"VALUES(?, ?, ?, ?, ?, ?, ?)";
			 jdbcTemplate.update(notessql, new Object[] { traceno, client.getDataCollectedUser(), "Open Notes", currentDate, "6", 
					 client.getLoginName(), currentDate});
			 
			 if(client.getStatusId() != "6")
			 {
				 notessql = "INSERT INTO azc_notes "
					 		+ "(TraceNo,  user_id, Notes, Notes_Dt, Followup_Dt, Status_Id, Created_By, Created_Dt )" 
							 +"VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				 jdbcTemplate.update(notessql, new Object[] { traceno, client.getAssignToUser(), notes, currentDate, followupDate, client.getStatusId(), 
					 client.getLoginName(), currentDate});
			 }
		 }*/
		
	}

	public List<Client> getClientList(String roleid, String userid) throws Exception 
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
		
		if(roleid != null && !roleid.equals(AllzoneCRMConstants.TEAM_ROLE_ID))
		{
		
			sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name, azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, (Select azc_notes.Notes from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by " + 
					"Notes_Id desc limit 1) as notes, azc_user_master.First_Name, azc_buckets.bucket_name from azc_client left JOIN azc_countries ON azc_client.countries_id=azc_countries.id  " + 
					"left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_user_master.User_id = azc_client.User_id left join azc_buckets on azc_buckets.bucket_id =azc_client.bucket_id " + 
					"where azc_client.Modified_Dt BETWEEN '"+endDate+"' AND '"+currentDate+"'  order by azc_client.traceno desc limit 500";
		}
		else
		{
			sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name, azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, (Select azc_notes.Notes from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by " + 
					"Notes_Id desc limit 1) as notes, azc_user_master.First_Name, azc_buckets.bucket_name from azc_client left JOIN azc_countries ON azc_client.countries_id=azc_countries.id " + 
					"left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_user_master.User_id = azc_client.User_id left join azc_buckets on azc_buckets.bucket_id =azc_client.bucket_id " + 
					"where azc_client.Modified_Dt BETWEEN '"+endDate+"' AND '"+currentDate+"' and azc_client.User_id='"+userid+"' order by azc_client.traceno desc limit 500";
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		clientlist = jdbcTemplate.query(sql, new CRMClientRowMapper());

		return clientlist;
		
	}
	
	
	public List<Client> getClientListonAddGrp(String roleid, String userid) throws Exception 
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
		
		if(roleid != null && !roleid.equals(AllzoneCRMConstants.TEAM_ROLE_ID))
		{
		
			sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name, azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, (Select azc_notes.Notes from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by " + 
					"Notes_Id desc limit 1) as notes, azc_user_master.First_Name, azc_buckets.bucket_name from azc_client left JOIN azc_countries ON azc_client.countries_id=azc_countries.id  " + 
					"left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_user_master.User_id = azc_client.User_id left join azc_buckets on azc_buckets.bucket_id =azc_client.bucket_id " + 
					"where azc_client.Status_Id !=14  and azc_client.Modified_Dt BETWEEN '"+endDate+"' AND '"+currentDate+"'  order by azc_client.traceno desc limit 500";
		}
		else
		{
			sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name, azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, (Select azc_notes.Notes from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by " + 
					"Notes_Id desc limit 1) as notes, azc_user_master.First_Name, azc_buckets.bucket_name from azc_client left JOIN azc_countries ON azc_client.countries_id=azc_countries.id " + 
					"left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_user_master.User_id = azc_client.User_id left join azc_buckets on azc_buckets.bucket_id =azc_client.bucket_id " + 
					"where azc_client.Status_Id !=14 and azc_client.Modified_Dt BETWEEN '"+endDate+"' AND '"+currentDate+"' and azc_client.User_id='"+userid+"' order by azc_client.traceno desc limit 500";
		}
		
		System.out.println("sql :"+sql);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		clientlist = jdbcTemplate.query(sql, new CRMClientRowMapper());

		return clientlist;
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void insertNotes(CRMNotes notes) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date notesdt = null;
		String notesdate = "";
		if (notes.getNotesDate() != null && !notes.getNotesDate().equals("")) 
		{
			notesdt = originalFormat.parse(notes.getNotesDate());
			notesdate = targetFormat.format(notesdt);
		}
			
		Date followupdt = null;
		String followupdate = "";
		if (notes.getFollowUpDate() != null && !notes.getFollowUpDate().equals("")) 
		{
			followupdt = originalFormat.parse(notes.getFollowUpDate());
			followupdate = targetFormat.format(followupdt);
		}
		else
		{
			followupdate=null;
		}
		
		Date appointmentdt = null;
		String appointmentdate = "";
		if (notes.getAppointmentDate() != null && !notes.getAppointmentDate().equals("")) 
		{
			appointmentdt = originalFormat.parse(notes.getAppointmentDate());
			appointmentdate = targetFormat.format(appointmentdt);
		}
		else
		{
			appointmentdate=null;
		}
		
		if(notes.getStatusId() == null && !notes.getStatusId().equals(AllzoneCRMConstants.STATUS_ID_OPEN))
		{
			notes.setAppointmentTime(null);
		}
		
		notes.setAppointmentStatus(null);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		
		String sql = "INSERT INTO azc_notes "
		    + "(Notes_Id, TraceNo, user_id, Notes, Notes_Dt, Status_Id,bucket_id, Followup_Dt,"
		    + " Appointment_With, Appointment_Status, Created_By, Created_Dt, Modified_By, Modified_Dt) "
		    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
         jdbcTemplate.update(sql, new Object[] { notes.getNotesId(), notes.getTraceNo(), notes.getUserId(), notes.getNotes(), notesdate,
				 notes.getStatusId(),notes.getBucketId(), followupdate,  notes.getAppointmentWith(),
				 notes.getAppointmentStatus(), notes.getLoginName(), currentDate , notes.getLoginName(), currentDate });
		 
		 String existingCustomer="";
		 if(notes.getStatusId().equals(AllzoneCRMConstants.STATUS_ID_CLOSED))
		 {
			 existingCustomer = "N";
		 }
		 else
		 {
			 existingCustomer = "Y";
		 }
		 
		 String sql1 = "UPDATE azc_client set Status_Id=?, bucket_id = ?, Is_Existing_Customer=?, Modified_By=?, Modified_Dt = ?  where TraceNo = ?";

		 jdbcTemplate.update(sql1, new Object[] { notes.getStatusId(),notes.getBucketId(), existingCustomer, notes.getLoginName(), currentDate,  notes.getTraceNo() });
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void modifyNotes(CRMNotes notes) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date notesdt = null;
		String notesdate = "";
		if (notes.getNotesDate() != null && !notes.getNotesDate().equals("")) 
		{
			notesdt = originalFormat.parse(notes.getNotesDate());
			notesdate = targetFormat.format(notesdt);
		}
			
		Date followupdt = null;
		String followupdate = "";
		if (notes.getFollowUpDate() != null && !notes.getFollowUpDate().equals("")) 
		{
			followupdt = originalFormat.parse(notes.getFollowUpDate());
			followupdate = targetFormat.format(followupdt);
		}
		else
		{
			followupdate=null;
		}
		
		Date appointmentdt = null;
		String appointmentdate = "";
		if (notes.getAppointmentDate() != null && !notes.getAppointmentDate().equals("")) 
		{
			appointmentdt = originalFormat.parse(notes.getAppointmentDate());
			appointmentdate = targetFormat.format(appointmentdt);
		}
		else
		{
			appointmentdate=null;
		}
		
		if(notes.getStatusId() == null && !notes.getStatusId().equals(AllzoneCRMConstants.STATUS_ID_OPEN))
		{
			notes.setAppointmentTime(null);
		}
		
		notes.setAppointmentStatus(null);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		
/*		String sql = "INSERT INTO azc_notes "
		    + "(Notes_Id, TraceNo, user_id, Notes, Notes_Dt, Status_Id,bucket_id, Followup_Dt,"
		    + " Appointment_With, Appointment_Status, Created_By, Created_Dt, Modified_By, Modified_Dt) "
		    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";*/
		
		//System.out.println("notes.getNotesId() "+ notes.getNotesId());
		String sql = "UPDATE azc_notes set "
			    + "  Notes= ?, Notes_Dt= ?, Status_Id= ?,bucket_id= ?, Followup_Dt= ?,"
			    + " Appointment_With= ?, Appointment_Status= ?, Modified_By= ?, Modified_Dt= ? "
			    + " where Notes_Id = ?";
		
         jdbcTemplate.update(sql, new Object[] { notes.getNotes(), notesdate,
				 notes.getStatusId(),notes.getBucketId(), followupdate,  notes.getAppointmentWith(),
				 notes.getAppointmentStatus(),  notes.getLoginName(), 
				 currentDate, notes.getNotesId() });
		 
		 String existingCustomer="";
		 if(notes.getStatusId().equals(AllzoneCRMConstants.STATUS_ID_CLOSED))
		 {
			 existingCustomer = "N";
		 }
		 else
		 {
			 existingCustomer = "Y";
		 }
		 
		 String sql1 = "UPDATE azc_client set Status_Id=?, bucket_id = ?, Is_Existing_Customer=?, Modified_By=?, Modified_Dt = ?  where TraceNo = ?";

		 jdbcTemplate.update(sql1, new Object[] { notes.getStatusId(),notes.getBucketId(), existingCustomer, notes.getLoginName(), currentDate,  notes.getTraceNo() });
		
	}
	
	

	public Client getClientDetails(String id) throws Exception 
	{
		List<Client> clientlist = new ArrayList<Client>();		
		
		String sql = "SELECT azc_client.*,azc_countries.name , azc_department.Dept_Name, azc_source.Source_Desc, " + 
				"azc_status.Status_Desc, (Select azc_notes.Notes from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by " + 
				"Notes_Id desc limit 1) as notes, azc_user_master.First_Name,azc_buckets.bucket_name from azc_client "
				+ "left JOIN azc_countries ON azc_client.countries_id=azc_countries.id " + 
				"left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
				"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
				"left join azc_user_master on azc_user_master.User_id = azc_client.User_id "
				+ "left join azc_buckets on azc_buckets.bucket_id =azc_client.bucket_id where azc_client.TraceNo= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		clientlist = jdbcTemplate.query(sql, new CRMClientRowMapper());
		
	  return clientlist.get(0);
	}

	public void updateClient(Client client) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");	
		
		if(client.getStatusId() != null && client.getStatusId().equals(AllzoneCRMConstants.STATUS_ID_CLOSED))
		{
			client.setExistingCustomer("Y");
		}
		else
		{
			client.setExistingCustomer("N");
		}
		if(client.getAnnualRevenue().equals(""))
		{
			client.setAnnualRevenue(null);
		}
		if(client.getEventId() == null || client.getEventId().equals(""))
		{
			client.setEventId(null);
		}
		if(client.getServicesId() == null || client.getServicesId().equals(""))
		{
			client.setServicesId(null);
		}
		
		String phoneNo = "";
		String mobileNo = "";
		String altMobileNo = "";
		if(client.getPhoneNumber() != null && !client.getPhoneNumber().equals(""))
		{
			phoneNo = client.getPhoneNumber().replaceAll("-", "");
		}
		if(client.getMobileNumber() != null && !client.getMobileNumber().equals(""))
		{
			mobileNo = client.getMobileNumber().replaceAll("-", "");
		}
		if(client.getAlternateMobileNumber() != null && !client.getAlternateMobileNumber().equals(""))
		{
			altMobileNo = client.getAlternateMobileNumber().replaceAll("-", "");
		}
		
		String sql = "UPDATE azc_client set  Client_Name=?,Address = ?, City=?, State=?, countries_id=?, zip=?, Dept_Id=?, Contact_Person=?, designation=?, "
				+ "Phone_No=?, extn=?, mobile_no=?, alt_phone_no=?, Fax=?, e_Mail=?, time_zone = ?,  event_id = ?,  Website=?,  Annual_Revenue=?, "
				+ "Description=?, Is_Existing_Customer=?, services_id=?, Modified_By=?, "
				+ "Modified_Dt = ?, Source_Id=?, aggrement = ? where TraceNo = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update( sql, new Object[] {client.getClientName(), client.getAddress(), client.getCityName(), client.getStateName(), client.getCountriesId(),
				client.getZip(), client.getDeptId(), client.getContactPerson(), client.getDesignation(),  phoneNo, client.getExtension(),
				mobileNo, altMobileNo, client.getFax(), client.getEmail(), client.getTimezone(), client.getEventId(),
				 client.getWebsite(),  client.getAnnualRevenue(), client.getDescription(), 
				client.getExistingCustomer(), client.getServicesId(), client.getLoginName(), 
				currentDate, client.getSourceId(), client.getAggreement(), client.getTraceNo()});
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Client insertData(Sheet sheet, String fileName, Client client, Workbook wb) throws Exception 
	{
		int k = 2;
		try 
		{

			List<Client> logTracelist = new ArrayList<Client>();
			List<Client> clientlist = new ArrayList<Client>();
			
			LinkedHashMap<String, String> departmentHashmap = getdeptHashMap();
			LinkedHashMap<String, String> userHashmap = getUserHashMap();
			LinkedHashMap<String, String> eventHashmap = getEventHashMap();
			LinkedHashMap<String, String> servicesHashmap = getServicesHashMap();
			LinkedHashMap<String, String> sourceHashmap = getSourceHashMap();

			int i = 0;

			Iterator<Row> rows = sheet.rowIterator();
			int rownum = client.getRownum();
			
			//System.out.println("rownum in dao "+rownum);

			while (rows.hasNext()) 
			{
				Row row = (Row) rows.next();
				if (i == 0) 
				{
					row = (Row) rows.next();
					i++;
				}
				int s = 0;
				Iterator<Cell> cells = row.cellIterator();
				String[] queryValue = new String[13];
				
				//System.out.println("before cells");

				while (cells.hasNext()) 
				{

					if (s < 13) 
					{
						Cell cell = cells.next();
						int currentCell = cell.getColumnIndex();
						//System.out.println("currentCell "+currentCell);

					
						switch (cell.getCellType()) 
						{
							case Cell.CELL_TYPE_NUMERIC:
								if (DateUtil.isCellDateFormatted(cell)) 
								{
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
									String datevalue = dateFormat.format(cell.getDateCellValue());									
									queryValue[currentCell] = datevalue;
								} 
								else 
								{
									queryValue[currentCell] = NumberToTextConverter.toText(cell.getNumericCellValue());
								}
								break;
							case Cell.CELL_TYPE_STRING:
								queryValue[currentCell] = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_FORMULA:
								 FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();
								 CellValue c=formulaEval.evaluate(cell);
								 double dformulaValue=c.getNumberValue();
								// System.out.println("dformulaValue "+ dformulaValue);
								 String sformulaValue = String.valueOf(dformulaValue);
								 //System.out.println("sformulaValue "+ sformulaValue);
								 queryValue[currentCell] =sformulaValue;
								 break;
						}
						s++;
						//System.out.println("s value is "+ s);
					}
					else
					{
						break;
					}
					//System.out.println("s < 17");
				}
				
				//System.out.println("after while");
				
				String userloginname = client.getLoginName();
				String userid= client.getUserId();
				
			/*	queryValue[17] = userid;
				queryValue[18] = userloginname;
				queryValue[19] = currentDate;
				queryValue[20] = userloginname;
				queryValue[21] = currentDate;*/
				
				Client client1 = new Client();
				
				String[] phoneNumber=null;
				if(queryValue[7] != null && !queryValue[7].equals(""))
				{
					phoneNumber = queryValue[7].split(";");
					
					for (int m=0; m < phoneNumber.length; m++)
					 {
						 if(m == 0)
						 {
							 client1.setPhoneNumber(phoneNumber[0]);
						 }
						 if(m == 1)
						 {
							 client1.setMobileNumber(phoneNumber[1]);
						 }
						 if(m == 2)
						 {
							 client1.setAlternateMobileNumber(phoneNumber[2]);
						 }
					 }
				}
				
				
				String[] Email=null;
				if(queryValue[8] != null && !queryValue[8].equals(""))
				{
					Email = queryValue[8].split(";");
					
					 for (int m=0; m < Email.length; m++)
					 {
						 if(m == 0)
						 {
							 client1.setEmail(Email[0]);
						 }
						 if(m == 1)
						 {
							 client1.setEmail2(Email[1]);
						 }
						 if(m == 2)
						 {
							 client1.setEmail3(Email[2]);
						 }
					 }					
				}
				
				//client1.setEmail(queryValue[10]);
				//client1.setPhoneNumber(queryValue[8]);
				client1.setWebsite(queryValue[9]);
				client1.setClientName(queryValue[0]);
				client.setClientName(queryValue[0]);
				
				Client clientmail = null;
				Client clientwebsite = null;
				Client clientphone  = null;
				Client mobileNo = null;
				Client altMobileNo = null ;
				if(queryValue[8] != null && !queryValue[8].equals(""))
				{
					clientmail = checkEmail(client1);
				}
				if(queryValue[9] != null && !queryValue[9].equals(""))
				{
					clientwebsite = checkWebsite(client1);
				}
				if(queryValue[7] != null && !queryValue[7].equals(""))
				{
					clientphone = checkPhoneNo(client1);					
				}
				if(client1.getMobileNumber() != null && !client1.getMobileNumber().equals(""))
				{
					mobileNo = checkMobileNo(client1);
				}
				if(client1.getAlternateMobileNumber() != null && !client1.getAlternateMobileNumber().equals(""))
				{
					altMobileNo = checkaltMobileNo(client1);
				}
				Client clientname = checkClientName(client1);
		
				
				if((clientmail == null) && clientphone == null && clientwebsite== null &&  clientname== null && mobileNo == null && altMobileNo== null)
				{
					//System.out.println("clientmail == null");
					if (queryValue[0] != null && !queryValue[0].equals("")) 
					{
						Client clientadd = new Client();
						clientadd.setClientName(queryValue[0]);
						clientadd.setAddress(queryValue[1]);
						clientadd.setCountriesId("231");											
						//System.out.println("departmentHashmap.get(queryValue[6])  "+departmentHashmap.get(queryValue[6]));
						clientadd.setStateName(queryValue[2]);
						clientadd.setCityName(queryValue[3]);					
						clientadd.setZip(queryValue[4]);
						clientadd.setDeptId(departmentHashmap.get(queryValue[5]));
						//clientadd.setServicesId(servicesHashmap.get(queryValue[6]));
						clientadd.setSourceId(sourceHashmap.get(queryValue[12]));
						
						String conPerson = "";
						String designation = "";
						
						//System.out.println("queryValue[6] "+ queryValue[6]);
						
						if(queryValue[6] != null && queryValue[6].contains(";"))
						{
					
							String str[] = queryValue[6].split(";");
							
							//System.out.println("str[0] "+ str[0]);
							//System.out.println("str[1] "+ str[1]);
							
							//String str[0] = queryValue[6];
							
							for(int x = 0; x < str.length; x++) 
							{	
								
								if(x == 0) 
								{
									if( str[x].contains("("))
									{
									 conPerson = str[x].substring(0, str[x].indexOf("("));
									 designation = str[x].substring(str[x].indexOf("(")+1, str[x].indexOf(")"));
									}
									else  {
										 conPerson = str[x];
										 designation = "-";
										}
								}
								else {
									if( str[x].contains("("))
									{
									 
										conPerson = conPerson+";"+ str[x].substring(0, str[x].indexOf("("));
										
										
										if(designation.equals(""))
										{
											designation = "-;"+ str[x].substring(str[x].indexOf("(")+1, str[x].indexOf(")"));
										}
										else
										{
											designation = designation+";"+ str[x].substring(str[x].indexOf("(")+1, str[x].indexOf(")"));
										}
									}
									else  {
										 conPerson = conPerson+";"+str[x];
										 designation = designation + ";-";
										}
									
								}
							}
						}
						else
						{
							String contValue = queryValue[6];
							
							//System.out.println("contValue "+ contValue);

								if(contValue.contains("("))
								{
								 conPerson = contValue.substring(0, contValue.indexOf("("));
								 designation = contValue.substring(contValue.indexOf("(")+1, contValue.indexOf(")"));
								}
								else  {
									 conPerson = contValue;
									 designation = "-";
									}
				
						}
						//System.out.println("conPerson "+ conPerson);
						//System.out.println("designation "+ designation);
						
						clientadd.setContactPerson(conPerson);
						clientadd.setDesignation(designation);
						
						clientadd.setPhoneNumber(client1.getPhoneNumber());
						clientadd.setMobileNumber(client1.getMobileNumber());
						clientadd.setAlternateMobileNumber(client1.getAlternateMobileNumber());
						//clientadd.setFax(queryValue[10]);
						//clientadd.setEmail(queryValue[10]);
						String email =client1.getEmail();						
						if(client1.getEmail2() == null || client1.getEmail2().equals(""))
						{
							client1.setEmail2("");
						}
						else
						{
							
							email = email + ";" + client1.getEmail2();
						}
						if(client1.getEmail3() == null || client1.getEmail3().equals(""))
						{
							client1.setEmail3("");
						}
						else
						{
							email = email + ";" + client1.getEmail3();
						}
						clientadd.setEmail(email);
						clientadd.setStatusId("6");
						clientadd.setBucketId("1");
						clientadd.setWebsite(queryValue[9]);
						clientadd.setDataSourceId(AllzoneCRMConstants.SOURCE_ID_TWO);
						//clientadd.setAnnualRevenue(queryValue[12]);
						//clientadd.setDescription(queryValue[13]);		
						clientadd.setDataCollectedUser(userHashmap.get(queryValue[10]));
						clientadd.setUserId(userHashmap.get(queryValue[10]));	
						
						/*if(queryValue[15] != null && !queryValue[15].equals(""))
						{
							clientadd.setEventId(eventHashmap.get(queryValue[15]));
						}
						*/
						clientadd.setTimezone(queryValue[11]);
						clientadd.setLoginName(userloginname);
						clientlist.add(clientadd);
						
						//System.out.println("before insert client in dao "+row.getRowNum());
						insertClient(clientadd);
					}
				}
				else
				{
					Client clien = new Client();
					clien.setUploadClientName(queryValue[0]);
					clien.setUserId(userid);
					clien.setLoginName(userloginname);
					
					String description="";

					if(clientname != null)
					{
						description= "Row No : "+ k+ " -";
						description = description + " Client Name("+clientname.getLogclientname()+") already exist in Trace No : "+clientname.getTraceNo()+",";
					}
					else if(clientmail != null)
					{
						description= "Row No :"+ k+ " -";
						description = description + " Email Id("+clientmail.getLogemail()+") already exist in Trace No : "+clientmail.getTraceNo()+","; 
					}
					else if(clientphone != null)
					{
						description= "Row No : "+ k+ " -";
						description = description + " Phone number("+clientphone.getLogPhoneNo()+") already exist in Trace No : "+clientphone.getTraceNo()+","; 
	
					}
					else if(mobileNo != null)
					{
						description= "Row No : "+ k+ " -";
						description = description + " Mobile No("+mobileNo.getLogPhoneNo()+") already exist in Trace No : "+mobileNo.getTraceNo()+","; 
					}
					else if(altMobileNo != null)
					{
						description= "Row No : "+ k+ " -";
						description = description + " Alternate Phone number("+altMobileNo.getLogPhoneNo()+") already exist in Trace No : "+altMobileNo.getTraceNo()+","; 
					}
					else if(clientwebsite != null)
					{
						description= "Row No : "+ k+ " -";
						description = description + " Website("+clientwebsite.getLogwebsite()+") already exist in Trace No : "+clientwebsite.getTraceNo()+","; 

					}
					
					if(description != null && description.endsWith(","))
					{
						description = description.substring(0, description.length()-1);
					}
					
										
					//description = description + " already Exist!";
					clien.setLogDescription(description);
					insertLog(clien);
					logTracelist.add(clien);	
				}
				
				k++;
				
				//System.out.println("row.getRowNum() in dao "+row.getRowNum());
				if(row.getRowNum() >= rownum)
				{
					break;
					
				}
			}
			
			client.setLogTraceList(logTracelist);
			client.setClientList(clientlist);
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			String eMsg = e.getMessage();

			if (eMsg.contains("java.sql.SQLException")) {
				eMsg = eMsg.substring(eMsg.indexOf("java.sql.SQLException:"));
				eMsg = eMsg.replace("java.sql.SQLException:", "");
			} else if (eMsg.contains("nested exception is com.mysql.jdbc")) {
				eMsg = eMsg.substring(eMsg.indexOf("nested exception is com.mysql.jdbc"));
				eMsg = eMsg.replace("nested exception is com.mysql.jdbc", "");
			}

			if (eMsg != null && eMsg.contains("foreign key constraint fails")) {
				eMsg = eMsg.replace(".exceptions.jdbc4.MySQLIntegrityConstraintViolationException: ", "");
				eMsg = eMsg.substring(0, eMsg.indexOf(": a foreign key"));
				
				/*System.out.println("eMsg 2 "+eMsg);
				if (eMsg != null && !eMsg.equals("")) {
					String serialnum = eMsg.substring(eMsg.lastIndexOf("-"), eMsg.lastIndexOf("'"));
					eMsg = "";
				}*/
			}
			if (eMsg != null && eMsg.contains("FOREIGN KEY"))
			{
				eMsg = eMsg.substring(0, eMsg.indexOf("at row"));
			}
			String validationmsg = "Error while processing row " + k + ". " + eMsg;
			throw new Exception(validationmsg);
		}
		return client;

	}

	public List<CRMNotes> getNotesList(String roleId, String userid) throws Exception 
	{
		List<CRMNotes> notesList = new ArrayList<CRMNotes>();
		/*DateFormat originalFormat =new SimpleDateFormat("yyyy-MM-dd");
		DateFormat targetFormat =  new SimpleDateFormat("MM/dd/yyyy");*/
		
		String sql="";
		if(roleId != null && roleId.equals(AllzoneCRMConstants.TEAM_ROLE_ID))
		{
			sql = "SELECT * FROM azc_notes where user_id='"+userid+"'";
		}
		else
		{
			sql = "SELECT * FROM azc_notes ";
			
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) 
		{
			CRMNotes notes = new CRMNotes();
			String appointmentDate="";
			String appointmentTime="";
			String appointmentWith="";
			String note="";
			String traceNo="";
			String notesId="";
			String appointmentStatus="";
			String timeZone="";
			
			if(row.get("Appointment_Dt") != null && !row.get("Appointment_Dt").equals(""))
			{
				 appointmentDate = row.get("Appointment_Dt").toString();
			}
			if(row.get("Appointment_Time") != null && !row.get("Appointment_Time").equals(""))
			{
				 appointmentTime = row.get("Appointment_Time").toString();
				
			}
			if(row.get("Appointment_With") != null && !row.get("Appointment_With").equals(""))
			{
				appointmentWith = row.get("Appointment_With").toString();
			}
			if(row.get("notes") != null && !row.get("notes").equals(""))
			{
				note = row.get("notes").toString();
			}
			if(row.get("TraceNo") != null && !row.get("TraceNo").equals(""))
			{
				traceNo = row.get("TraceNo").toString();
			}
			if(row.get("Notes_Id") != null && !row.get("Notes_Id").equals(""))
			{
				notesId = row.get("Notes_Id").toString();
			}
			if(row.get("Appointment_Status") != null && !row.get("Appointment_Status").equals(""))
			{
				appointmentStatus = row.get("Appointment_Status").toString();
			}
			if(row.get("time_zone") != null && !row.get("time_zone").equals(""))
			{
				timeZone = row.get("time_zone").toString();
			}
			
			
			
			/*Date appointmentdt = null;
			String appointmentdate = "";
			if (appointmentDate != null && !appointmentDate.equals("")) 
			{
				appointmentdt = originalFormat.parse(appointmentDate);
				appointmentdate = targetFormat.format(appointmentdt);
			}*/
			
			notes.setAppointmentDate(appointmentDate);
			notes.setAppointmentTime(appointmentTime);
			notes.setAppointmentWith(appointmentWith);
			notes.setTraceNo(traceNo);
			notes.setNotes(note);
			notes.setNotesId(notesId);
			notes.setAppointmentStatus(appointmentStatus);
			notes.setTimezone(timeZone);

			notesList.add(notes);
		}

		return notesList;
		
	}

	public Client checkEmail(Client client) throws Exception 
	{		
		String mail="";
		String mail2 = "";
		String mail3 = "";
	
		if(client.getEmail() != null && !client.getEmail().equals(""))
		{
			String[] arrOfStr = client.getEmail().split("@", 0 );
		
			 for (String a : arrOfStr) 
			 {
				mail =arrOfStr[1]; 
				//mail = "@"+mail;
			 } 
		}
		if(client.getEmail2() != null && !client.getEmail2().equals(""))
		{
			String[] arrOfStr = client.getEmail2().split("@", 0 );
		
			 for (String a : arrOfStr) 
			 {
				 mail2 =arrOfStr[1]; 
				 //mail2 = "@"+mail2;
			 } 
		}
		if(client.getEmail3() != null && ! client.getEmail3().equals(""))
		{
			String[] arrOfStr =  client.getEmail3().split("@", 0 );
		
			 for (String a : arrOfStr) 
			 {
				 mail3 =arrOfStr[1]; 
				 //mail3 = "@"+mail3;
			 } 
		}
		
		Client clientmail = new Client();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql="";
		
		if(mail != null && !mail.equals(""))
		{ 
			if(!AllzoneCRMUtil.IsCommonDomain(mail)){
				sql= "select Client_Name,traceNo from azc_client where e_Mail Like '%"+mail+"%'  ";
				}else {
				sql= "select Client_Name,traceNo from azc_client where e_Mail = '"+client.getEmail()+"'  ";
			   }
				List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
						new Object[] {});
				String clientname = "";
				String traceNo = "";
				if(rows.size() > 0)
				{
					for (Map<String, Object> row : rows) 
					{
						clientname = row.get("Client_Name").toString();
						traceNo = row.get("traceNo").toString();
					}
					clientmail.setMailflag("1");
					clientmail.setTraceNo(traceNo);
					clientmail.setEmail(client.getEmail());
					clientmail.setLogemail(client.getEmail());
					clientmail.setEmaillogDescription("Email ID or Domain Name "+ client.getEmail() +" already Exists in "+ clientname + "!");
					return clientmail;
				}
				else 
				{
					if(mail2 != null && !mail2.equals(""))
					{
						if(!AllzoneCRMUtil.IsCommonDomain(mail2)){ sql= "select Client_Name,traceNo from azc_client where e_Mail Like '%"+mail2+"%'  ";
						}else 
						{
							sql= "select Client_Name,traceNo from azc_client where e_Mail = '"+client.getEmail2()+"'  ";
					     }
							rows = jdbcTemplate.queryForList(sql,new Object[] {});
							if(rows.size() > 0)
							{
								for (Map<String, Object> row : rows) 
								{
									clientname = row.get("Client_Name").toString();
									traceNo = row.get("traceNo").toString();
								}
								clientmail.setMailflag("1");	
								clientmail.setTraceNo(traceNo);
								clientmail.setEmail(client.getEmail2());
								clientmail.setLogemail(client.getEmail2());
								clientmail.setEmaillogDescription("Email ID or Domain Name "+ client.getEmail2() +" already Exists in "+ clientname + "!");
								return clientmail;
							}
							else 
							{
								if(mail3 != null && !mail3.equals(""))
								{
									if(!AllzoneCRMUtil.IsCommonDomain(mail3)){ sql= "select Client_Name,traceNo from azc_client where e_Mail Like '%"+mail3+"%'  ";
									}else 
									{
										sql= "select Client_Name,traceNo from azc_client where e_Mail = '"+client.getEmail3()+"'  ";
								     }
									rows = jdbcTemplate.queryForList(sql,new Object[] {});
										if(rows.size() > 0)
										{
											for (Map<String, Object> row : rows) 
											{
												clientname = row.get("Client_Name").toString();
												traceNo = row.get("traceNo").toString();
											}
											
											clientmail.setMailflag("1");
											clientmail.setTraceNo(traceNo);
											clientmail.setEmail(client.getEmail3());
											clientmail.setLogemail(client.getEmail3());
											clientmail.setEmaillogDescription("Email ID or Domain Name "+ client.getEmail3() +" already Exists in "+ clientname + "!");
											return clientmail;
										}
										else 
										{
											return null;
										}
								}
							}
					}
					return null;
				}
				
		}
		return null;
		
	}

	public List<Client> getClientAssignmentList(String roleid, String userid) throws Exception 
	{
		List<Client> clientassignmentlist = new ArrayList<Client>();
		
		String sql="";
		
		if(roleid != null && !roleid.equals(AllzoneCRMConstants.TEAM_ROLE_ID))
		{
			 sql = "SELECT  * FROM (select id, azc_client_assignment.TraceNo, azc_client.User_id, Client_Name, azc_client_assignment.from_user_id, First_Name from azc_client_assignment "
			 		+ "left join azc_client on azc_client_assignment.TraceNo = azc_client.TraceNo left join azc_user_master on " + 
			 		"azc_user_master.User_id = azc_client.User_id  ORDER BY id DESC) AS x GROUP BY TraceNo ";
		}
		else
		{
			sql = "SELECT  * FROM (select id, azc_client_assignment.TraceNo, azc_client.User_id,  Client_Name, First_Name from azc_client_assignment " + 
					"left join azc_client on azc_client_assignment.TraceNo = azc_client.TraceNo left join azc_user_master on " + 
					"azc_user_master.User_id = azc_client.User_id where azc_client.User_id = '"+userid+"'  ORDER BY id DESC) AS x GROUP BY TraceNo  ";
				
		}
		
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) 
		{                                                                                      
			Client client = new Client();
			
			client.setId(row.get("id").toString());
			client.setUserId(row.get("User_id").toString());
			client.setUsername(row.get("First_Name").toString());
			client.setClientName(row.get("Client_Name").toString());
			client.setTraceNo(row.get("TraceNo").toString());
			client.setFromuserid(row.get("from_user_id").toString());
			
			
			
			clientassignmentlist.add(client);
		}

		return clientassignmentlist;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void updateClientAssignment(Client client) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		String enddate  ="9999-12-31";
		
		System.out.println("client.getLoginName() "+ client.getLoginName());
		System.out.println("client.getHiddenuserid() "+ client.getHiddenuserid());

		System.out.println("client.getTraceNo() "+ client.getTraceNo());
		System.out.println("client.getId() "+ client.getId());
		System.out.println("client.getUsername() "+ client.getUsername());
		
		Date startdt = null;
		String startdate = "";

		if (currentDate != null && !currentDate.equals("")) 
		{
			startdt = targetFormat.parse(currentDate);
			startdate = originalFormat.format(startdt);
		}
		
		String previousenddate =AllzoneCRMUtil.subtractDate(startdate, -1);
		
		Date endtodt = null;
		String endtodate = "";
		if (previousenddate != null && !previousenddate.equals("")) 
		{
			endtodt = originalFormat.parse(previousenddate);
			endtodate = targetFormat.format(endtodt);
		}
		
		LocalDate todaydate = java.time.LocalDate.now();
		
		if (currentDate.compareTo(endtodate) >= 0) 
		{
		   endtodate = String.valueOf(todaydate);
		}
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql2 = "UPDATE azc_client_assignment set End_Dt=?, Modifed_By=?, Modifed_Dt = ? where to_user_id = ? and End_Dt =?";
		
		jdbcTemplate.update( sql2, new Object[] { endtodate, client.getLoginName(), currentDate, client.getHiddenuserid(), enddate});

		
		String sql = "INSERT INTO azc_client_assignment ( TraceNo, from_user_id, to_user_id, Start_Dt, End_Dt, Created_By, Created_Dt, Modifed_By, Modifed_Dt) "
				+ "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update( sql, new Object[] {client.getTraceNo(), client.getHiddenuserid(), client.getUsername(), currentDate, enddate,
				client.getLoginName(), currentDate , client.getLoginName(), currentDate});
		
		String sql1 = "UPDATE azc_client set User_id=?, Modified_By=?, Modified_Dt = ? where TraceNo = ?";
		
		jdbcTemplate.update( sql1, new Object[] { client.getUsername(), client.getLoginName(), currentDate, client.getTraceNo()});
		
		String notessql = "UPDATE azc_notes set user_id=?, Modified_By=?, Modified_Dt = ? where TraceNo = ? and Appointment_Dt >=?";
		
		jdbcTemplate.update( notessql, new Object[] { client.getUsername(), client.getLoginName(), currentDate, client.getTraceNo(), currentDate });
				
	}

	public Client getClientDetailsAssignemnt(String id, String traceNo) throws Exception 
	{
		List<Client> clientassignmentlist = new ArrayList<Client>();		
		
		String sql = "select id, Client_Name, azc_client_assignment.from_user_id, azc_client_assignment.to_user_id, azc_client_assignment.TraceNo, e_Mail,"
				+ " Phone_No from azc_client_assignment left join azc_client on azc_client_assignment.TraceNo = azc_client.TraceNo "
				+ "where azc_client_assignment.TraceNo= '"+traceNo+"' and id = '"+id+"' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) 
		{
			Client client = new Client();
			
			if(row.get("id") != null && !row.get("id").equals(""))
			{
				client.setId(row.get("id").toString());
			}
			if(row.get("Client_Name") != null && !row.get("Client_Name").equals(""))
			{
				client.setClientName(row.get("Client_Name").toString());
			}
			if(row.get("to_user_id") != null && !row.get("to_user_id").equals(""))
			{
				client.setUserId(row.get("to_user_id").toString());
			}
			if(row.get("TraceNo") != null && !row.get("TraceNo").equals(""))
			{
				client.setTraceNo(row.get("TraceNo").toString());
			}
			if(row.get("e_Mail") != null && !row.get("e_Mail").equals(""))
			{
				client.setEmail(row.get("e_Mail").toString());
			}
			if(row.get("Phone_No") != null && !row.get("Phone_No").equals(""))
			{
				client.setPhoneNumber(row.get("Phone_No").toString());
			}
			
			clientassignmentlist.add(client);
		}

	
		return clientassignmentlist.get(0);
	}

	public List<Client> getViewNotesList(String id) throws Exception 
	{
		List Noteslist = new ArrayList();
		String sql = "select azc_notes.*, azc_client.Client_Name, azc_status.Status_Desc, azc_buckets.bucket_name,azc_user_master.First_Name from azc_notes left join azc_client on " + 
				"azc_client.TraceNo = azc_notes.TraceNo left join azc_status on azc_status.status_id =azc_notes.status_id "
				+ " left join azc_buckets on azc_buckets.bucket_id =azc_notes.bucket_id left join azc_user_master on azc_user_master.user_id =azc_notes.user_id " + 
				 " where azc_notes.TraceNo = " +id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Noteslist = jdbcTemplate.query(sql, new CRMNotesRowMapper());

		return Noteslist;
	}

	public List<CRMNotes> getDashboardNotesList(String roleid, String userid) throws Exception 
	{
		List<CRMNotes> Noteslist = new ArrayList<CRMNotes>();
		// LocalDateTime currentTime = LocalDateTime.now();
		 //LocalDate date1 = currentTime.toLocalDate();
		 
		 String sql="";
		 if(roleid != null && !roleid.equals(AllzoneCRMConstants.TEAM_ROLE_ID))
		 {
			 sql = "select azc_notes.*, azc_client.Client_Name, azc_status.Status_Desc from azc_notes left join azc_client on " + 
			 		"azc_client.TraceNo = azc_notes.TraceNo left join azc_status on azc_status.status_id =azc_notes.status_id where "
			 		+ "azc_notes.Status_Id = '7'  " ;
		 }
		 else
		 {
			 sql = "select azc_notes.*, azc_client.Client_Name, azc_status.Status_Desc from azc_notes left join azc_client on " + 
			 		"azc_client.TraceNo = azc_notes.TraceNo left join azc_status on azc_status.status_id =azc_notes.status_id "
			 		+ "where azc_notes.Status_Id = '7' and azc_notes.user_id='"+userid+"' " ;
		 }
		 
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Noteslist = jdbcTemplate.query(sql, new CRMNotesRowMapper());
	
		return Noteslist;
	}

	public CRMNotes getNoteDetails(String id) throws Exception 
	{
		List<CRMNotes> noteslist = new ArrayList<CRMNotes>();		
		
		String sql = "select azc_notes.*, azc_client.Client_Name, azc_status.Status_Desc ,azc_buckets.bucket_name, azc_user_master.First_Name "
				+ "from azc_notes left join azc_client on azc_client.TraceNo = azc_notes.TraceNo "
				+ "left join azc_status on azc_status.status_id =azc_notes.status_id  "
				+ "left join azc_buckets on azc_buckets.status_id=azc_notes.status_id "
				+ "left join azc_user_master on azc_user_master.User_id=azc_notes.user_id "
				+ "where Notes_Id= "+id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		noteslist = jdbcTemplate.query(sql, new CRMNotesRowMapper());
	
		return noteslist.get(0);
	}

	public Client checkPhoneNo(Client client) throws Exception
	{
		//System.out.println("inside mobile number check");
		
		String phoneNo = client.getPhoneNumber().replaceAll("-", "");
		List<Client> clientList  = new ArrayList<Client>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql="select * from azc_client where Phone_No = '"+phoneNo+"' or mobile_no='"+phoneNo+"' or alt_phone_no = '"+phoneNo+"'";	
		
		//System.out.println("sql "+sql);
		
		clientList = jdbcTemplate.query(sql, new CheckClientRowMapper());
		
		if (clientList != null && clientList.size() > 0) 
		{
			for(int i=0; i<clientList.size(); i++)
			{
				Client clien = (clientList).get(i);
				client.setTraceNo(clien.getTraceNo());
				client.setUserId(clien.getUserId());
				client.setLogclientname(clien.getClientName());				
				client.setLogPhoneNo(client.getPhoneNumber());
			}
			client.setPhoneflag(1);	
			client.setLogDescription("Phone Number already Exists!");
			clientList.add(client);
			return client;
		}
		else 
		{
			return null;
		}
		
	}

	public Client checkClientName(Client client) throws Exception 
	{
		String clientname = client.getClientName();
		
		List<Client> clientList  = new ArrayList<Client>();
		String sql= "select * from azc_client where Client_Name=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		if(client.getFrompage()!=null && client.getFrompage().equals("editClient")) {
			 sql= sql+ " and TraceNo!='"+client.getTraceNo()+"'";
		}
		
		clientList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, clientname);
			}
		}, new CheckClientRowMapper());
		
		if (clientList != null && clientList.size() > 0) 
		{
			if(client.getFrompage()!="editClient") {
			for(int i=0; i<clientList.size(); i++){
					Client clien = (clientList).get(i);
					client.setTraceNo(clien.getTraceNo());
					client.setUserId(clien.getUserId());
					client.setLogclientname(clien.getClientName());
			}
			}
			client.setNameflag(1);
			client.setLogDescription("Client Name already Exists!");
			clientList.add(client);
			
			return client;
		}
		else 
		{
			return null;
		}
		
	}

	public Client checkWebsite(Client client) throws Exception 
	{
		String website = client.getWebsite();
		
		List<Client> clientList  = new ArrayList<Client>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql= "select * from azc_client where Website= ? ";
		
		clientList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, website);
			}
		}, new CheckClientRowMapper());
		
		if (clientList != null && clientList.size() > 0) 
		{
			for(int i=0; i<clientList.size(); i++)
			{
				Client clien = (clientList).get(i);
				client.setTraceNo(clien.getTraceNo());
				client.setUserId(clien.getUserId());
				client.setLogclientname(clien.getClientName());
				client.setLogwebsite(website);
			}
			client.setWebsiteflag(1);
			client.setLogDescription("Website already Exists!");
			clientList.add(client);

			return client;
		}
		else 
		{
			return null;
		}
	}

	public void insertLog(Client client) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
		String sqlinsert = "INSERT INTO azc_log "
			    + "(Log_Id, User_Id, Description, Upload_Dt, Created_By, Created_Dt) "
			    + "VALUES (?, ?, ?, ?, ?, ?)";

			 jdbcTemplate.update(sqlinsert, new Object[] { client.getLogId(), client.getUserId(), client.getLogDescription(), currentDate,
					 client.getLoginName(), currentDate });
	}

	public Client checkEditEmail(Client client) throws Exception 
	{
		String email = client.getEmail();
		String email2 = client.getEmail2();
		String email3 = client.getEmail3();
		
		String mail="";
		String mail2 = "";
		String mail3 = "";
		
		if(email != null && !email.equals(""))
		{
			String[] arrOfStr = email.split("@", 0 );
		
			 for (String a : arrOfStr) 
			 {
				mail =arrOfStr[1]; 
				//mail = "@"+mail;
			 } 

		}
		if(email2 != null && !email2.equals(""))
		{
			String[] arrOfStr = email2.split("@", 0 );
		
			 for (String a : arrOfStr) 
			 {
				 mail2 =arrOfStr[1]; 
				 //mail2 = "@"+mail2;
			 } 
		}
		if(email3 != null && !email3.equals(""))
		{
			String[] arrOfStr = email3.split("@", 0 );
		
			 for (String a : arrOfStr) 
			 {
				 mail3 =arrOfStr[1]; 
				 //mail3 = "@"+mail3;
			 } 
		}
		Client clientmail = new Client();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql="";
		//System.out.println("mail is"+ mail);
		
		if(mail != null && !mail.equals(""))
		{ 
			if(!AllzoneCRMUtil.IsCommonDomain(mail)){
				sql= "select Client_Name,traceNo from azc_client where e_Mail Like '%"+mail+"%' and TraceNo != '"+client.getTraceNo()+"'   ";
				}else {
				sql= "select Client_Name,traceNo from azc_client where e_Mail = '"+client.getEmail()+"' and TraceNo != '"+client.getTraceNo()+"'  ";
			    }
			
			//System.out.println("sql is"+ sql);
				List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
						new Object[] {});
				String clientname = "";
				String traceNo = "";
				if(rows.size() > 0)
				{
					for (Map<String, Object> row : rows) 
					{
						clientname = row.get("Client_Name").toString();
						traceNo = row.get("traceNo").toString();
					}
					clientmail.setMailflag("1");
					clientmail.setTraceNo(traceNo);
					clientmail.setEmail(mail);
					clientmail.setLogDescription("Email ID or Domain Name  " + client.getEmail() +" already Exists in "+ clientname + "!");
					return clientmail;
				}
				else 
				{
					if(mail2 != null && !mail2.equals(""))
					{
						if(!AllzoneCRMUtil.IsCommonDomain(mail2)){ sql= "select Client_Name,traceNo from azc_client where e_Mail Like '%"+mail2+"%'  and TraceNo != '"+client.getTraceNo()+"' ";
						}else {
							sql= "select Client_Name,traceNo from azc_client where e_Mail = '"+client.getEmail2()+"' and TraceNo != '"+client.getTraceNo()+"'  ";
					     }
						rows = jdbcTemplate.queryForList(sql,new Object[] {});
							if(rows.size() > 0)
							{
								for (Map<String, Object> row : rows) 
								{
									clientname = row.get("Client_Name").toString();
									traceNo = row.get("traceNo").toString();
								}
								clientmail.setMailflag("1");	
								clientmail.setTraceNo(traceNo);
								clientmail.setEmail(mail2);
								clientmail.setLogDescription("Email ID or Domain Name "  + client.getEmail2() +" already Exists in "+ clientname + "!");
								return clientmail;
							}
							else 
							{
								if(mail3 != null && !mail3.equals(""))
								{
									if(!AllzoneCRMUtil.IsCommonDomain(mail3)){ sql= "select Client_Name,traceNo from azc_client where e_Mail Like '%"+mail3+"%'  and TraceNo != '"+client.getTraceNo()+"' ";
									}else {
										sql= "select Client_Name,traceNo from azc_client where e_Mail = '"+client.getEmail3()+"' and TraceNo != '"+client.getTraceNo()+"'  ";
								    }
									rows = jdbcTemplate.queryForList(sql,new Object[] {});
										if(rows.size() > 0)
										{
											for (Map<String, Object> row : rows) 
											{
												clientname = row.get("Client_Name").toString();
												traceNo = row.get("traceNo").toString();
											}
											
											clientmail.setMailflag("1");
											clientmail.setTraceNo(traceNo);
											clientmail.setEmail(mail3);
											clientmail.setLogDescription("Email ID or Domain Name " + client.getEmail3() +" already Exists in "+ clientname + "!");
											return clientmail;
										}
										else 
										{
											return null;
										}
								}
							}
					}
					return null;
				}
				
		}
		return null;
		
	}

	public Client checkEditPhoneNo(Client client) throws Exception
	{	
		String mobileNo = client.getPhoneNumber().replaceAll("-", "");
		List<Client> clientList  = new ArrayList<Client>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql="select * from azc_client where (Phone_No = '"+mobileNo+"' or mobile_no='"+mobileNo+"' or "
				+ "alt_phone_no = '"+mobileNo+"') and TraceNo != '"+client.getTraceNo()+"' ";
	
		clientList = jdbcTemplate.query(sql, new CheckClientRowMapper());
		
		if (clientList != null && clientList.size() > 0) 
		{
			/*for(int i=0; i<clientList.size(); i++)
			{
				Client clien = (clientList).get(i);
				//client.setTraceNo(clien.getTraceNo());
				client.setUserId(clien.getUserId());
				client.setClientName(clien.getClientName());
			}*/
			client.setPhoneflag(1);	
			client.setNameflag(1);
			client.setUserId(clientList.get(0).getUserId());
			client.setClientName(clientList.get(0).getClientName());
			client.setLogDescription("Phone Number already Exists in trace no : "+clientList.get(0).getTraceNo());
			return client;
		}
		else 
		{
			return null;
		}
	}

	public Client checkEditClientName(Client client) throws Exception 
	{		
		List<Client> clientList  = new ArrayList<Client>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql= "select * from azc_client where Client_Name = ? and TraceNo != ?";
		
		clientList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, client.getClientName());
				ps.setString(2, client.getTraceNo());
			}
		}, new CheckClientRowMapper());
		
		if (clientList != null && clientList.size() > 0) 
		{
			/*for(int i=0; i<clientList.size(); i++)
			{
				Client clien = (clientList).get(i);
				//client.setTraceNo(clien.getTraceNo());
				client.setUserId(clien.getUserId());
				client.setClientName(clien.getClientName());
			}*/
			client.setNameflag(1);
			client.setUserId(clientList.get(0).getUserId());
			client.setClientName(clientList.get(0).getClientName());
			client.setLogDescription("Client Name already Exists in trace no : "+clientList.get(0).getTraceNo());
			
			return client;
		}
		else 
		{
			return null;
		}
	}


	public Client checkEditWebsite(Client client) throws Exception 
	{
		String website = client.getWebsite();
		
		List<Client> clientList  = new ArrayList<Client>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql= "select * from azc_client where Website=? and TraceNo != ? ";
		
		clientList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, website);
				ps.setString(2, client.getTraceNo());
			}
		}, new CheckClientRowMapper());
		
		if (clientList != null && clientList.size() > 0) 
		{
			/*for(int i=0; i<clientList.size(); i++)
			{
				Client clien = (clientList).get(i);
				//client.setTraceNo(clien.getTraceNo());
				client.setUserId(clien.getUserId());
				client.setClientName(clien.getClientName());
			}*/
			client.setWebsiteflag(1);
			client.setNameflag(1);
			client.setUserId(clientList.get(0).getUserId());
			client.setClientName(clientList.get(0).getClientName());
			client.setLogDescription("Website already Exists in trace no : "+clientList.get(0).getTraceNo());
			
			return client;
		}
		else 
		{
			return null;
		}
	}

	public List<Client> getDashboardfollowupList(String roleid, String userid) throws Exception 
	{
		List followupList = new ArrayList();
		
		 LocalDateTime currentTime = LocalDateTime.now();
		 LocalDate date1 = currentTime.toLocalDate();
		 
		 String sql="";
		 if(roleid != null && !roleid.equals(AllzoneCRMConstants.TEAM_ROLE_ID))
		 {
			 sql = "select azc_notes.*, azc_client.Client_Name, azc_status.Status_Desc, azc_buckets.bucket_name, "
			 		+ "azc_user_master.First_Name from azc_notes left join azc_client on " + 
			 		"azc_client.TraceNo = azc_notes.TraceNo left join azc_status on azc_status.status_id =azc_notes.status_id "
			 		+ " left join azc_buckets on azc_buckets.bucket_id =azc_notes.bucket_id "
			 		+ " left join azc_user_master on azc_user_master.User_id =azc_notes.user_id "
			 		+ " where azc_notes.Followup_Dt = '"+date1.toString()+"' " ;
		 }
		 else
		 {
			 sql = "select azc_notes.*, azc_client.Client_Name, azc_status.Status_Desc, azc_buckets.bucket_name, "
			 		+ " azc_user_master.First_Name from azc_notes left join azc_client on " + 
			 		"azc_client.TraceNo = azc_notes.TraceNo "
			 		+ "left join azc_status on azc_status.status_id =azc_notes.status_id "
			 		+ " left join azc_buckets on azc_buckets.bucket_id =azc_notes.bucket_id "
			 		+ " left join azc_user_master on azc_user_master.User_id =azc_notes.user_id "
			 		+ "where azc_notes.Followup_Dt = '"+date1.toString()+"' and azc_notes.user_id='"+userid+"' " ;
		 }

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		followupList = jdbcTemplate.query(sql, new CRMNotesRowMapper());
	
		return followupList;
	}

	public List<Client> getExceedFollowupList() throws Exception 
	{
		List<Client> followupList = new ArrayList<Client>();
		LocalDate date = LocalDate.now().minusDays(3);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		 String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar currentdt = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     currentdt.add(Calendar.YEAR,-1);
	     String endDate = f.format(currentdt.getTime());
		
		
		String sql = "select azc_client.*, azc_user_master.First_Name from azc_user_master  left join azc_client on azc_user_master.User_id= azc_client.User_id "
				+ "where azc_client.Modified_Dt <? and status_id =? and azc_client.Modified_Dt BETWEEN ? AND ?   " ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[] {date.toString(), 4, endDate,currentDate});
		for (Map<String, Object> row : rows) 
		{
			Client client = new Client();			
			client.setTraceNo(row.get("TraceNo").toString());
			client.setClientName(row.get("Client_Name").toString());
			client.setUsername(row.get("First_Name").toString());
			client.setStatusId(row.get("status_id").toString());
			//client.setModifiedDate(row.get("Modified_Dt").toString());
			
			followupList.add(client);
		}
		return followupList;
	}

	public List<Client> getExceedEmailList() throws Exception 
	{
		List<Client> emailList = new ArrayList<Client>();
		LocalDate date = LocalDate.now().minusDays(3);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		 String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar currentdt = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     currentdt.add(Calendar.YEAR,-1);
	     String endDate = f.format(currentdt.getTime());
		
		String sql = "select azc_client.*, azc_user_master.First_Name from azc_user_master  left join azc_client on azc_user_master.User_id= azc_client.User_id "
				+ "where azc_client.Modified_Dt <? and status_id =? and azc_client.Modified_Dt BETWEEN ? AND ?  and e_mail !='' " ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[] {date.toString(), 6, endDate,currentDate});
		for (Map<String, Object> row : rows) 
		{
			Client client = new Client();			
			client.setTraceNo(row.get("TraceNo").toString());
			client.setClientName(row.get("Client_Name").toString());
			client.setUsername(row.get("First_Name").toString());	
			client.setStatusId(row.get("status_id").toString());
			//client.setModifiedDate(row.get("Modified_Dt").toString());
			
			emailList.add(client);
		}
		
		return emailList;
	}

	public List<Client> getClientListsearch(Client client) throws Exception 
	{
		List<Client> clientlist = new ArrayList<Client>();
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String sql="";
		
		if(client.getRoleid() != null && !client.getRoleid().equals(AllzoneCRMConstants.TEAM_ROLE_ID))
		{
		
			sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name, azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, (Select azc_notes.Notes from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by " + 
					"Notes_Id desc limit 1) as notes, azc_user_master.First_Name,azc_buckets.bucket_name from azc_client left JOIN azc_countries ON azc_client.countries_id=azc_countries.id " + 
					"left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_user_master.User_id = azc_client.User_id left join azc_buckets on azc_buckets.bucket_id =azc_client.bucket_id " + 
					"where ";
		}
		else
		{
			sql = "SELECT azc_client.*,azc_countries.name , azc_states.name , azc_cities.name, azc_department.Dept_Name, azc_source.Source_Desc, " + 
					"azc_status.Status_Desc, (Select azc_notes.Notes from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by " + 
					"Notes_Id desc limit 1) as notes, azc_user_master.First_Name,azc_buckets.bucket_name from azc_client left JOIN azc_countries ON azc_client.countries_id=azc_countries.id  " + 
					"left join azc_states on azc_client.states_id = azc_states.id left join azc_cities " + 
					"on azc_cities.id = azc_client.cities_id left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
					"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
					"left join azc_user_master on azc_user_master.User_id = azc_client.User_id left join azc_buckets on azc_buckets.bucket_id =azc_client.bucket_id " + 
					"where azc_client.User_id = '"+client.getUserId()+"'  ";
		}
		
		if(client.getFromDate() != null && !client.getFromDate().equals("") 
				&& client.getToDate() != null && !client.getToDate().equals("") )
		{
			Date fromdt = null;
			String fromddate = "";					
			fromdt = originalFormat.parse(client.getFromDate());
			fromddate = targetFormat.format(fromdt);
			
			Date todt = null;
			String todate = "";					
			todt = originalFormat.parse(client.getToDate());
			todate = targetFormat.format(todt);
			
			if (sql.endsWith("where ")) 
			{
				sql= sql + " azc_client.Modified_Dt BETWEEN '"+fromddate+"' and '"+todate+"'  ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19') ";
			}
			else
			{
				sql=sql + " and azc_client.Modified_Dt BETWEEN '"+fromddate+"' and '"+todate+"' ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}

		}
		
		if(client.getStatus() != null && !client.getStatus().equals(""))
		{
			if (sql.endsWith("where ")) 
			{
				sql= sql + " azc_client.status_id = '"+client.getStatus()+"' ";// and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
			else
			{
				 sql= sql + " and azc_client.status_id = '"+client.getStatus()+"'  ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
		}
		
		if(client.getSearchbox() != null && client.getSearchbox().equals("traceNo") )
		{
			if (sql.endsWith("where ")) 
			{
				sql=sql + " azc_client.TraceNo='"+client.getTextbox()+"'  ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
			else
			{
				 sql=sql + " and azc_client.TraceNo='"+client.getTextbox()+"' ";//azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
		}

		if(client.getSearchbox() != null && client.getSearchbox().equals("clientName") )
		{
			if (sql.endsWith("where ")) 
			{
				 sql=sql + " azc_client.Client_Name LIKE '%"+client.getTextbox()+"%' ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
			else
			{
				sql=sql + " and azc_client.Client_Name LIKE '%"+client.getTextbox()+"%' ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
		}
		
		if(client.getSearchbox() != null && client.getSearchbox().equals("contactPerson") )
		{
			if (sql.endsWith("where ")) 
			{
				sql=sql + " azc_client.Contact_Person LIKE '%"+client.getTextbox()+"%' ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
			else
			{
				sql=sql + " and azc_client.Contact_Person LIKE '%"+client.getTextbox()+"%' ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
		}
		
		if(client.getSearchbox() != null && client.getSearchbox().equals("phoneNumber") )
		{
			if (sql.endsWith("where ")) 
			{
				 sql=sql + " azc_client.phone_no LIKE '%"+client.getTextbox()+"%' or mobile_no ='%"+client.getTextbox()+"%' or "
					 		+ "alt_phone_no = '%"+client.getTextbox()+"%' ";// and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
			else
			{
				sql=sql + " and azc_client.phone_no LIKE '%"+client.getTextbox()+"%' or mobile_no ='%"+client.getTextbox()+"%' or " + 
				 		"alt_phone_no = '%"+client.getTextbox()+"%' ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
		}
		
		if(client.getSearchbox() != null && client.getSearchbox().equals("email") )
		{
			if (sql.endsWith("where ")) 
			{
				sql=sql + " azc_client.e_mail LIKE '%"+client.getTextbox()+"%' ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
			else
			{
				sql=sql + "and azc_client.e_mail LIKE '%"+client.getTextbox()+"%' ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
		}
		
		if(client.getSearchbox() != null && client.getSearchbox().equals("website") )
		{
			if (sql.endsWith("where ")) 
			{
				sql=sql + " azc_client.website LIKE '%"+client.getTextbox()+"%' ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
			else
			{
				sql=sql + "and azc_client.website LIKE '%"+client.getTextbox()+"%' ";//and azc_client.status_id in('3','4','6','9', '10', '14','18','19')";
			}
		}
		
		sql = sql + " order by azc_client.traceno desc limit 500";
		
		System.out.println("sql is "+sql);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		clientlist = jdbcTemplate.query(sql, new CRMClientRowMapper());
		return clientlist;
		
	}

	public void updateNotes(CRMNotes notes) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		
		String sql = "UPDATE azc_notes  set  Appointment_Status = ?, Modified_By=?, Modified_Dt = ? where Notes_Id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update( sql, new Object[] { notes.getAppointmentStatus(), notes.getLoginName(), currentDate , notes.getNotesId()});
	}

	public List<CRMNotes> getCalendatNotesList(String roleid, String userid) throws Exception 
	{
		List<CRMNotes> notesList = new ArrayList<CRMNotes>();
		String sql="";
		
		if(roleid != null && roleid.equals("4"))
		{
			sql = "SELECT * FROM azc_notes where user_id='"+userid+"' and Status_Id in ('7') ";
		}
		else
		{
			sql = "SELECT * FROM azc_notes where Status_Id in ('7')  ";
			//System.out.println("sql "+sql);
			
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) 
		{
			CRMNotes notes = new CRMNotes();
			String appointmentDate="";
			String appointmentTime="";
			String appointmentWith="";
			String note="";
			String traceNo="";
			String notesId="";
			String appointmentStatus="";
			String timeZone="";
			String username="";
			
			if(row.get("Appointment_Dt") != null && !row.get("Appointment_Dt").equals(""))
			{
				 appointmentDate = row.get("Appointment_Dt").toString();
			}
			if(row.get("Appointment_Time") != null && !row.get("Appointment_Time").equals(""))
			{
				 appointmentTime = row.get("Appointment_Time").toString();
				
			}
			if(row.get("Appointment_With") != null && !row.get("Appointment_With").equals(""))
			{
				appointmentWith = row.get("Appointment_With").toString();
			}
			if(row.get("notes") != null && !row.get("notes").equals(""))
			{
				note = row.get("notes").toString();
			}
			if(row.get("TraceNo") != null && !row.get("TraceNo").equals(""))
			{
				traceNo = row.get("TraceNo").toString();
			}
			if(row.get("Notes_Id") != null && !row.get("Notes_Id").equals(""))
			{
				notesId = row.get("Notes_Id").toString();
			}
			if(row.get("Appointment_Status") != null && !row.get("Appointment_Status").equals(""))
			{
				appointmentStatus = row.get("Appointment_Status").toString();
			}
			if(row.get("time_zone") != null && !row.get("time_zone").equals(""))
			{
				timeZone = row.get("time_zone").toString();
			}
			if(row.get("Created_By") != null && !row.get("Created_By").equals(""))
			{
				username = row.get("Created_By").toString();
			}
			
			
			
			/*Date appointmentdt = null;
			String appointmentdate = "";
			if (appointmentDate != null && !appointmentDate.equals("")) 
			{
				appointmentdt = originalFormat.parse(appointmentDate);
				appointmentdate = targetFormat.format(appointmentdt);
			}*/
			
			notes.setAppointmentDate(appointmentDate);
			notes.setAppointmentTime(appointmentTime);
			notes.setAppointmentWith(appointmentWith);
			notes.setTraceNo(traceNo);
			notes.setNotes(note);
			notes.setNotesId(notesId);
			notes.setAppointmentStatus(appointmentStatus);
			notes.setTimezone(timeZone);
			notes.setLoginName(username);

			notesList.add(notes);
		}

		return notesList;
	}

	public Client checkMobileNo(Client client) throws Exception 
	{
		String mobileNo = client.getMobileNumber().replaceAll("-", "");
		List<Client> clientList  = new ArrayList<Client>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
		String sql="select * from azc_client where Phone_No = '"+mobileNo+"' or mobile_no='"+mobileNo+"' or alt_phone_no = '"+mobileNo+"'";
		
		clientList = jdbcTemplate.query(sql, new CheckClientRowMapper());
				
		if (clientList != null && clientList.size() > 0) 
		{
			for(int i=0; i<clientList.size(); i++)
			{
				Client clien = (clientList).get(i);
				client.setTraceNo(clien.getTraceNo());
				client.setUserId(clien.getUserId());
				client.setLogclientname(clien.getClientName());				
				client.setLogPhoneNo(clien.getMobileNumber());
			}
			client.setPhoneflag(2);	
			client.setLogDescription("Mobile Number already Exists!");
			clientList.add(client);
			return clientList.get(0);
		}
		else 
		{
			return null;
		}
	}

	public Client checkaltMobileNo(Client client) throws Exception 
	{
		String altphoneNo = client.getAlternateMobileNumber().replaceAll("-", "");
		List<Client> clientList  = new ArrayList<Client>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
		String sql="select * from azc_client where Phone_No = '"+altphoneNo+"' or mobile_no='"+altphoneNo+"' or "
				+ "alt_phone_no = '"+altphoneNo+"'";	
				
		clientList = jdbcTemplate.query(sql, new CheckClientRowMapper());
				
		if (clientList != null && clientList.size() > 0) 
		{
			for(int i=0; i<clientList.size(); i++)
			{
				Client clien = (clientList).get(i);
				client.setTraceNo(clien.getTraceNo());
				client.setUserId(clien.getUserId());
				client.setLogclientname(clien.getClientName());				
				client.setLogPhoneNo(clien.getAlternateMobileNumber());
			}
			client.setPhoneflag(3);	
			client.setLogDescription("Alternate Mobile Number already Exists!");
			clientList.add(client);
			return clientList.get(0);
		}
		else 
		{
			return null;
		}
	}

	public Client checkEditMobileNo(Client client) throws Exception 
	{
		String mobileNo = client.getMobileNumber().replaceAll("-", "");
		List<Client> clientList  = new ArrayList<Client>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql="select * from azc_client where( Phone_No = '"+mobileNo+"' or mobile_no='"+mobileNo+"' or "
				+ "alt_phone_no = '"+mobileNo+"' ) and TraceNo != '"+client.getTraceNo()+"' ";
		
		clientList = jdbcTemplate.query(sql, new CheckClientRowMapper());
		
		if (clientList != null && clientList.size() > 0) 
		{
			for(int i=0; i<clientList.size(); i++)
			{
				Client clien = (clientList).get(i);
				//client.setTraceNo(clien.getTraceNo());
				client.setUserId(clien.getUserId());
				client.setClientName(clien.getClientName());
			}
			client.setPhoneflag(2);	
			client.setLogDescription("Mobile Number already Exists!");
			return clientList.get(0);
		}
		else 
		{
			return null;
		}
	}

	public Client checkEditAltMobileNo(Client client) throws Exception 
	{
		String altmobileNo = client.getAlternateMobileNumber().replaceAll("-", "");
		List<Client> clientList  = new ArrayList<Client>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql="select * from azc_client where (Phone_No = '"+altmobileNo+"' or mobile_no='"+altmobileNo+"' "
				+ "or alt_phone_no = '"+altmobileNo+"' ) and  TraceNo != '"+client.getTraceNo()+"' ";
		
		clientList = jdbcTemplate.query(sql, new CheckClientRowMapper());
		
		if (clientList != null && clientList.size() > 0) 
		{
			for(int i=0; i<clientList.size(); i++)
			{
				Client clien = (clientList).get(i);
				//client.setTraceNo(clien.getTraceNo());
				client.setUserId(clien.getUserId());
				client.setClientName(clien.getClientName());
			}
			client.setPhoneflag(3);	
			client.setLogDescription("Alternate Mobile Number already Exists!");
			return clientList.get(0);
		}
		else 
		{
			return null;
		}
	}

	public LinkedHashMap<String, String> getdeptHashMap() throws Exception 
	{
		LinkedHashMap<String, String> departmentHashmap = new LinkedHashMap<>();
		
		String sql = "Select * from azc_department where Dept_Name != 'ALL' and Status='A' ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) 
		{
			String deptName = row.get("Dept_Code").toString();
			String deptId = row.get("dept_id").toString();
			departmentHashmap.put(deptName, deptId);
		}
		return departmentHashmap;
	}

	public LinkedHashMap<String, String> getUserHashMap() throws Exception 
	{
		LinkedHashMap<String, String> userHashmap = new LinkedHashMap<>();
		
		String sql = "Select * from azc_user_master where user_status = 'A' ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) 
		{
			String userName = row.get("Login_Name").toString();
			String userId = row.get("User_id").toString();
			userHashmap.put(userName, userId);
		}
		return userHashmap;
	}

	public LinkedHashMap<String, String> getEventHashMap() throws Exception 
	{
		LinkedHashMap<String, String> eventHashmap = new LinkedHashMap<>();
		
		String sql = "Select * from azc_events ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) 
		{
			String eventName = row.get("Event_Code").toString();
			String eventId = row.get("event_id").toString();
			eventHashmap.put(eventName, eventId);
		}
		return eventHashmap;
	}

	@Override
	public LinkedHashMap<String, String> getServicesHashMap() throws Exception 
	{
		LinkedHashMap<String, String> servicesHashmap = new LinkedHashMap<>();
		
		String sql = "Select * from azc_services ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) 
		{
			String servicesDescription = row.get("services_desc").toString();
			String servicesId = row.get("services_id").toString();
			servicesHashmap.put(servicesDescription, servicesId);
		}
		return servicesHashmap;
	}
	
	public LinkedHashMap<String, String> getSourceHashMap() throws Exception 
	{
		LinkedHashMap<String, String> sourceHashmap = new LinkedHashMap<>();
		
		String sql = "Select * from azc_source where Source_Desc != 'ALL' and Status='A' ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) 
		{
			String sourceName = row.get("Source_Desc").toString();
			String sourceId = row.get("source_id").toString();
			sourceHashmap.put(sourceName, sourceId);
		}
		return sourceHashmap;
	}

	//Notes closed Status for submit button.
	public String getStatusId(String traceNo) throws Exception {
		String sql = "SELECT status_id  FROM azc_client where TraceNo="+traceNo;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		String statusid=null;
		for (Map<String, Object> row : rows){
			statusid = row.get("status_id").toString();
			}
		return statusid;
	}

	@Override
	public List<Client> findClientByTraceNoOnGroupNotes(String traceNo) {
List<Client> clientlist = new ArrayList<Client>();		
		
		String sql = "SELECT azc_client.*,azc_countries.name , azc_department.Dept_Name, azc_source.Source_Desc, " + 
				"azc_status.Status_Desc, (Select azc_notes.Notes from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by " + 
				"Notes_Id desc limit 1) as notes, azc_user_master.First_Name,azc_buckets.bucket_name from azc_client "
				+ "left JOIN azc_countries ON azc_client.countries_id=azc_countries.id " + 
				"left join azc_department on azc_department.dept_id = azc_client.Dept_Id " + 
				"left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id " + 
				"left join azc_user_master on azc_user_master.User_id = azc_client.User_id "
				+ "left join azc_buckets on azc_buckets.bucket_id =azc_client.bucket_id where azc_client.Status_Id !=14  and azc_client.TraceNo= " + traceNo;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		clientlist = jdbcTemplate.query(sql, new CRMClientRowMapper());
		
	  return clientlist;
	}
	
	
	@Override
	public List<Client> findClientByTraceNo(String traceNo) {
List<Client> clientlist = new ArrayList<Client>();		
		
		String sql = "SELECT azc_client.*,azc_countries.name , azc_department.Dept_Name, azc_source.Source_Desc, \r\n" + 
				"				azc_status.Status_Desc, (Select azc_notes.Notes from allzone_crm.azc_notes where azc_notes.TraceNo = azc_client.TraceNo order by  \r\n" + 
				"				Notes_Id desc limit 1) as notes, azc_user_master.First_Name,azc_buckets.bucket_name from azc_client \r\n" + 
				"				left JOIN azc_countries ON azc_client.countries_id=azc_countries.id  \r\n" + 
				"				left join azc_department on azc_department.dept_id = azc_client.Dept_Id \r\n" + 
				"				left join azc_source on azc_source.source_id = azc_client.Source_Id left join azc_status on azc_status.status_id = azc_client.Status_Id \r\n" + 
				"				left join azc_user_master on azc_user_master.User_id = azc_client.User_id \r\n" + 
				"                left join azc_client_assignment on  azc_client.TraceNo = azc_client_assignment.TraceNo \r\n" + 
				"				left join azc_buckets on azc_buckets.bucket_id =azc_client.bucket_id where azc_client.Status_Id !=14  \r\n" + 
				"                and azc_client_assignment.TraceNo =" + traceNo +" GROUP BY TraceNo" ;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		clientlist = jdbcTemplate.query(sql, new CRMClientRowMapper());
		
	  return clientlist;
	}

	@Override
	public void deleteClientByTraceNo(String traceNo) {
		
		String sql1 = "DELETE FROM azc_notes WHERE TraceNo = ?";
		String sql2 = "DELETE FROM azc_client_assignment WHERE TraceNo = ?";
		String sql3 = "DELETE FROM azc_client WHERE TraceNo = ?";
		
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update( sql1, new Object[] {traceNo});
		jdbcTemplate.update( sql2, new Object[] {traceNo});
		jdbcTemplate.update( sql3, new Object[] {traceNo});
		
		
	}

	@Override
	public void updateClientNotesandDT(CRMNotes notes) throws Exception {
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date notesdt = null;
		String notesdate = "";
		if (notes.getNotesDate() != null && !notes.getNotesDate().equals("")) 
		{
			notesdt = originalFormat.parse(notes.getNotesDate());
			notesdate = targetFormat.format(notesdt);
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql1 = "UPDATE azc_client set Notes = ? , NotesDt = ?  where TraceNo = ?";
		jdbcTemplate.update(sql1, new Object[] { notes.getNotes(),notesdate,notes.getTraceNo() });
	}
	
	
	
	
}
