package com.soarcrm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.soarcrm.domain.CRMEmail;
import com.soarcrm.domain.CRMMainmenu;
import com.soarcrm.domain.CRMRolemaster;
import com.soarcrm.domain.CRMSubmenu;
import com.soarcrm.domain.CRMUser;
import com.soarcrm.jdbc.CRMUserRowMapper;
import com.soarcrm.util.AllzoneCRMUtil;


public class CRMUserDaoImpl implements CRMUserDao 
{
	@Autowired
	DataSource dataSource;
	@Autowired
	DataSource mdbdataSource;
	@Autowired
	DataSource sqlserverdataSource;

	public CRMUser isValidUser(String loginName, String password1) throws Exception 
	{
		CRMUser user = null;
		List<CRMUser> userlist = new ArrayList<CRMUser>();
	
		String query = "Select * from azc_user_master where BINARY Login_Name ='"+loginName+"' and BINARY Password = '"+password1+"' ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userlist = jdbcTemplate.query(query, new Object[]{ }, new CRMUserRowMapper());
		
		if(userlist != null && userlist.size() > 0)
		{
			user = userlist.get(0);
		}
		
		return user;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void insertUser(CRMUser user) throws Exception 
	{
		try
		{
			String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");			
			String status = "A";
			
			/*if(user.getUserType().equals("Employee"))
			{
				user.setUserType("E");
			}			
			if(user.getUserType().equals("Admin"))
			{
				user.setUserType("A");
			}*/
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		
			String sql = "INSERT INTO azc_user_master "
			    + "(role_id, First_Name, Last_Name, Login_Name, Password, password2,  Email_Id, employee_id, user_status,  "
			    + "Created_By,  Created_Dt) "
			    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	
			 jdbcTemplate.update(sql, new Object[] {user.getRoleId(), user.getFirstName(), user.getLastName(), user.getLoginName(),
					 user.getUserpassword(),  user.getHighSecurityPassword(), user.getEmailId(), user.getEmployeeId(), 
					 status, user.getUserLoginName(),  currentDate});
			 
			 String sqlselect = "SELECT User_Id FROM azc_user_master group by User_Id desc LIMIT 1";
				
				List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlselect);
				String userid="";
				for (Map<String, Object> row : rows) 
				{
					userid = row.get("User_Id").toString();
				}
				
				String[] deptId = null;
				if(user.getDepartmentId() != null && !user.getDepartmentId().equals(""))
				{
					deptId = user.getDepartmentId().split(",");
				}
			
			//System.out.println("deptId "+deptId);
			
			if (deptId != null && deptId.length > 0) 
			{
				for (int i = 0; i < deptId.length; i++) 
				{
						String departmentid = deptId[i];
						
						String deptsql = "INSERT INTO azc_userdept_mapping "
							    + "(User_id, dept_id, Created_By, Created_Dt) "
							    + "VALUES (?, ?, ?, ?)";
				
						jdbcTemplate.update(deptsql, new Object[] {userid, departmentid, user.getUserLoginName(), currentDate});
				}
			}
			 
			 String sql1 = "INSERT INTO azc_role_mapping "
					    + "(User_id, role_id, Created_By,  Created_Dt, Modified_By, Modified_Dt) "
					    + "VALUES (?, ?, ?, ?, ?, ?)";
			
			jdbcTemplate.update(sql1, new Object[] { userid, user.getRoleId(), user.getUserLoginName(),  currentDate,
					user.getUserLoginName(),  currentDate});
		}
		 catch(Exception e)
		 {
			e.printStackTrace();
			String eMsg = e.getMessage();
				
			if(eMsg != null && eMsg.contains("Duplicate entry"))
			{
				eMsg = eMsg.replace(".exceptions.jdbc4.MySQLIntegrityConstraintViolationException: ", "");
				eMsg = eMsg.substring(0, eMsg.indexOf("for key "));
				
				
				if(eMsg != null && !eMsg.equals(""))
				{
					String serialnum = eMsg.substring((eMsg.indexOf("'")+1), eMsg.lastIndexOf("'"));
					eMsg = "Duplicate Login Name " + serialnum;
				}
			}
			String validationmsg = "Error while processing "+ eMsg;
			
			throw new Exception(validationmsg);
		}
	}

	public List<CRMUser> getuserlist() throws Exception 
	{
		List<CRMUser> userList = new ArrayList<CRMUser>();
		
		String sql = "select * from azc_user_master where user_status='A' order by First_Name";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userList = jdbcTemplate.query(sql, new CRMUserRowMapper());

		return userList;
	}
	
	public CRMUser getUser(String id) throws Exception 
	{
		List<CRMUser> userlist = new ArrayList<CRMUser>();		
		
		String sql = "select * from azc_user_master where User_id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userlist = jdbcTemplate.query(sql, new CRMUserRowMapper());
	
		return userlist.get(0);
	}

	public void updateUser(CRMUser user) throws Exception 
	{	
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		
		/*if(user.getUserType().equals("Employee"))
		{
			user.setUserType("E");
		}			
		if(user.getUserType().equals("Admin"))
		{
			user.setUserType("A");
		}*/
		
	String sql = "UPDATE azc_user_master set role_id=?, First_Name = ?, Last_Name = ?, Login_Name = ?, Password = ?, password2 = ?, "
			+ "Email_Id = ?, employee_id=?, "
			+" Modifed_By = ?, Modified_Dt = ? where User_id = ?";
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	jdbcTemplate.update( sql, new Object[] {user.getRoleId(), user.getFirstName(), user.getLastName(), user.getLoginName(), user.getUserpassword(), 
			user.getHighSecurityPassword(), user.getEmailId(), user.getEmployeeId(), user.getUserLoginName(), currentDate, user.getUserId()});
	
	if(!user.getRoleId().equals(user.getHiddenroleid()))
	{
		 String sql1 = "INSERT INTO azc_role_mapping "
				    + "(User_id, role_id, Created_By,  Created_Dt, Modified_By, Modified_Dt) "
				    + "VALUES (?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql1, new Object[] {user.getUserId(), user.getRoleId(), user.getUserLoginName(),  currentDate,
				user.getUserLoginName(),  currentDate});
	}
	}
	
	public List<CRMMainmenu> getMainMenuList(String roleid) throws Exception 
	{
		List<CRMMainmenu> mainMenuList = new ArrayList<CRMMainmenu>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String selectsql = "SELECT * from azc_menurole_mapping left join azc_mainmenu on azc_menurole_mapping.MainMenu_Id = azc_mainmenu.MainMenu_Id where "
					+ "Role_id = '"+roleid+"' && azc_menurole_mapping.MainMenu_Id!= '4' group by azc_menurole_mapping.MainMenu_Id" ;
			
		List<Map<String, Object>> prows = jdbcTemplate.queryForList(selectsql,
				new Object[] {});

		for (Map<String, Object> row : prows) 
		{
			CRMMainmenu mainmenu = new CRMMainmenu();
			
			String menuid = row.get("MainMenu_Id").toString();
			String MenuName = row.get("MenuName").toString();
			String hrefname = row.get("href_name").toString();
			String classname = row.get("class_name").toString();
			
			mainmenu.setMainMenuId(menuid);
			mainmenu.setMainMenuName(MenuName);
			mainmenu.setMainMenuhref(hrefname);
			mainmenu.setMainMenuClassName(classname);
			
			mainMenuList.add(mainmenu);
		}
		return mainMenuList;
	}

	public List<CRMSubmenu> getSubMenuList(String menuid, String roleid) throws Exception 
	{
		List<CRMSubmenu> subMenuList = new ArrayList<CRMSubmenu>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String selectsql = "SELECT * from azc_submenu left join azc_menurole_mapping on azc_menurole_mapping.submenu_id = azc_submenu.submenu_id where " + 
				"Role_id = '"+roleid+"' and  azc_menurole_mapping.MainMenu_Id ='"+menuid+"' ";
		
		List<Map<String, Object>> prows = jdbcTemplate.queryForList(selectsql,
				new Object[] {});

		for (Map<String, Object> row : prows) 
		{
			CRMSubmenu submenu = new CRMSubmenu();
			
			String submenuid = row.get("SubMenu_Id").toString();
			String MenuName = row.get("SubMenuName").toString();
			String mainmenuid = row.get("MainMenu_Id").toString();
			String hrefname = row.get("href_name").toString();
			
			submenu.setSubMenuId(submenuid);
			submenu.setSubMenuName(MenuName);
			submenu.setSubMenuhref(hrefname);
			
			
			subMenuList.add(submenu);
		}
		return subMenuList;
	}

	public List<CRMRolemaster> getRolelist() throws Exception
	{
		List<CRMRolemaster> roleList = new ArrayList<CRMRolemaster>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String selectsql = "select * from azc_role_master";
		
		List<Map<String, Object>> prows = jdbcTemplate.queryForList(selectsql, new Object[] {});

		for (Map<String, Object> row : prows) 
		{
			CRMRolemaster user = new CRMRolemaster();
			
			user.setRoleId(row.get("role_id").toString());
			user.setRoleName(row.get("role_name").toString());			
			
			roleList.add(user);
		}
		return roleList;
	}

	public CRMUser isValidSuperUser(String loginName, String password, String password2) throws Exception 
	{
		CRMUser user = null;
		List<CRMUser> userlist = new ArrayList<CRMUser>();
	
		String query = "Select * from azc_user_master where BINARY Login_Name ='"+loginName+"' and BINARY Password = '"+password+"' and "
				+ "BINARY password2 ='"+password2+"' and user_status='A' ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userlist = jdbcTemplate.query(query, new Object[]{ }, new CRMUserRowMapper());
		
		if(userlist != null && userlist.size() > 0)
		{
			user = userlist.get(0);
		}
		
		return user;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public boolean insertExistingClient() throws Exception {
		
		boolean datainserted = false;
		
		try
		{
			String mbdsql = "select client_trace_no, client_company_name, client_conPerson, client_address, client_city, client_state, "
					+ " client_zip, client_country, client_phone, client_email, client_fax, client_website, client_enquire_date,"
					+ " client_source, client_dept, UserName, duplicate, notes_status,Conference from client where client_dept in(2,3,4)";
			
			/*String mbdsql = "select client_trace_no, client_company_name, client_conPerson, client_address, client_city, client_state, "
					+ " client_zip, client_country, client_phone, client_email, client_fax, client_website, client_request, client_enquire_date,"
					+ " client_source, client_source_details, client_dept, notes, notes_date, notes_status, notes_status_date, "
					+ "appnmtDate, appnmtTime,appSession,appZone,appStatus,appNotes,appClosedNotes,appClosedFollowupDate,appClosedStatus,"
					+ "No_of_Beds, UserName, duplicate, Conference from client";*/
			
			//System.out.println("mbdsql "+ mbdsql);
	
			JdbcTemplate jdbcTemplate1 = new JdbcTemplate(sqlserverdataSource);
	
			List<Map<String, Object>> mdbrows = jdbcTemplate1.queryForList(mbdsql, new Object[] {});
	
			System.out.println("mdbrows size is "+ mdbrows.size());
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			int insertcount = 0;
			
			for (Map<String, Object> row : mdbrows) {
				String userid = "0";
				String traceno = "";
				if( row.get("client_trace_no") != null)
				{
					traceno = row.get("client_trace_no").toString();
				}
				String clientname = "";
				if( row.get("client_company_name") != null)
				{
					clientname = row.get("client_company_name").toString();
				}
				String contactperson = "";
				if( row.get("client_conPerson") != null)
				{
					contactperson = row.get("client_conPerson").toString();
				}
				String address = "";
				if( row.get("client_address") != null)
				{
					address = row.get("client_address").toString();
				}
				
				String city = "";
				if( row.get("client_city") != null)
				{
					city = row.get("client_city").toString();
				}
				String state = "";
				if( row.get("client_state") != null)
				{
					state = row.get("client_state").toString();
				}
				
				String zip = "";
				if( row.get("client_zip") != null)
				{
					zip = row.get("client_zip").toString();
				}
				String country = "";
				if( row.get("client_country") != null)
				{
					country = row.get("client_country").toString();
				}
				
				String phone = "";
				if( row.get("client_phone") != null)
				{
					phone = row.get("client_phone").toString();
				}
				String email = "";
				if( row.get("client_email") != null)
				{
					email = row.get("client_email").toString();
				}
				String fax = "";
				if( row.get("client_fax") != null)
				{
					fax = row.get("client_fax").toString();
				}
				String website = "";
				if( row.get("client_website") != null)
				{
					website = row.get("client_website").toString();
				}
				
				String clientrequest = "";
				if( row.get("client_request") != null)
				{
					clientrequest = row.get("client_request").toString();
				}
				
				String enqdate = "";
				if( row.get("client_enquire_date") != null)
				{
					enqdate = row.get("client_enquire_date").toString();
				}
				String source = "";
				if( row.get("client_source") != null)
				{
					source = row.get("client_source").toString();
				}
				String sourcedetails = "";
				if( row.get("client_source_details") != null)
				{
					sourcedetails = row.get("client_source_details").toString();
				}
				String dept = "";
				if( row.get("client_dept") != null)
				{
					dept = row.get("client_dept").toString();
				}
				String notes = "";
				if( row.get("notes") != null)
				{
					notes = row.get("notes").toString();
				}
				String notesdate = null;
				if( row.get("notes_date") != null)
				{
					notesdate = row.get("notes_date").toString();
				}
				String notesstatus = "";
				if( row.get("notes_status") != null)
				{
					notesstatus = row.get("notes_status").toString();
				}
				String notesstatusdate = null;
				if( row.get("notes_status_date") != null)
				{
					notesstatusdate = row.get("notes_status_date").toString();
				}
				
				String appnmtDate = null;
				if( row.get("appnmtDate") != null)
				{
					appnmtDate = row.get("appnmtDate").toString();
				}
				String appnmtTime = null;
				if( row.get("null") != null)
				{
					appnmtTime = row.get("appnmtTime").toString();
				}
				String appSession = "";
				if( row.get("appSession") != null)
				{
					appSession = row.get("appSession").toString();
				}
				String appZone = "";
				if( row.get("appZone") != null)
				{
					appZone = row.get("appZone").toString();
				}
				String appStatus = "";
				if( row.get("appStatus") != null)
				{
					appStatus = row.get("appStatus").toString();
				}
				String appNotes = "";
				if( row.get("appNotes") != null)
				{
					appNotes = row.get("appNotes").toString();
				}
				String appClosedNotes = "";
				if( row.get("appClosedNotes") != null)
				{
					appClosedNotes = row.get("appClosedNotes").toString();
				}
				String appClosedFollowupDate = null;
				if( row.get("appClosedFollowupDate") != null)
				{
					appClosedFollowupDate = row.get("appClosedFollowupDate").toString();
				}
				String appClosedStatus = "";
				if( row.get("appClosedStatus") != null)
				{
					appClosedStatus = row.get("appClosedStatus").toString();
				}
				
				String noofbeds = "0";
				if( row.get("No_of_Beds") != null)
				{
					noofbeds = row.get("No_of_Beds").toString();
				}
				
				if( row.get("UserName") != null)
				{
					userid = row.get("UserName").toString();
				}
				String duplicate = "";
				if( row.get("duplicate") != null)
				{
					duplicate = row.get("duplicate").toString();
				}
				String conference = "";
				if( row.get("Conference") != null)
				{
					conference = row.get("Conference").toString();
				}
				//website = website.s

				System.out.println("notesstatus "+ notesstatus);
				
				if(contactperson !=null && !contactperson.equals("-"))
				{
					if(contactperson.length() > 100)
					{
						contactperson = contactperson.substring(0,99);
					}
				}
				

				//String notesdate = enqdate;
			
				
				String sql = "INSERT INTO azc_client "
						+ "(TraceNo, Client_Name, Contact_Person, Address, City, State, zip, Country, "
						+ "Phone_No, e_Mail, Fax, Website, Created_Dt,Source_Id, Dept_Id, User_id, Is_Duplicate,status_id,event_id, Modified_Dt,Created_By)"
						+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				/*String sql = "INSERT INTO client (client_trace_no,client_company_name,client_conPerson,client_address,"
						+ " client_city,client_state,client_zip,client_country,client_phone,client_email,client_fax,client_website,"
						+ " client_request, client_enquire_date,client_source,client_source_details,client_dept,notes,"
						+ " notes_date, notes_status, notes_status_date, appnmtDate, appnmtTime, appSession, appZone, "
						+ " appStatus, appNotes, appClosedNotes,appClosedFollowupDate,appClosedStatus,No_of_Beds,UserName,duplicate,Conference)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
						   
						
				jdbcTemplate.update(sql, new Object[] {traceno,clientname,contactperson,address,city,state,zip,country,
						phone,email,fax,website, enqdate,source,
						dept,userid,duplicate,notesstatus,conference,notesdate, "Migration"});
				
				insertcount++;
				
			}
			
			System.out.println("insertcount "+ insertcount);
			
			if(insertcount == mdbrows.size())
			{
				datainserted = true;
				System.out.println("all data inserted successfully");
			}
		}
		catch (Exception e) {
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
				//eMsg = eMsg.substring(0, eMsg.indexOf("for key "));
	
				if (eMsg != null && !eMsg.equals("")) {
					//String serialnum = eMsg.substring(eMsg.lastIndexOf("-"), eMsg.lastIndexOf("'"));
					//eMsg = "Employee code at row " +k + " doesn't exist in Payroll System. Please change and upload again.";
				}
			}
			/*if (eMsg != null && eMsg.contains("FOREIGN KEY")) {
				System.out.println("inside second if");
				eMsg = eMsg.substring(0, eMsg.indexOf("at row"));
			}*/
			//String validationmsg = "Error while processing row " + k + ". " + eMsg;
			throw new Exception(eMsg);
		}
		
		return datainserted;
	} 
	
	/*@Transactional(rollbackFor = Exception.class)
	public boolean insertExistingClient() throws Exception {
		
		boolean datainserted = false;
		
		try
		{
	
			
			String mbdsql = "select client_trace_no, client_company_name, client_conPerson, client_address, client_city, client_state, "
					+ " client_zip, client_country, client_phone, client_email, client_fax, client_website, client_request, client_enquire_date,"
					+ " client_source, client_source_details, client_dept, notes, notes_date, notes_status, notes_status_date, "
					+ "appnmtDate, appnmtTime,appSession,appZone,appStatus,appNotes,appClosedNotes,appClosedFollowupDate,appClosedStatus,"
					+ "No_of_Beds, UserName, duplicate, Conference from client";
			
			//System.out.println("mbdsql "+ mbdsql);
	
			JdbcTemplate jdbcTemplate1 = new JdbcTemplate(sqlserverdataSource);
	
			List<Map<String, Object>> mdbrows = jdbcTemplate1.queryForList(mbdsql, new Object[] {});
	
			System.out.println("mdbrows size is "+ mdbrows.size());
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			int insertcount = 0;
			
			for (Map<String, Object> row : mdbrows) {
				String userid = "0";
				String traceno = "";
				if( row.get("client_trace_no") != null)
				{
					traceno = row.get("client_trace_no").toString();
				}
				String clientname = "";
				if( row.get("client_company_name") != null)
				{
					clientname = row.get("client_company_name").toString();
				}
				String contactperson = "";
				if( row.get("client_conPerson") != null)
				{
					contactperson = row.get("client_conPerson").toString();
				}
				String address = "";
				if( row.get("client_address") != null)
				{
					address = row.get("client_address").toString();
				}
				
				String city = "";
				if( row.get("client_city") != null)
				{
					city = row.get("client_city").toString();
				}
				String state = "";
				if( row.get("client_state") != null)
				{
					state = row.get("client_state").toString();
				}
				
				String zip = "";
				if( row.get("client_zip") != null)
				{
					zip = row.get("client_zip").toString();
				}
				String country = "";
				if( row.get("client_country") != null)
				{
					country = row.get("client_country").toString();
				}
				
				String phone = "";
				if( row.get("client_phone") != null)
				{
					phone = row.get("client_phone").toString();
				}
				String email = "";
				if( row.get("client_email") != null)
				{
					email = row.get("client_email").toString();
				}
				String fax = "";
				if( row.get("client_fax") != null)
				{
					fax = row.get("client_fax").toString();
				}
				String website = "";
				if( row.get("client_website") != null)
				{
					website = row.get("client_website").toString();
				}
				
				String clientrequest = "";
				if( row.get("client_request") != null)
				{
					clientrequest = row.get("client_request").toString();
				}
				
				String enqdate = "";
				if( row.get("client_enquire_date") != null)
				{
					enqdate = row.get("client_enquire_date").toString();
				}
				String source = "";
				if( row.get("client_source") != null)
				{
					source = row.get("client_source").toString();
				}
				String sourcedetails = "";
				if( row.get("client_source_details") != null)
				{
					sourcedetails = row.get("client_source_details").toString();
				}
				String dept = "";
				if( row.get("client_dept") != null)
				{
					dept = row.get("client_dept").toString();
				}
				String notes = "";
				if( row.get("notes") != null)
				{
					notes = row.get("notes").toString();
				}
				String notesdate = null;
				if( row.get("notes_date") != null)
				{
					notesdate = row.get("notes_date").toString();
				}
				String notesstatus = "";
				if( row.get("notes_status") != null)
				{
					notesstatus = row.get("notes_status").toString();
				}
				String notesstatusdate = null;
				if( row.get("notes_status_date") != null)
				{
					notesstatusdate = row.get("notes_status_date").toString();
				}
				
				String appnmtDate = null;
				if( row.get("appnmtDate") != null)
				{
					appnmtDate = row.get("appnmtDate").toString();
				}
				String appnmtTime = null;
				if( row.get("null") != null)
				{
					appnmtTime = row.get("appnmtTime").toString();
				}
				String appSession = "";
				if( row.get("appSession") != null)
				{
					appSession = row.get("appSession").toString();
				}
				String appZone = "";
				if( row.get("appZone") != null)
				{
					appZone = row.get("appZone").toString();
				}
				String appStatus = "";
				if( row.get("appStatus") != null)
				{
					appStatus = row.get("appStatus").toString();
				}
				String appNotes = "";
				if( row.get("appNotes") != null)
				{
					appNotes = row.get("appNotes").toString();
				}
				String appClosedNotes = "";
				if( row.get("appClosedNotes") != null)
				{
					appClosedNotes = row.get("appClosedNotes").toString();
				}
				String appClosedFollowupDate = null;
				if( row.get("appClosedFollowupDate") != null)
				{
					appClosedFollowupDate = row.get("appClosedFollowupDate").toString();
				}
				String appClosedStatus = "";
				if( row.get("appClosedStatus") != null)
				{
					appClosedStatus = row.get("appClosedStatus").toString();
				}
				
				String noofbeds = "0";
				if( row.get("No_of_Beds") != null)
				{
					noofbeds = row.get("No_of_Beds").toString();
				}
				
				if( row.get("UserName") != null)
				{
					userid = row.get("UserName").toString();
				}
				String duplicate = "";
				if( row.get("duplicate") != null)
				{
					duplicate = row.get("duplicate").toString();
				}
				String conference = "";
				if( row.get("Conference") != null)
				{
					conference = row.get("Conference").toString();
				}
				//website = website.s

				System.out.println("zip "+ zip);
				
				if(contactperson !=null && !contactperson.equals("-"))
				{
					if(contactperson.length() > 100)
					{
						contactperson = contactperson.substring(0,99);
					}
				}
				

				//String notesdate = enqdate;
			
				
				
				String sql = "INSERT INTO client (client_trace_no,client_company_name,client_conPerson,client_address,"
						+ " client_city,client_state,client_zip,client_country,client_phone,client_email,client_fax,client_website,"
						+ " client_request, client_enquire_date,client_source,client_source_details,client_dept,notes,"
						+ " notes_date, notes_status, notes_status_date, appnmtDate, appnmtTime, appSession, appZone, "
						+ " appStatus, appNotes, appClosedNotes,appClosedFollowupDate,appClosedStatus,No_of_Beds,UserName,duplicate,Conference)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						   
						
				jdbcTemplate.update(sql, new Object[] {traceno,clientname,contactperson,address,city,state,zip,country,
						phone,email,fax,website, clientrequest, enqdate,source,sourcedetails,
						dept,notes,notesdate,notesstatus,notesstatusdate, appnmtDate, appnmtTime,appSession,appZone,
						appStatus,appNotes, appClosedNotes, appClosedFollowupDate, appClosedStatus, noofbeds, userid,duplicate,conference});
				
				insertcount++;
				
			}
			
			System.out.println("insertcount "+ insertcount);
			
			if(insertcount == mdbrows.size())
			{
				datainserted = true;
				System.out.println("all data inserted successfully");
			}
		}
		catch (Exception e) {
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
				//eMsg = eMsg.substring(0, eMsg.indexOf("for key "));
	
				if (eMsg != null && !eMsg.equals("")) {
					//String serialnum = eMsg.substring(eMsg.lastIndexOf("-"), eMsg.lastIndexOf("'"));
					//eMsg = "Employee code at row " +k + " doesn't exist in Payroll System. Please change and upload again.";
				}
			}
			//String validationmsg = "Error while processing row " + k + ". " + eMsg;
			throw new Exception(eMsg);
		}
		
		return datainserted;
	} */
	
	
	@Transactional(rollbackFor = Exception.class)
	public boolean insertExistingNotes() throws Exception {
		
		boolean datainserted = false;
		
		try
		{
			String mbdsql = "select client_trace_no, notes, notes_date,notes_status,notes_followup_date,"
					+ "UserName from notes1";
			
						
			//System.out.println("mbdsql "+ mbdsql);
	
			//JdbcTemplate jdbcTemplate1 = new JdbcTemplate(sqlserverdataSource);
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	
			List<Map<String, Object>> mdbrows = jdbcTemplate.queryForList(mbdsql, new Object[] {});
	
			System.out.println("notes size is "+ mdbrows.size());
			
			
			
			int insertcount = 0;
			
			for (Map<String, Object> row : mdbrows) {
				String traceno = "";
				if( row.get("client_trace_no") != null)
				{
					traceno = row.get("client_trace_no").toString();
				}
				
				String notes_id = "0";
				if( row.get("notes_id") != null)
				{
					notes_id = row.get("notes_id").toString();
				}
				//String notesid = row.get("notes_id").toString();
				String notes =  "";
				if( row.get("notes") != null)
				{
					notes = row.get("notes").toString();
				}
				String notesdate = null;
				if( row.get("notes_date") != null)
				{
					notesdate = row.get("notes_date").toString();
				}
				String notesstatus = "";
				if( row.get("notes_status") != null)
				{
					notesstatus = row.get("notes_status").toString();
				}
				String notesfollowupdate = null;
				if( row.get("notes_followup_date") != null)
				{
					notesfollowupdate = row.get("notes_followup_date").toString();
				}
				String appNotes = "";
				if( row.get("AppNotes") != null)
				{
					appNotes = row.get("AppNotes").toString();
				}
				String completedNotes = "";
				if( row.get("CompletedNotes") != null)
				{
					completedNotes = row.get("CompletedNotes").toString();
				}
				String completedStatus = "";
				if( row.get("CompletedStatus") != null)
				{
					completedStatus = row.get("CompletedStatus").toString();
				}
				String completedFollowupDt = null;
				if( row.get("CompletedFollowupDt") != null)
				{
					completedFollowupDt = row.get("CompletedFollowupDt").toString();
				}
				String dept = "";
				if( row.get("Dept") != null)
				{
					dept = row.get("Dept").toString();
				}
				String username = "";
				if( row.get("UserName") != null)
				{
					username = row.get("UserName").toString();
				}

				System.out.println("notes "+ notes);
				
				String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
				
				
				String sql = "INSERT INTO azc_notes "
						+ "(Notes_Id, TraceNo, Notes, Notes_Dt, Status_Id, Followup_Dt, user_id,Created_By,Created_Dt)"
						+ "VALUES (?,?,?,?,?,?,?,?,?)";
		
				jdbcTemplate.update(sql, new Object[] {notes_id,traceno,notes,notesdate,notesstatus,
						notesfollowupdate,username,"Migration", currentDate});
				
				insertcount++;
				
			}
			
			System.out.println("insertcount "+ insertcount);
			
			if(insertcount == mdbrows.size())
			{
				datainserted = true;
				System.out.println("all notes data inserted successfully");
				
				String clientsql = "select TraceNo, bucket_id from azc_client";
				List<Map<String, Object>> clientrows = jdbcTemplate.queryForList(clientsql, new Object[] {});
				
				for (Map<String, Object> crow : clientrows) {
					String cTraceNo =  crow.get("TraceNo").toString();
					String cbucketId =  crow.get("bucket_id").toString();
					
					String maxsql = "SELECT max(Notes_Id) as Notes_Id FROM azc_notes where TraceNo = ?";
					List<Map<String, Object>> maxrows = jdbcTemplate.queryForList(maxsql, new Object[] {cTraceNo});
					String maxNotesId = "";
					for (Map<String, Object> mrow : maxrows) {
						maxNotesId =  mrow.get("Notes_Id").toString();
					}
					
					String updatesql = "update azc_notes set bucket_id = ? where TraceNo = ? and Notes_Id =?";
					
					jdbcTemplate.update(updatesql, new Object[] {cbucketId, cTraceNo, maxNotesId});
					
					System.out.println("notes bucket update completed successfully ");
				}
			}
		}
		catch (Exception e) {
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
				//eMsg = eMsg.substring(0, eMsg.indexOf("for key "));
	
				if (eMsg != null && !eMsg.equals("")) {
					//String serialnum = eMsg.substring(eMsg.lastIndexOf("-"), eMsg.lastIndexOf("'"));
					//eMsg = "Employee code at row " +k + " doesn't exist in Payroll System. Please change and upload again.";
				}
			}
			/*if (eMsg != null && eMsg.contains("FOREIGN KEY")) {
				System.out.println("inside second if");
				eMsg = eMsg.substring(0, eMsg.indexOf("at row"));
			}*/
			//String validationmsg = "Error while processing row " + k + ". " + eMsg;
			throw new Exception(eMsg);
		}
		
		return datainserted;
	}
	
	//@Transactional(rollbackFor = Exception.class)
	/*public boolean insertExistingNotes() throws Exception {
		
		boolean datainserted = false;
		
		try
		{

			
			String mbdsql = "select client_trace_no, notes_id, notes, notes_date,notes_status,notes_followup_date,"
					+ " AppNotes, CompletedNotes, CompletedStatus, CompletedFollowupDt, Dept, UserName from Notes";
			
			//System.out.println("mbdsql "+ mbdsql);
	
			JdbcTemplate jdbcTemplate1 = new JdbcTemplate(sqlserverdataSource);
	
			List<Map<String, Object>> mdbrows = jdbcTemplate1.queryForList(mbdsql, new Object[] {});
	
			System.out.println("mdbrows size is "+ mdbrows.size());
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			int insertcount = 0;
			
			for (Map<String, Object> row : mdbrows) {
				String traceno = "";
				if( row.get("client_trace_no") != null)
				{
					traceno = row.get("client_trace_no").toString();
				}
				
				String notes_id = "";
				if( row.get("notes_id") != null)
				{
					notes_id = row.get("notes_id").toString();
				}
				//String notesid = row.get("notes_id").toString();
				String notes =  "";
				if( row.get("notes") != null)
				{
					notes = row.get("notes").toString();
				}
				String notesdate = null;
				if( row.get("notes_date") != null)
				{
					notesdate = row.get("notes_date").toString();
				}
				String notesstatus = "";
				if( row.get("notes_status") != null)
				{
					notesstatus = row.get("notes_status").toString();
				}
				String notesfollowupdate = null;
				if( row.get("notes_followup_date") != null)
				{
					notesfollowupdate = row.get("notes_followup_date").toString();
				}
				String appNotes = "";
				if( row.get("AppNotes") != null)
				{
					appNotes = row.get("AppNotes").toString();
				}
				String completedNotes = "";
				if( row.get("CompletedNotes") != null)
				{
					completedNotes = row.get("CompletedNotes").toString();
				}
				String completedStatus = "";
				if( row.get("CompletedStatus") != null)
				{
					completedStatus = row.get("CompletedStatus").toString();
				}
				String completedFollowupDt = null;
				if( row.get("CompletedFollowupDt") != null)
				{
					completedFollowupDt = row.get("CompletedFollowupDt").toString();
				}
				String dept = "";
				if( row.get("Dept") != null)
				{
					dept = row.get("Dept").toString();
				}
				String username = "";
				if( row.get("UserName") != null)
				{
					username = row.get("UserName").toString();
				}

				System.out.println("notes "+ notes);
				
				String sql = "INSERT INTO notes "
						+ "(client_trace_no,notes_id, notes, notes_date, notes_status, notes_followup_date, AppNotes,"
						+ " CompletedNotes, CompletedStatus, CompletedFollowupDt,Dept, UserName)"
						+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
				jdbcTemplate.update(sql, new Object[] {traceno, notes_id, notes,notesdate,notesstatus,
						notesfollowupdate,appNotes, completedNotes, completedStatus, completedFollowupDt,dept, username});
				
				insertcount++;
				
			}
			
			System.out.println("insertcount "+ insertcount);
			
			if(insertcount == mdbrows.size())
			{
				datainserted = true;
				System.out.println("all notes data inserted successfully");
			}
		}
		catch (Exception e) {
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
				//eMsg = eMsg.substring(0, eMsg.indexOf("for key "));
	
				if (eMsg != null && !eMsg.equals("")) {
					//String serialnum = eMsg.substring(eMsg.lastIndexOf("-"), eMsg.lastIndexOf("'"));
					//eMsg = "Employee code at row " +k + " doesn't exist in Payroll System. Please change and upload again.";
				}
			}

			//String validationmsg = "Error while processing row " + k + ". " + eMsg;
			throw new Exception(eMsg);
		}
		
		return datainserted;
	}
	*/

	public CRMEmail getsequencelist(CRMEmail mail) throws Exception 
	{
		
		String permanentsequence= "SELECT attachment_seq FROM azc_config";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(permanentsequence);
		if(rows.size() > 0)
		{
			String seqid="";
			for (Map<String, Object> row : rows) 
			{
				seqid = row.get("attachment_seq").toString();
				System.out.println("seqid "+seqid);
			}
			int seqidint = Integer.parseInt(seqid);
			int number=1;
			int value=seqidint+number;
			
			mail.setEmailSequenceNo(value);
			
		}
		else
		{
			mail.setEmailSequenceNo(1);
		}
		
		
		return mail;
		
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void insertEMailDetails(CRMEmail mail) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		
		String sql = "INSERT INTO azc_email_details "
		    + "(trace_id, email_seq, from_email_id, to_email_id, cc, email_subject, email_content, created_by, created_dt, modified_by, modified_dt) "
		    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


		 jdbcTemplate.update(sql, new Object[] {mail.getTraceNo(), mail.getEmailSequenceNo(), mail.getFrom(), mail.getRecipient(), mail.getCc(), mail.getSubject(),
				 mail.getContent(), mail.getLoginname(), currentDate, mail.getLoginname(), currentDate  });
		 
		 String sqlselectsequence= "SELECT attachment_seq FROM azc_config";
		 
			List<Map<String, Object>> seqrows = jdbcTemplate.queryForList(sqlselectsequence);
			if(seqrows.size() > 0)
			{
				int sequence_No = mail.getEmailSequenceNo();				
				  String updatesql = "update azc_config set attachment_seq=?";							  
				  jdbcTemplate.update(updatesql,new Object[] { sequence_No });
			}
			else
			{
				int sequence_No = mail.getEmailSequenceNo();	
				String insertsql = "insert into azc_config (attachment_seq) values (?)";						  
				jdbcTemplate.update(insertsql,new Object[] { sequence_No });
			}
		
	}

	public List<CRMEmail> getemailDetails(String id) throws Exception
	{
		List<CRMEmail> mailList = new ArrayList<CRMEmail>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String selectsql = "select * from azc_email_details where trace_id = " +id ;
		
		List<Map<String, Object>> prows = jdbcTemplate.queryForList(selectsql, new Object[] {});

		for (Map<String, Object> row : prows) 
		{
			CRMEmail email = new CRMEmail();
			
			if(row.get("trace_id") != null && !row.get("trace_id").equals(""))
			{
				email.setTraceNo(row.get("trace_id").toString());
			}
			if(row.get("email_seq") != null && !row.get("email_seq").equals(""))
			{
				email.setEmailSequenceNo(Integer.valueOf(row.get("email_seq").toString()));
			}
			if(row.get("from_email_id") != null && !row.get("from_email_id").equals(""))
			{
				email.setFrom(row.get("from_email_id").toString());
			}
			if(row.get("to_email_id") != null && !row.get("to_email_id").equals(""))
			{
				email.setRecipient(row.get("to_email_id").toString());
			}
			if(row.get("email_subject") != null && !row.get("email_subject").equals(""))
			{
				email.setSubject(row.get("email_subject").toString());
			}
			if(row.get("email_content") != null && !row.get("email_content").equals(""))
			{
				email.setContent(row.get("email_content").toString());
			}
			if(row.get("cc") != null && !row.get("cc").equals(""))
			{
				email.setCc(row.get("cc").toString());
			}
			mailList.add(email);
		}
		return mailList;
	}

	public void inactiveUser(CRMUser user) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		 String sqlselect = "SELECT user_status FROM azc_user_master where User_id='"+user.getUserId()+"' ";
			
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlselect);
		String status="";
		for (Map<String, Object> row : rows) 
		{
			status = row.get("user_status").toString();
		}
			
		if(status.equals("A"))
		{
			status = "I";
		}
		else
		{
			status = "A";
		}
		
		String sql = "UPDATE azc_user_master set user_status=?, "
			+" Modifed_By = ?, Modified_Dt = ? where User_id = ? ";

		jdbcTemplate.update( sql, new Object[] {status, user.getUserLoginName(), currentDate, user.getUserId()});
		
	}


	public List<CRMUser> getuserlistwithinactive() throws Exception 
	{
		List<CRMUser> userList = new ArrayList<CRMUser>();
		
		String sql = "select * from azc_user_master order by First_Name";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userList = jdbcTemplate.query(sql, new CRMUserRowMapper());

		return userList;
	}
	
	public List<CRMUser> getUserManagerTeam() throws Exception 
	{
		List<CRMUser> userList = new ArrayList<CRMUser>();
		
		String sql = "select * from azc_user_master "
				+ "where role_id in (3,4) and user_status = 'A' order by First_Name";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userList = jdbcTemplate.query(sql, new CRMUserRowMapper());

		return userList;
	}

}
