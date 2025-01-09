package com.soarcrm.dao;

import java.sql.SQLException;
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
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.soarcrm.domain.CRMStatus;
import com.soarcrm.jdbc.CRMStatusRowMapper;
import com.soarcrm.util.AllzoneCRMUtil;

public class CRMStatusDaoImpl implements CRMStatusDao 
{
	@Autowired
	DataSource dataSource;

	public void insertStatus(CRMStatus status) throws Exception
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		String crmstatus="A";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		
		String sql = "INSERT INTO azc_status "
		    + "(status_id, Status_Desc, Status, Created_By, Created_Dt) "
		    + "VALUES (?, ?, ?, ?, ?)";


		 jdbcTemplate.update(sql, new Object[] { status.getStatusId(), status.getStatusDescription(), crmstatus,   
				 status.getLoginName(), currentDate});
	}

	public List<CRMStatus> getStatusList() throws Exception 
	{
		List<CRMStatus> statuslist = new ArrayList<CRMStatus>();
		
		String sql = "select * from azc_status where Status = 'A' and Status_Desc != 'ALL' ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		statuslist = jdbcTemplate.query(sql, new CRMStatusRowMapper());

		return statuslist;
	}
	
	public List<CRMStatus> getStatusList(String closed, String open) throws Exception 
	{
		List<CRMStatus> statuslist = new ArrayList<CRMStatus>();
		
		String sql = "select * from azc_status where Status_Desc != 'ALL' and Status = 'A' and status_id not in ('"+closed+"', '"+open+"')";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		statuslist = jdbcTemplate.query(sql, new CRMStatusRowMapper());

		return statuslist;
	}

	public CRMStatus getStatus(String id) throws Exception 
	{
		List<CRMStatus> statuslist = new ArrayList<CRMStatus>();		
		String sql = "select * from azc_status where status_id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		statuslist = jdbcTemplate.query(sql, new CRMStatusRowMapper());
	
		return statuslist.get(0);
		
	}

	public void updateStatus(CRMStatus crmstatus) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		String status="A";
		
		String sql = "UPDATE azc_status set  Status_Desc = ?, Status = ?,  Modified_By = ?, Modified_Dt = ? where status_id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update( sql, new Object[] {crmstatus.getStatusDescription(), status, crmstatus.getLoginName(), currentDate, 
				crmstatus.getStatusId()});
	}

	public CRMStatus getDashboardDetails(CRMStatus crmstatus) throws Exception 
	{
		LocalDate date = LocalDate.now().minusDays(3);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar currentdt = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     currentdt.add(Calendar.YEAR,-1);
	     String startDate = f.format(currentdt.getTime());
		
		String openstatus = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";		
		int opendeals = jdbcTemplate.queryForObject(openstatus, new Object[] {6, startDate, currentDate}, Integer.class);
		crmstatus.setOpendeals(String.valueOf(opendeals));
		
		String followupstatus = "select count(*) from azc_client where Status_Id  in ('3') and User_id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";			
		int followup = jdbcTemplate.queryForObject(followupstatus, new Object[] {crmstatus.getUserid(), startDate, currentDate}, Integer.class);
		crmstatus.setFollowup(String.valueOf(followup));
			
		String emailstatus = "select count(*) from azc_client where Status_Id = ? and User_id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";			
		int email = jdbcTemplate.queryForObject(emailstatus, new Object[] {4, crmstatus.getUserid(),startDate, currentDate}, Integer.class);
		crmstatus.setEmailsent(String.valueOf(email));
			
		String	overduefollowup = "select count(*) from azc_client where Status_Id = ? and User_id = ? and azc_client.Modified_Dt <= ? and azc_client.Modified_Dt BETWEEN ? AND ?";			
		int followupoverdue = jdbcTemplate.queryForObject(overduefollowup, new Object[] {4, crmstatus.getUserid(), date.toString(), startDate, currentDate}, Integer.class);
		crmstatus.setOverduefollowup(String.valueOf(followupoverdue));
			
		String overdueemail = "select count(*) from azc_client where Status_Id = ? and User_id = ? and  azc_client.Modified_Dt <= ? and azc_client.Modified_Dt BETWEEN ? AND ? "
				+ " and e_mail !=''";		

	
		
		int emailoverdue = jdbcTemplate.queryForObject(overdueemail, new Object[] {6,  crmstatus.getUserid(),
				date.toString(), startDate, currentDate}, Integer.class);
		
		//System.out.println("emailoverdue is "+ emailoverdue);
		crmstatus.setOverdueemail(String.valueOf(emailoverdue));
		
		String closedstatus = "select count(*) from azc_client where Status_Id = ? and User_id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";		
		int closeddeals = jdbcTemplate.queryForObject(closedstatus, new Object[] {14, crmstatus.getUserid(), startDate, currentDate}, Integer.class);
		crmstatus.setClosedDeals(String.valueOf(closeddeals));
		
		String hotleadsstatus = "select count(*) from azc_client where Status_Id = ? and User_id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";			
		int ihotleads = jdbcTemplate.queryForObject(hotleadsstatus, new Object[] {30, crmstatus.getUserid(), startDate,currentDate}, Integer.class);
		crmstatus.setHotleads(String.valueOf(ihotleads));
		
		String droppedstatus = "select count(*) from azc_client where Status_Id = ? and User_id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";			
		int idroppedleads = jdbcTemplate.queryForObject(droppedstatus, new Object[] {31, crmstatus.getUserid(), startDate,currentDate}, Integer.class);
		crmstatus.setDroppedleads(String.valueOf(idroppedleads));
		
		/*String overdueDealstatus = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt <=? ";
		int overduedeals = jdbcTemplate.queryForObject(overdueDealstatus, new Object[] {6, date.toString()}, Integer.class);
		crmstatus.setOverdueDeal(String.valueOf(overduedeals));*/
		
		return crmstatus;
	}

	public CRMStatus getdashboardDataList(CRMStatus crmstatus) throws Exception
	{		
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");		
		String weekDate = AllzoneCRMUtil.getDate7DaysBefore("yyyy-MM-dd");		
		String monthDate = AllzoneCRMUtil.getmonthDatetime("yyyy-MM-dd");
		
		Calendar c = Calendar.getInstance(  ); 
		
		int year = c.get(Calendar.YEAR);
		/*int month = c.get(Calendar.MONTH + 1);*/
		
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		int numOfWeeksInMonth = (int)AllzoneCRMUtil.getNoofweekforMonth(month, year);	
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String followupstatusdaily="";
		String emailstatusdaily = "";		
		String responsestatusdaily="";
		String closestatusdaily="";
		
		String followupstatusweekly="";		
		String emailstatusweekly ="";
		String responsestatusweekly ="";
		String closestatusweekly="";
		
		String followupstatusmonthly = "";
		String emailstatusmonthly ="";
		String responsestatusmonthly ="";		
		String closestatusmonthly="";
		
		String targetfollowstatusdaily = "";
		String targetemailstatusdaily = "";
		String targetresponsestatusdaily = "";
		String targetclosestatusdaily="";
		
		int targetfollowstatusweekly  =  0;
		int targetemailstatusweekly = 0;
		int targetresponsestatusweekly = 0;
		int targetclosestatusweekly=0;
		
		int targetfollowstatusmonthly = 0;
		int targetemailstatusmonthly = 0;
		int targetresponsestatusmonthly = 0;
		int targetclosestatusmonthly=0;
		
		if(crmstatus.getUserid() != null && !crmstatus.getUserid().equals(""))
		{			
			String openstatusdaily  = "select count(*) as daily_target from azc_notes where Status_Id='6' and Created_Dt='"+currentDate+"'"
					+ "and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> targetopenrowsdaily= jdbcTemplate.queryForList(openstatusdaily);
			for (Map<String, Object> row : targetopenrowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setOpendealsdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setOpendealsdaily("0");
				}
			}
			
			String openstatusweekly  = "select count(*) as daily_target from azc_notes where status_id = '6' and "
					+ "Created_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> openrowsweekly = jdbcTemplate.queryForList(openstatusweekly);
			for (Map<String, Object> row : openrowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setOpendealsweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setOpendealsweekly("0");
				}
			}
			
			String openstatusmonthly  = "select count(*) as daily_target from azc_notes where status_id = '6' and "
					+ "Created_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> openrowsmonthly = jdbcTemplate.queryForList(openstatusmonthly);
			for (Map<String, Object> row : openrowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setOpendealsmonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setOpendealsmonthly("0");
				}
			}
			
			String targetopenstatusdaily= "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
					"tyear = '"+year+"' and tmonth = '"+month+"' and status_id='6' and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> openrows = jdbcTemplate.queryForList(targetopenstatusdaily);
			for (Map<String, Object> row : openrows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetopendaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetopendaily("0");
				}
			}
			
			int targetopenstatusweekly  = Integer.valueOf(crmstatus.getTargetopendaily()) * 5 ;		
			crmstatus.setTargetopenweekly(String.valueOf(targetopenstatusweekly));			
			
			int targetopenstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetopendaily());			
			crmstatus.setTargetopenmonthly(String.valueOf(targetopenstatusmonthly));
			
			followupstatusdaily = "select count(*) as daily_target from azc_notes where status_id = '3' and Modified_Dt='"+currentDate+"'"
					+ "and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> followuprows = jdbcTemplate.queryForList(followupstatusdaily);
			for (Map<String, Object> row : followuprows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{
					crmstatus.setFollowupdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setFollowupdaily("0");
				}
			}
			
			emailstatusdaily  = "select count(*) as daily_target from azc_notes where status_id = '4' and Modified_Dt='"+currentDate+"'"
					+ "and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> mailrows = jdbcTemplate.queryForList(emailstatusdaily);
			for (Map<String, Object> row : mailrows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{ 
					crmstatus.setEmailsentdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setEmailsentdaily("0");
				}
			}
			
			responsestatusdaily  = "select count(*) as daily_target from azc_notes where status_id = '16' and Modified_Dt='"+currentDate+"'"
					+ "and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> responserows = jdbcTemplate.queryForList(responsestatusdaily);
			for (Map<String, Object> row : responserows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setResponsedaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setResponsedaily("0");
				}
			}
			
			closestatusdaily  = "select count(*) as daily_target from azc_notes where status_id = '14' and Modified_Dt='"+currentDate+"' "
					+ "and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> closerows = jdbcTemplate.queryForList(closestatusdaily);
			for (Map<String, Object> row : closerows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setClosedaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setClosedaily("0");
				}
			}
			
				
			followupstatusweekly  = "select count(*) as daily_target from azc_notes where status_id = '3' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> followuprowsweekly = jdbcTemplate.queryForList(followupstatusweekly);
			for (Map<String, Object> row : followuprowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setFollowupweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setFollowupweekly("0");
				}
			}
			
			emailstatusweekly  = "select count(*) as daily_target from azc_notes where status_id = '4' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> emailrowsweekly = jdbcTemplate.queryForList(emailstatusweekly);
			for (Map<String, Object> row : emailrowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setEmailsentweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setEmailsentweekly("0");
				}
			}
			
			responsestatusweekly  = "select count(*) as daily_target from azc_notes where status_id = '16' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> responserowsweekly = jdbcTemplate.queryForList(responsestatusweekly);
			for (Map<String, Object> row : responserowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setResponseweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setResponseweekly("0");
				}
			}
			
			closestatusweekly  = "select count(*) as daily_target from azc_notes where status_id = '14' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> closerowsweekly = jdbcTemplate.queryForList(closestatusweekly);
			for (Map<String, Object> row : closerowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setCloseweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setCloseweekly("0");
				}
			}
			
			followupstatusmonthly  = "select count(*) as daily_target from azc_notes where status_id = '3' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> followrowsmonthly = jdbcTemplate.queryForList(followupstatusmonthly);
			for (Map<String, Object> row : followrowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setFollowupmonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setFollowupmonthly("0");
				}
			}
			
			emailstatusmonthly  = "select count(*) as daily_target from azc_notes where status_id = '4' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> emailrowsmonthly = jdbcTemplate.queryForList(emailstatusmonthly);
			for (Map<String, Object> row : emailrowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setEmailsentmonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setEmailsentmonthly("0");
				}
			}
			
			responsestatusmonthly  = "select count(*) as daily_target from azc_notes where status_id = '16' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> responserowsmonthly = jdbcTemplate.queryForList(responsestatusmonthly);
			for (Map<String, Object> row : responserowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setReponsemonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setReponsemonthly("0");
				}
			}
			
			closestatusmonthly  = "select count(*) as daily_target from azc_notes where status_id = '14' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> closerowsmonthly = jdbcTemplate.queryForList(closestatusmonthly);
			for (Map<String, Object> row : closerowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setClosemonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setClosemonthly("0");
				}
			}
			
			targetfollowstatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
					"tmonth = '"+month+"' and tyear = '"+year+"' and status_id='3' and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> targetfollowuprowsdaily= jdbcTemplate.queryForList(targetfollowstatusdaily);
			for (Map<String, Object> row : targetfollowuprowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetfollowupdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetfollowupdaily("0");
				}
			}
			
			targetemailstatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
					"tmonth = '"+month+"' and tyear = '"+year+"' and status_id='4' and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> targetemailrowsdaily= jdbcTemplate.queryForList(targetemailstatusdaily);
			for (Map<String, Object> row : targetemailrowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetemaildaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetemaildaily("0");
				}
			}
		
			
			targetresponsestatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
					"tmonth = '"+month+"' and tyear = '"+year+"' and status_id='7' and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> targetresponserowsdaily= jdbcTemplate.queryForList(targetresponsestatusdaily);
			for (Map<String, Object> row : targetresponserowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetresponsedaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetresponsedaily("0");
				}
			}
			
			targetclosestatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where tyear = '"+year+"' " + 
					"and tmonth = '"+month+"' and status_id='14' and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> targetcloserowsdaily= jdbcTemplate.queryForList(targetclosestatusdaily);
			for (Map<String, Object> row : targetcloserowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetcloseedaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetcloseedaily("0");
				}
			}
			
			targetfollowstatusweekly  = Integer.valueOf(crmstatus.getTargetfollowupdaily()) * 5 ;		
			crmstatus.setTargetfollowupweekly(String.valueOf(targetfollowstatusweekly));
			
			targetemailstatusweekly  = Integer.valueOf(crmstatus.getTargetemaildaily()) * 5 ;		
			crmstatus.setTargetemailweekly(String.valueOf(targetemailstatusweekly));
			
			targetresponsestatusweekly = Integer.valueOf(crmstatus.getTargetresponsedaily()) *5;
			crmstatus.setTargetresponseweekly(String.valueOf(targetresponsestatusweekly));
			
			targetclosestatusweekly = Integer.valueOf(crmstatus.getTargetcloseedaily()) *5;
			crmstatus.setTargetcloseeweekly(String.valueOf(targetclosestatusweekly));
			
			
			targetfollowstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetfollowupdaily());			
			crmstatus.setTargetfollowupmonthly(String.valueOf(targetfollowstatusmonthly));
			
			targetemailstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetemaildaily());
			crmstatus.setTargetemailmonthly(String.valueOf(targetemailstatusmonthly));
			
			targetresponsestatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetresponsedaily());
			crmstatus.setTargetresponsemonthly(String.valueOf(targetresponsestatusmonthly));
			
			targetclosestatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetcloseedaily());
			crmstatus.setTargetcloseemonthly(String.valueOf(targetclosestatusmonthly));
			
		}
		else
		{
			
			String openstatusdaily  = "select count(*) as daily_target from azc_notes where Status_Id='6' and Created_Dt='"+currentDate+"'";
			
			List<Map<String, Object>> targetopenrowsdaily= jdbcTemplate.queryForList(openstatusdaily);
			for (Map<String, Object> row : targetopenrowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setOpendealsdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setOpendealsdaily("0");
				}
			}
			
			String openstatusweekly  = "select count(*) as daily_target from azc_notes where status_id = '6' and "
					+ "Created_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' ";
			
			List<Map<String, Object>> openrowsweekly = jdbcTemplate.queryForList(openstatusweekly);
			for (Map<String, Object> row : openrowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setOpendealsweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setOpendealsweekly("0");
				}
			}
			
			String openstatusmonthly  = "select count(*) as daily_target from azc_notes where status_id = '6' and "
					+ "Created_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' ";
			
			List<Map<String, Object>> openrowsmonthly = jdbcTemplate.queryForList(openstatusmonthly);
			for (Map<String, Object> row : openrowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setOpendealsmonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setOpendealsmonthly("0");
				}
			}
			
			String targetopenstatusdaily= "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
					"tmonth = '"+month+"' and tyear = '"+year+"' and status_id='6'";
			
			List<Map<String, Object>> openrows = jdbcTemplate.queryForList(targetopenstatusdaily);
			for (Map<String, Object> row : openrows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetopendaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetopendaily("0");
				}
			}
			
			int targetopenstatusweekly  = Integer.valueOf(crmstatus.getTargetopendaily()) * 5 ;		
			crmstatus.setTargetopenweekly(String.valueOf(targetopenstatusweekly));			
			
			int targetopenstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetopendaily());			
			crmstatus.setTargetopenmonthly(String.valueOf(targetopenstatusmonthly));
			
			followupstatusdaily = "select count(*) as daily_target from azc_notes where status_id = '3' and Modified_Dt='"+currentDate+"'";
			
			List<Map<String, Object>> followuprows = jdbcTemplate.queryForList(followupstatusdaily);
			for (Map<String, Object> row : followuprows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{
					crmstatus.setFollowupdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setFollowupdaily("0");
				}
			}
			
			emailstatusdaily  = "select count(*) as daily_target from azc_notes where status_id = '4' and Modified_Dt='"+currentDate+"'";
			
			List<Map<String, Object>> mailrows = jdbcTemplate.queryForList(emailstatusdaily);
			for (Map<String, Object> row : mailrows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{ 
					crmstatus.setEmailsentdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setEmailsentdaily("0");
				}
			}
			
			responsestatusdaily  = "select count(*) as daily_target from azc_notes where status_id = '16' and Modified_Dt='"+currentDate+"'";
			
			List<Map<String, Object>> responserows = jdbcTemplate.queryForList(responsestatusdaily);
			for (Map<String, Object> row : responserows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setResponsedaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setResponsedaily("0");
				}
			}
			
			closestatusdaily  = "select count(*) as daily_target from azc_notes where status_id = '14' and Modified_Dt='"+currentDate+"'";
			
			List<Map<String, Object>> closerows = jdbcTemplate.queryForList(closestatusdaily);
			for (Map<String, Object> row : closerows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setClosedaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setClosedaily("0");
				}
			}
			
			followupstatusweekly  = "select count(*) as daily_target from azc_notes where status_id = '3' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' ";
			
			List<Map<String, Object>> followuprowsweekly = jdbcTemplate.queryForList(followupstatusweekly);
			for (Map<String, Object> row : followuprowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setFollowupweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setFollowupweekly("0");
				}
			}
			
			emailstatusweekly  = "select count(*) as daily_target from azc_notes where status_id = '4' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' ";
			
			List<Map<String, Object>> emailrowsweekly = jdbcTemplate.queryForList(emailstatusweekly);
			for (Map<String, Object> row : emailrowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setEmailsentweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setEmailsentweekly("0");
				}
			}
			
			responsestatusweekly  = "select count(*) as daily_target from azc_notes where status_id = '16' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' ";
			
			List<Map<String, Object>> responserowsweekly = jdbcTemplate.queryForList(responsestatusweekly);
			for (Map<String, Object> row : responserowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setResponseweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setResponseweekly("0");
				}
			}
			
			closestatusweekly  = "select count(*) as daily_target from azc_notes where status_id = '14' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' ";
			
			List<Map<String, Object>> closerowsweekly = jdbcTemplate.queryForList(closestatusweekly);
			for (Map<String, Object> row : closerowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setCloseweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setCloseweekly("0");
				}
			}
			
			followupstatusmonthly  = "select count(*) as daily_target from azc_notes where status_id = '3' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' ";
			
			List<Map<String, Object>> followrowsmonthly = jdbcTemplate.queryForList(followupstatusmonthly);
			for (Map<String, Object> row : followrowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setFollowupmonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setFollowupmonthly("0");
				}
			}
			
			emailstatusmonthly  = "select count(*) as daily_target from azc_notes where status_id = '4' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' ";
			
			List<Map<String, Object>> emailrowsmonthly = jdbcTemplate.queryForList(emailstatusmonthly);
			for (Map<String, Object> row : emailrowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setEmailsentmonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setEmailsentmonthly("0");
				}
			}
			
			responsestatusmonthly  = "select count(*) as daily_target from azc_notes where status_id = '16' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' ";
			
			List<Map<String, Object>> responserowsmonthly = jdbcTemplate.queryForList(responsestatusmonthly);
			for (Map<String, Object> row : responserowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setReponsemonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setReponsemonthly("0");
				}
			}	
			
			closestatusmonthly  = "select count(*) as daily_target from azc_notes where status_id = '14' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' ";
			
			List<Map<String, Object>> closerowsmonthly = jdbcTemplate.queryForList(closestatusmonthly);
			for (Map<String, Object> row : closerowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setClosemonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setClosemonthly("0");
				}
			}	
			
			targetfollowstatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where tyear = '"+year+"' "
					+ "and tmonth = '"+month+"' and status_id='3' ";
			
			List<Map<String, Object>> targetfollowuprowsdaily= jdbcTemplate.queryForList(targetfollowstatusdaily);
			for (Map<String, Object> row : targetfollowuprowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetfollowupdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetfollowupdaily("0");
				}
			}
			
			targetemailstatusdaily  = "SELECT sum(Daily_Target) as daily_target  FROM azc_targets where tyear = '"+year+"' " + 
					"and tmonth = '"+month+"' and status_id='4'";
			
			List<Map<String, Object>> targetemailrowsdaily= jdbcTemplate.queryForList(targetemailstatusdaily);
			for (Map<String, Object> row : targetemailrowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetemaildaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetemaildaily("0");
				}
			}
			
			targetresponsestatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where tyear = '"+year+"' " + 
					"and tmonth = '"+month+"' and status_id='7'";
			
			List<Map<String, Object>> targetresponserowsdaily= jdbcTemplate.queryForList(targetresponsestatusdaily);
			for (Map<String, Object> row : targetresponserowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetresponsedaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetresponsedaily("0");
				}
			}
			
			targetclosestatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where tyear = '"+year+"' " + 
					"and tmonth = '"+month+"' and status_id='14'";
			
			List<Map<String, Object>> targetcloserowsdaily= jdbcTemplate.queryForList(targetclosestatusdaily);
			for (Map<String, Object> row : targetcloserowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetcloseedaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetcloseedaily("0");
				}
			}
			
			targetfollowstatusweekly  = Integer.valueOf(crmstatus.getTargetfollowupdaily()) * 5 ;		
			crmstatus.setTargetfollowupweekly(String.valueOf(targetfollowstatusweekly));
			
			targetemailstatusweekly  = Integer.valueOf(crmstatus.getTargetemaildaily()) * 5 ;		
			crmstatus.setTargetemailweekly(String.valueOf(targetemailstatusweekly));
			
			targetresponsestatusweekly = Integer.valueOf(crmstatus.getTargetresponsedaily()) *5;
			crmstatus.setTargetresponseweekly(String.valueOf(targetresponsestatusweekly));
			
			targetclosestatusweekly = Integer.valueOf(crmstatus.getTargetcloseedaily()) *5;
			crmstatus.setTargetcloseeweekly(String.valueOf(targetclosestatusweekly));
			
			
			targetfollowstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetfollowupdaily());			
			crmstatus.setTargetfollowupmonthly(String.valueOf(targetfollowstatusmonthly));
			
			targetemailstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetemaildaily());
			crmstatus.setTargetemailmonthly(String.valueOf(targetemailstatusmonthly));
			
			targetresponsestatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetresponsedaily());
			crmstatus.setTargetresponsemonthly(String.valueOf(targetresponsestatusmonthly));
			
			targetclosestatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetcloseedaily());
			crmstatus.setTargetcloseemonthly(String.valueOf(targetclosestatusmonthly));
			
		}
		
		String billing_open = "select count(*) from azc_client where Dept_Id =? and Status_Id=?  and year(Modified_Dt)= ? ";		
		int billing_opencount = jdbcTemplate.queryForObject(billing_open, new Object[] {2 , 6, year}, Integer.class);
		crmstatus.setBilling_open(String.valueOf(billing_opencount));
		
		String billing_emailsent = "select count(*) from azc_client where Dept_Id =? and Status_Id=? and year(Modified_Dt)= ? ";		
		int billing_emailcount = jdbcTemplate.queryForObject(billing_emailsent, new Object[] {2 , 4, year}, Integer.class);
		crmstatus.setBilling_email(String.valueOf(billing_emailcount));
		
		String billing_followup = "select count(*) from azc_client where Dept_Id =? and Status_Id in ('3') and year(Modified_Dt)= ? ";		
		int billing_callscount = jdbcTemplate.queryForObject(billing_followup, new Object[] {2 , year}, Integer.class);
		crmstatus.setBilling_followup(String.valueOf(billing_callscount));
		
		String coding_open = "select count(*) from azc_client where Dept_Id =? and Status_Id=? and year(Modified_Dt)= ? ";		
		int coding_opencount = jdbcTemplate.queryForObject(coding_open, new Object[] {4 , 6, year}, Integer.class);
		crmstatus.setCoding_open(String.valueOf(coding_opencount));
		
		String coding_emailsent = "select count(*) from azc_client where Dept_Id =? and Status_Id=? and year(Modified_Dt)= ? ";		
		int coding_emailcount = jdbcTemplate.queryForObject(coding_emailsent, new Object[] {4 , 4, year}, Integer.class);
		crmstatus.setCoding_email(String.valueOf(coding_emailcount));
		
		String coding_followup = "select count(*) from azc_client where Dept_Id =? and Status_Id in ('3')  and year(Modified_Dt)= ? ";		
		int coding_callscount = jdbcTemplate.queryForObject(coding_followup, new Object[] {4 , year}, Integer.class);
		crmstatus.setCoding_followup(String.valueOf(coding_callscount));
		
		String response_year = "select count(*) from azc_client where Status_Id in('3','4', '6') and year(Modified_Dt)='"+year+"' ";		
		int year_count = jdbcTemplate.queryForObject(response_year, new Object[] {}, Integer.class);
		crmstatus.setResponse_year(String.valueOf(year_count));
		
		String response_month = "select count(*) from azc_client where Status_Id in('3','4', '6') and year(Modified_Dt)='"+year+"' and month(Modified_Dt)='"+month+"' ";		
		int month_count = jdbcTemplate.queryForObject(response_month, new Object[] {}, Integer.class);
		crmstatus.setResponse_month(String.valueOf(month_count));	
		
		String closed_year = "select count(*) from azc_client where Status_Id = ? and year(Modified_Dt)='"+year+"' ";		
		int closed_count = jdbcTemplate.queryForObject(closed_year, new Object[] {14}, Integer.class);
		crmstatus.setClosed_year(String.valueOf(closed_count));
		
		String closed_month = "select count(*) from azc_client where Status_Id = ? and year(Modified_Dt)='"+year+"' and month(Modified_Dt)='"+month+"' ";		
		int month_closed = jdbcTemplate.queryForObject(closed_month, new Object[] {14}, Integer.class);
		crmstatus.setClosed_month(String.valueOf(month_closed));	
		
		return crmstatus;
	}

	public CRMStatus checkStatusDescription(CRMStatus status) throws Exception 
	{
		String statusname = status.getStatusDescription();
		
		List<CRMStatus> statusList = new ArrayList<CRMStatus>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from azc_status  where Status_Desc = ?";

		statusList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, statusname);
			}
		}, new CRMStatusRowMapper());
		
		if (statusList != null && statusList.size() > 0) 
		{
			status.setFlag(1);
			return statusList.get(0);
		} 
		else 
		{
			return null;
		}
	}

	public CRMStatus checkEditDuplicateStatusDescription(CRMStatus status) throws Exception 
	{		
		List<CRMStatus> statusList = new ArrayList<CRMStatus>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from azc_status where Status_Desc = ? and status_id != ?";
		statusList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, status.getStatusDescription());
				ps.setString(2, status.getStatusId());
			}
		}, new CRMStatusRowMapper());
		
		if (statusList != null && statusList.size() == 1) 
		{
			status.setFlag(1);
			return statusList.get(0);
		} 
		else 
		{
			return null;
		}
	}

	public List<CRMStatus> checkEditStatusDescription(String closed) throws Exception 
	{
		List<CRMStatus> statuslist = new ArrayList<CRMStatus>();		
		String sql = "select * from azc_status where status_id != "+ 14;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		statuslist = jdbcTemplate.query(sql, new CRMStatusRowMapper());

		return statuslist;
	}

	public CRMStatus getStatusChart(CRMStatus crmstatus) throws Exception 
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar currentdt = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     currentdt.add(Calendar.YEAR,-1);
	     String startDate = f.format(currentdt.getTime());
		
		
		String wonstatus = "select count(*) from azc_client where Status_Id = ? and "
				+ "azc_client.Modified_Dt BETWEEN ? and ? ";
		
		int won = jdbcTemplate.queryForObject(wonstatus, new Object[] {14, startDate, currentDate}, Integer.class);
		crmstatus.setWon(String.valueOf(won));
		
		String coldstatus = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt BETWEEN ? and ?  ";		
		int cold = jdbcTemplate.queryForObject(coldstatus, new Object[] {2, startDate, currentDate}, Integer.class);
		crmstatus.setCold(String.valueOf(cold));
		
		String loststatus = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt BETWEEN ? and ?  ";		
		int lost = jdbcTemplate.queryForObject(loststatus, new Object[] {18, startDate, currentDate}, Integer.class);
		crmstatus.setLost(String.valueOf(lost));
		
		String inprogressstatus = "select count(*) from azc_client where Status_Id in( '3', '4', '6' ,'9','10') and azc_client.Modified_Dt BETWEEN ? and ?  ";		
		int inprogress = jdbcTemplate.queryForObject(inprogressstatus, new Object[] {startDate, currentDate}, Integer.class);
		crmstatus.setInProgress(String.valueOf(inprogress));
		
		return crmstatus;
	}

	public LinkedHashMap<String, List<CRMStatus>> getUserHashmapList(CRMStatus crmstatus)throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");		
		String weekDate = AllzoneCRMUtil.getDate7DaysBefore("yyyy-MM-dd");		
		String monthDate = AllzoneCRMUtil.getmonthDatetime("yyyy-MM-dd");
		
		Calendar c = Calendar.getInstance(  ); 
		int numOfWeeksInMonth = c.getActualMaximum(Calendar.WEEK_OF_MONTH);
		int weeksOfYear = c.getActualMaximum(Calendar.WEEK_OF_YEAR);
		int year = c.get(Calendar.YEAR);
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		
	
		
		long weeksforfirstHalfYearly = AllzoneCRMUtil.getNoofweekforHalfYearly(6, year);
		long weeksforsecondHalfYearly = AllzoneCRMUtil.getNoofweekforHalfYearly(12, year);

		long weeksforfirstQuarterly = AllzoneCRMUtil.getNoofweekforQuarter(3, year);
		long weeksforSecondQuarterly = AllzoneCRMUtil.getNoofweekforQuarter(6, year);
		long weeksforThirdQuarterly = AllzoneCRMUtil.getNoofweekforQuarter(9, year);
		long weeksforfourthQuarterly = AllzoneCRMUtil.getNoofweekforQuarter(12, year);
		
		if(crmstatus.getRoleid() != null && crmstatus.getRoleid().equals("4"))
		{
			crmstatus.setUserid(crmstatus.getUserId());
		}
		
		List<CRMStatus> productivityList= new ArrayList<CRMStatus>();		
		 LinkedHashMap<String, List<CRMStatus>> statusHashmap = new LinkedHashMap<String, List<CRMStatus>>();
		 
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
	    try 
	    {
	    	String userStatus = "";
            String previousStatus = "";
            int i = 0;
            String userId=crmstatus.getUserid(); 
            CRMStatus istatus = new CRMStatus();
            
	         statusHashmap.put("Response", productivityList);
	   		 statusHashmap.put("Email", productivityList);
	   		 statusHashmap.put("Calls", productivityList);
	   		 statusHashmap.put("Data Collection", productivityList);
	   		 
	   		 String sql="select Status_Desc from azc_status where status_id in ('7','3','6','4') order by Status_Desc";
	   		 
	   		 List<Map<String, Object>> row = jdbcTemplate.queryForList(sql);
	   		 
	   		 for (Map<String, Object> rows : row) 
	   		 { 
	    		userStatus = rows.get("Status_Desc").toString();
	    		
	    		if(userStatus != null && userStatus.equals("Appt Fixed"))
	    		{
	    			crmstatus.setStatusId("7");
	    			userStatus = "Response";
	    		}
	    		if(userStatus != null && userStatus.equals("Follow Up"))
	    		{
	    			crmstatus.setStatusId("3");
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
    			   		+ "status_id ='"+crmstatus.getStatusId()+"'  and tmonth = '"+month+"' and tyear = '"+year+"';";
    			   
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
	  				
	  				int targetopenstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(istatus.getTargetopendaily());			
	  				istatus.setTargetopenmonthly(String.valueOf(targetopenstatusmonthly));
	  				
	  				//int targetopenyearly = weeksOfYear * 5 * Integer.valueOf(istatus.getTargetopendaily());
	  				//istatus.setTargetYearlyDeals(String.valueOf(targetopenyearly));	 
	  				
	  				 String targetopenyearly = "select  sum(Daily_Target) as yearlytarget from azc_targets where User_id ='"+userId+"' and "
	     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"'";
	     			   
	 	  	    		  List<Map<String, Object>> yrows = jdbcTemplate.queryForList(targetopenyearly);
	 	  	    		  for (Map<String, Object> row1 : yrows) 
	 	  	    		  {	  	    			 
	 	  	    			  if(row1.get("yearlytarget") != null && !row1.get("yearlytarget").equals(""))
	 	  	    			  {
	 	  	    				int yearly_target = Integer.valueOf(row1.get("yearlytarget").toString()) / month;
	 	  	    				long targetyearly = Long.valueOf(weeksOfYear) * 5 * Integer.valueOf(yearly_target);	 
	 	  	    				
	 	  	    				istatus.setTargetYearlyDeals(String.valueOf(targetyearly));
	 	  	    			  }
	 	  	    			  else
	 	  	    			  {
	 	  	    				istatus.setTargetYearlyDeals("0") ;
	 	  	    			  }
	 	  	    		  }
	 	  	    		  
	 	  	    		
	 	  	    		  
	 	  	    		String targetopenfirstHalfyearly = "select  sum(Daily_Target) as firsthalfyearlytarget from azc_targets where User_id ='"+userId+"' and "
		     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (1,2,3,4,5,6)";
		     			   
		 	  	    		  List<Map<String, Object>> fhrows = jdbcTemplate.queryForList(targetopenfirstHalfyearly);
		 	  	    		  for (Map<String, Object> row1 : fhrows) 
		 	  	    		  {	  	    			 
		 	  	    			  if(row1.get("firsthalfyearlytarget") != null && !row1.get("firsthalfyearlytarget").equals(""))
		 	  	    			  {
		 	  	    				 int First_half_yearly_target = Integer.valueOf(row1.get("firsthalfyearlytarget").toString()) / month;
		 	  	    				 long targetopensfirstHalfyearly = Long.valueOf(weeksforfirstHalfYearly) * 5 * Integer.valueOf(First_half_yearly_target);
		 			  				
		 	  	    				istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly));
		 	  	    			  }
		 	  	    			  else
		 	  	    			  {
		 	  	    				istatus.setTargetFirstHalfYearlyDeals("0") ;
		 	  	    			  }
		 	  	    		  }
		 	  	    		  
		 	  	    	if(month > 6)
		 	  	    	{	  
		 	  	    		String targetopensecondHalfyearly = "select  sum(Daily_Target) as secondhalfyearlytarget from azc_targets where User_id ='"+userId+"' and "
		     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (7,8,9,10,11,12)";
		     			   
		 	  	    		  List<Map<String, Object>> shrows = jdbcTemplate.queryForList(targetopensecondHalfyearly);
		 	  	    		  for (Map<String, Object> row1 : shrows) 
		 	  	    		  {	  	    			 
		 	  	    			  if(row1.get("secondhalfyearlytarget") != null && !row1.get("secondhalfyearlytarget").equals(""))
		 	  	    			  {
		 	  	    				int Second_half_yearly_target = Integer.valueOf(row1.get("secondhalfyearlytarget").toString()) / 6;
		 	  	    				long targetopensfirstHalfyearly = Long.valueOf(weeksforsecondHalfYearly) * 5 * Integer.valueOf(Second_half_yearly_target);
		 	  	    				istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly));
		 	  	    			  }
		 	  	    			  else
		 	  	    			  {
		 	  	    				istatus.setTargetSecondHalfYearlyDeals("0") ;
		 	  	    			  }
		 	  	    		  }
		 	  	    	}
	 	  	    		String targetopenFirstQuarteryearly = "select  sum(Daily_Target) as firstQuarterlytarget from azc_targets where User_id ='"+userId+"' and "
		     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (1,2,3)";
		     			   
		 	  	    		  List<Map<String, Object>> qfrows = jdbcTemplate.queryForList(targetopenFirstQuarteryearly);
		 	  	    		  for (Map<String, Object> row1 : qfrows) 
		 	  	    		  {	  	    			 
		 	  	    			  if(row1.get("firstQuarterlytarget") != null && !row1.get("firstQuarterlytarget").equals(""))
		 	  	    			  {
		 	  	    				 int first_quarterly_target = Integer.valueOf(row1.get("firstQuarterlytarget").toString()) / 3;
		 	  	    				 long targetopensfirstQuarterly = Long.valueOf(weeksforfirstQuarterly) * 5 * Integer.valueOf(first_quarterly_target);
		 	  	    				 istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
		 	  	    			  }
		 	  	    			  else
		 	  	    			  {
		 	  	    				istatus.setTargetFirstQuarterlyDeals("0") ;
		 	  	    			  }
		 	  	    		  }	
		 	  	    	
	 	  	    		String targetopenSecondQuarteryearly = "select  sum(Daily_Target) as secondQuarterlytarget from azc_targets where User_id ='"+userId+"' and "
		     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (4,5,6)";
		     			   
		 	  	    		  List<Map<String, Object>> qsrows = jdbcTemplate.queryForList(targetopenSecondQuarteryearly);
		 	  	    		  for (Map<String, Object> row1 : qsrows) 
		 	  	    		  {	  	    			 
		 	  	    			  if(row1.get("secondQuarterlytarget") != null && !row1.get("secondQuarterlytarget").equals(""))
		 	  	    			  {
		 	  	    				 int second_quarterly_target = Integer.valueOf(row1.get("secondQuarterlytarget").toString()) / 3;
		 	  	    				 long targetopenssecondQuarterly = Long.valueOf(weeksforSecondQuarterly) * 5 * Integer.valueOf(second_quarterly_target);
		 	  	    				 istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));		 	  	    				 
		 	  	    				
		 	  	    			  }
		 	  	    			  else
		 	  	    			  {
		 	  	    				istatus.setTargetSecondQuarterlyDeals("0") ;
		 	  	    			  }
		 	  	    		  }	
		 	  	    	
	 	  	    		String targetopenthirdQuarteryearly = "select  sum(Daily_Target) as thirdQuarterlytarget from azc_targets where User_id ='"+userId+"' and "
		     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (7,8,9)";
		     			   
		 	  	    		  List<Map<String, Object>> qtrows = jdbcTemplate.queryForList(targetopenthirdQuarteryearly);
		 	  	    		  for (Map<String, Object> row1 : qtrows) 
		 	  	    		  {	  	    			 
		 	  	    			  if(row1.get("thirdQuarterlytarget") != null && !row1.get("thirdQuarterlytarget").equals(""))
		 	  	    			  {
		 	  	    				 int third_quarterly_target = Integer.valueOf(row1.get("thirdQuarterlytarget").toString()) / 3;
		 	  	    				 long targetopensthirdQuarterly = Long.valueOf(weeksforThirdQuarterly) * 5 * Integer.valueOf(third_quarterly_target);
		 	  	    				 istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
		 	  	    			  }
		 	  	    			  else
		 	  	    			  {
		 	  	    				istatus.setTargetThirdQuarterlyDeals("0") ;
		 	  	    			  }
		 	  	    		  }	
		 	  	    	
	 	  	    		String targetopenFourthQuarteryearly = "select  sum(Daily_Target) as fourthQuarterlytarget from azc_targets where User_id ='"+userId+"' and "
		     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (10,11,12)";
		     			   
		 	  	    		  List<Map<String, Object>> qforows = jdbcTemplate.queryForList(targetopenFourthQuarteryearly);
		 	  	    		  for (Map<String, Object> row1 : qforows) 
		 	  	    		  {	  	    			 
		 	  	    			  if(row1.get("fourthQuarterlytarget") != null && !row1.get("fourthQuarterlytarget").equals(""))
		 	  	    			  {
		 	  	    				 int fourth_quarterly_target = Integer.valueOf(row1.get("fourthQuarterlytarget").toString()) / 3;
		 	  	    				long targetopensfourthQuarterly = Long.valueOf(weeksforfourthQuarterly) * 5 * Integer.valueOf(fourth_quarterly_target);
		 	  	    				 istatus.setTargetFourthQuarterlyDeals(String.valueOf(targetopensfourthQuarterly));
		 	  	    			  }
		 	  	    			  else
		 	  	    			  {
		 	  	    				istatus.setTargetFourthQuarterlyDeals("0") ;
		 	  	    			  }
		 	  	    		  }	
		  				
	  	    		String actualdaily = "select count(*) as actualtoday from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	  	    				+ "and Modified_Dt='"+currentDate+"' ";
	  	    		  
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
  	    	    		  
  	    	    		String actualweekly = "select count(*) as actualweek from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
  		  	    				+ "and azc_client.Modified_Dt BETWEEN '"+weekDate+"' AND '"+currentDate+"' ";
  		  	    		  
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
  	    	    		
	  	    	    		  
    	    		  String actualmonth = "select count(*) as actualmonth from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
		  	    				+ "and azc_client.Modified_Dt BETWEEN '"+monthDate+"' AND '"+currentDate+"'";
    	    		  
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
 	    	    		  
 	    	    		 String actualyearly = "select count(*) as actualyear from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
 		  	    				+ "and year(Modified_Dt) ='"+year+"' ";
     	    		  
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
  	    	    		
  	    	    		String actualfirstHalfyearly = "select count(*) as actualfirstHalfyear from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
 		  	    				+ "and month(Modified_Dt) BETWEEN '1' AND '6' and year(Modified_Dt)='"+year+"' ";
     	    		  
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
  	    	    		  
  	    	    		
	  	    	    		String actualsecondHalfyearly = "select count(*) as actualsecondHalfyear from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and month(Modified_Dt) BETWEEN '6' AND '12'  and year(Modified_Dt)='"+year+"' ";
	     	    		  
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
  	    	    		
	  	    	    		String actualFirstQuarter = "select count(*) as actualFirstquarter from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and month(Modified_Dt) BETWEEN '1' AND '3' and year(Modified_Dt)='"+year+"' ";
	     	    		  
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
	  	    	    		
	  	    	    		String actualSecondQuarter = "select count(*) as actualSecondquarter from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and month(Modified_Dt) BETWEEN '3' AND '6' and year(Modified_Dt)='"+year+"'  ";
	     	    		  
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
	  	    	    		  
	  	    	    		String actualThirdQuarter = "select count(*) as actualThirdquarter from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and month(Modified_Dt) BETWEEN '6' AND '9' and year(Modified_Dt)='"+year+"'";
	     	    		  
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
	  	    	    		
	  	    	    		String actualFourthQuarter = "select count(*) as actualFourthquarter from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and month(Modified_Dt) BETWEEN '9' AND '12' and year(Modified_Dt)='"+year+"' ";
	     	    		  
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
	  	    			
	  	    			
	  	    	    	String actualDate = "select count(*) as dailyTarget from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
 		  	    				+ "and Modified_Dt BETWEEN '"+fromddate+"' AND '"+todate+"' ";
     	    		  
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
	  	    	    				"status_id ='"+crmstatus.getStatusId()+"' and tmonth between '"+frommonth+"' and '"+tomonth+"'";
	  	    	    		
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
	      			   		+ "status_id ='"+crmstatus.getStatusId()+"'  and tmonth = '"+month+"' and tyear = '"+year+"';";
	    			 
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
	  	  				
	  	  				int targetopenstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(istatus.getTargetopendaily());			
	  	  				istatus.setTargetopenmonthly(String.valueOf(targetopenstatusmonthly));
	  	  				
	  	  			 String targetopenyearly = "select  sum(Daily_Target) as yearlytarget from azc_targets where User_id ='"+userId+"' and "
		     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"';";
		     			   
		 	  	    		  List<Map<String, Object>> yrows = jdbcTemplate.queryForList(targetopenyearly);
		 	  	    		  for (Map<String, Object> row1 : yrows) 
		 	  	    		  {	  	    			 
		 	  	    			  if(row1.get("yearlytarget") != null && !row1.get("yearlytarget").equals(""))
		 	  	    			  {
		 	  	    				  	int yearly_target = Integer.valueOf(row1.get("yearlytarget").toString()) / month;
			 	  	    				long targetyearly = Long.valueOf(weeksOfYear) * 5 * Integer.valueOf(yearly_target);	 	  	    				
			 	  	    				istatus.setTargetYearlyDeals(String.valueOf(targetyearly));
		 	  	    			  }
		 	  	    			  else
		 	  	    			  {
		 	  	    				istatus.setTargetYearlyDeals("0") ;
		 	  	    			  }
		 	  	    		  }
		 	  	    		  
		 	  	    		String targetopenfirstHalfyearly = "select  sum(Daily_Target) as firsthalfyearlytarget from azc_targets where User_id ='"+userId+"' and "
			     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (1,2,3,4,5,6)";
			     			   
			 	  	    		  List<Map<String, Object>> fhrows = jdbcTemplate.queryForList(targetopenfirstHalfyearly);
			 	  	    		  for (Map<String, Object> row1 : fhrows) 
			 	  	    		  {	  	    			 
			 	  	    			  if(row1.get("firsthalfyearlytarget") != null && !row1.get("firsthalfyearlytarget").equals(""))
			 	  	    			  {
			 	  	    				 int First_half_yearly_target = Integer.valueOf(row1.get("firsthalfyearlytarget").toString()) / month;
			 	  	    				 long targetopensfirstHalfyearly = Long.valueOf(weeksforfirstHalfYearly) * 5 * Integer.valueOf(First_half_yearly_target);
			 			  				
			 	  	    				istatus.setTargetFirstHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly));
			 	  	    			  }
			 	  	    			  else
			 	  	    			  {
			 	  	    				istatus.setTargetFirstHalfYearlyDeals("0") ;
			 	  	    			  }
			 	  	    		  }
			 	  	    	
			 	  	    		  if(month > 6)
			 	  	    		  {
			 	  	    			  String targetopensecondHalfyearly = "select  sum(Daily_Target) as secondhalfyearlytarget from azc_targets where User_id ='"+userId+"' and "
			     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (7,8,9,10,11,12)";
			     			   
			 	  	    		  List<Map<String, Object>> shrows = jdbcTemplate.queryForList(targetopensecondHalfyearly);
			 	  	    		  for (Map<String, Object> row1 : shrows) 
			 	  	    		  {	  	    			 
			 	  	    			  if(row1.get("secondhalfyearlytarget") != null && !row1.get("secondhalfyearlytarget").equals(""))
			 	  	    			  {
			 	  	    				int Second_half_yearly_target = Integer.valueOf(row1.get("secondhalfyearlytarget").toString()) / 6;
			 	  	    				long targetopensfirstHalfyearly = Long.valueOf(weeksforsecondHalfYearly) * 5 * Integer.valueOf(Second_half_yearly_target);
			 	  	    				istatus.setTargetSecondHalfYearlyDeals(String.valueOf(targetopensfirstHalfyearly));
			 	  	    			  }
			 	  	    			  else
			 	  	    			  {
			 	  	    				istatus.setTargetSecondHalfYearlyDeals("0") ;
			 	  	    			  }
			 	  	    		  }
			 	  	    		 }
			 	  	    	
		 	  	    		String targetopenFirstQuarteryearly = "select  sum(Daily_Target) as firstQuarterlytarget from azc_targets where User_id ='"+userId+"' and "
			     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (1,2,3)";
			     			   
			 	  	    		  List<Map<String, Object>> qfrows = jdbcTemplate.queryForList(targetopenFirstQuarteryearly);
			 	  	    		  for (Map<String, Object> row1 : qfrows) 
			 	  	    		  {	  	    			 
			 	  	    			  if(row1.get("firstQuarterlytarget") != null && !row1.get("firstQuarterlytarget").equals(""))
			 	  	    			  {
			 	  	    				 int first_quarterly_target = Integer.valueOf(row1.get("firstQuarterlytarget").toString()) / 3;
			 	  	    				 long targetopensfirstQuarterly = Long.valueOf(weeksforfirstQuarterly) * 5 * Integer.valueOf(first_quarterly_target);
			 	  	    				 istatus.setTargetFirstQuarterlyDeals(String.valueOf(targetopensfirstQuarterly));
			 	  	    			  }
			 	  	    			  else
			 	  	    			  {
			 	  	    				istatus.setTargetFirstQuarterlyDeals("0") ;
			 	  	    			  }
			 	  	    		  }	
			 	  	    	
		 	  	    		String targetopenSecondQuarteryearly = "select  sum(Daily_Target) as secondQuarterlytarget from azc_targets where User_id ='"+userId+"' and "
			     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (4,5,6)";
			     			   
			 	  	    		  List<Map<String, Object>> qsrows = jdbcTemplate.queryForList(targetopenSecondQuarteryearly);
			 	  	    		  for (Map<String, Object> row1 : qsrows) 
			 	  	    		  {	  	    			 
			 	  	    			  if(row1.get("secondQuarterlytarget") != null && !row1.get("secondQuarterlytarget").equals(""))
			 	  	    			  {
			 	  	    				 int second_quarterly_target = Integer.valueOf(row1.get("secondQuarterlytarget").toString()) / 3;		 	  	    				
			 	  	    				 long targetopenssecondQuarterly = Long.valueOf(weeksforSecondQuarterly) * 5 * Integer.valueOf(second_quarterly_target);
			 	  	    				 istatus.setTargetSecondQuarterlyDeals(String.valueOf(targetopenssecondQuarterly));
			 	  	    			  }
			 	  	    			  else
			 	  	    			  {
			 	  	    				istatus.setTargetSecondQuarterlyDeals("0") ;
			 	  	    			  }
			 	  	    		  }	
			 	  	    	
		 	  	    		String targetopenthirdQuarteryearly = "select  sum(Daily_Target) as thirdQuarterlytarget from azc_targets where User_id ='"+userId+"' and "
			     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (7,8,9)";
			     			   
			 	  	    		  List<Map<String, Object>> qtrows = jdbcTemplate.queryForList(targetopenthirdQuarteryearly);
			 	  	    		  for (Map<String, Object> row1 : qtrows) 
			 	  	    		  {	  	    			 
			 	  	    			  if(row1.get("thirdQuarterlytarget") != null && !row1.get("thirdQuarterlytarget").equals(""))
			 	  	    			  {
			 	  	    				 int third_quarterly_target = Integer.valueOf(row1.get("thirdQuarterlytarget").toString()) / 3;
			 	  	    				 long targetopensthirdQuarterly = Long.valueOf(weeksforThirdQuarterly) * 5 * Integer.valueOf(third_quarterly_target);
			 	  	    				 istatus.setTargetThirdQuarterlyDeals(String.valueOf(targetopensthirdQuarterly));
			 	  	    			  }
			 	  	    			  else
			 	  	    			  {
			 	  	    				istatus.setTargetThirdQuarterlyDeals("0") ;
			 	  	    			  }
			 	  	    		  }	
			 	  	    	
		 	  	    		String targetopenFourthQuarteryearly = "select  sum(Daily_Target) as fourthQuarterlytarget from azc_targets where User_id ='"+userId+"' and "
			     			   		+ "status_id ='"+crmstatus.getStatusId()+"' and tyear = '"+year+"' and tmonth in (10,11,12)";
			     			   
			 	  	    		  List<Map<String, Object>> qforows = jdbcTemplate.queryForList(targetopenFourthQuarteryearly);
			 	  	    		  for (Map<String, Object> row1 : qforows) 
			 	  	    		  {	  	    			 
			 	  	    			  if(row1.get("fourthQuarterlytarget") != null && !row1.get("fourthQuarterlytarget").equals(""))
			 	  	    			  {
			 	  	    				 int fourth_quarterly_target = Integer.valueOf(row1.get("fourthQuarterlytarget").toString()) / 3;
			 	  	    				long targetopensfourthQuarterly = Long.valueOf(weeksforfourthQuarterly) * 5 * Integer.valueOf(fourth_quarterly_target);
			 	  	    				 istatus.setTargetFourthQuarterlyDeals(String.valueOf(targetopensfourthQuarterly));
			 	  	    			  }
			 	  	    			  else
			 	  	    			  {
			 	  	    				istatus.setTargetFourthQuarterlyDeals("0") ;
			 	  	    			  }
			 	  	    		  }	
	  	  				
	  	  	    		String actualdaily = "select count(*) as actualtoday from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	  	  	    				+ "and Modified_Dt='"+currentDate+"' ";
	  	  	    		  
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
	    	    	    		  
	    	    	    		String actualweekly = "select count(*) as actualweek from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	    		  	    				+ "and azc_client.Modified_Dt BETWEEN '"+weekDate+"' AND '"+currentDate+"' ";
	    		  	    		  
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
	    	    	    		
	  	  	    	    		  
	      	    		  String actualmonth = "select count(*) as actualmonth from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	  		  	    				+ "and azc_client.Modified_Dt BETWEEN '"+monthDate+"' AND '"+currentDate+"'";
	      	    		  
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
	   	    	    		  
	   	    	    		String actualyearly = "select count(*) as actualyear from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and year(Modified_Dt) ='"+year+"' ";
	     	    		  
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
	  	    	    		  
	  	    	    		String actualfirstHalfyearly = "select count(*) as actualfirstHalfyear from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and month(Modified_Dt) BETWEEN '1' AND '6' and year(Modified_Dt)='"+year+"' ";
	     	    		  
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
	  	    	    		  
  	    	    			String actualsecondHalfyearly = "select count(*) as actualsecondHalfyear from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and  month(Modified_Dt) BETWEEN '6' AND '12' and year(Modified_Dt)='"+year+"' ";
	     	    		  
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
  	    	    		
	  	    	    		  
	  	    	    		String actualFirstQuarter = "select count(*) as actualFirstquarter from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and month(Modified_Dt) BETWEEN '1' AND '3' and year(Modified_Dt)='"+year+"' ";
	     	    		  
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
	  	    	    		
	  	    	    		String actualSecondQuarter = "select count(*) as actualSecondquarter from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and month(Modified_Dt) BETWEEN '3' AND '6' and year(Modified_Dt)='"+year+"' ";
	     	    		  
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
	  	    	    		  
	  	    	    		String actualThirdQuarter = "select count(*) as actualThirdquarter from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and month(Modified_Dt) BETWEEN '6' AND '9' and year(Modified_Dt)='"+year+"' ";
	     	    		  
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
	  	    	    		
	  	    	    		String actualFourthQuarter = "select count(*) as actualFourthquarter from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	 		  	    				+ "and month(Modified_Dt) BETWEEN '9' AND '12' and year(Modified_Dt)='"+year+"' ";
	     	    		  
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
	  		  	    			
	  		  	    			
	  		  	    	    	String actualDate = "select count(*) as dailyTarget from azc_client where User_id ='"+userId+"' and status_id ='"+crmstatus.getStatusId()+"' "
	  	 		  	    				+ "and Modified_Dt BETWEEN '"+fromddate+"' AND '"+todate+"' ";	  		  	    	    	
	  	     	    		  
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
	  	  	    	    				"status_id ='"+crmstatus.getStatusId()+"' and tmonth between '"+frommonth+"' and '"+tomonth+"'";
	  	  	    	    		
	  	  	    	    	
	  	  	    	    		
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
	     }
		    catch (Exception e) 
		    {
	            e.printStackTrace();
		    }		
		return statusHashmap;
	}

	/*public CRMStatus getManagerViewdashboardDataList(CRMStatus crmstatus) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");		
		String weekDate = AllzoneCRMUtil.getDate7DaysBefore("yyyy-MM-dd");		
		String monthDate = AllzoneCRMUtil.getmonthDatetime("yyyy-MM-dd");
		
		Calendar c = Calendar.getInstance(  ); 
		int numOfWeeksInMonth = c.getActualMaximum(Calendar.WEEK_OF_MONTH);
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		if(crmstatus.getUserid() != null && !crmstatus.equals(""))
		{
			String openstatusdaily  = "select count(*) as daily_target from azc_client where Status_Id='6' and Created_Dt='"+currentDate+"' "
					+ "and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> targetopenrowsdaily= jdbcTemplate.queryForList(openstatusdaily);
			for (Map<String, Object> row : targetopenrowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setOpendealsdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setOpendealsdaily("0");
				}
			}
			
			String openstatusweekly  = "select count(*) as daily_target from azc_client where status_id = '6' and "
					+ "Created_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> openrowsweekly = jdbcTemplate.queryForList(openstatusweekly);
			for (Map<String, Object> row : openrowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setOpendealsweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setOpendealsweekly("0");
				}
			}
			
			String openstatusmonthly  = "select count(*) as daily_target from azc_client where status_id = '6' and "
					+ "Created_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> openrowsmonthly = jdbcTemplate.queryForList(openstatusmonthly);
			for (Map<String, Object> row : openrowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setOpendealsmonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setOpendealsmonthly("0");
				}
			}
			
			String targetopenstatusdaily= "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
					"effective_start_dt <= '"+currentDate+"' and effective_end_dt >= '"+currentDate+"' and status_id='6' and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> openrows = jdbcTemplate.queryForList(targetopenstatusdaily);
			for (Map<String, Object> row : openrows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetopendaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetopendaily("0");
				}
			}
			
			int targetopenstatusweekly  = Integer.valueOf(crmstatus.getTargetopendaily()) * 5 ;		
			crmstatus.setTargetopenweekly(String.valueOf(targetopenstatusweekly));			
			
			int targetopenstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetopendaily());			
			crmstatus.setTargetopenmonthly(String.valueOf(targetopenstatusmonthly));
			
			String followupstatusdaily = "select count(*) as daily_target from azc_client where status_id = '3' and Modified_Dt='"+currentDate+"' "
					+ "and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> followuprows = jdbcTemplate.queryForList(followupstatusdaily);
			for (Map<String, Object> row : followuprows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{
					crmstatus.setFollowupdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setFollowupdaily("0");
				}
			}
			
			String emailstatusdaily  = "select count(*) as daily_target from azc_client where status_id = '4' and Modified_Dt='"+currentDate+"' "
					+ "and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> mailrows = jdbcTemplate.queryForList(emailstatusdaily);
			for (Map<String, Object> row : mailrows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{ 
					crmstatus.setEmailsentdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setEmailsentdaily("0");
				}
			}
			
			String responsestatusdaily  = "select count(*) as daily_target from azc_client where status_id = '7' and Modified_Dt='"+currentDate+"' "
					+ "and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> responserows = jdbcTemplate.queryForList(responsestatusdaily);
			for (Map<String, Object> row : responserows) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setResponsedaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setResponsedaily("0");
				}
			}
			
				
			String followupstatusweekly  = "select count(*) as daily_target from azc_client where status_id = '3' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> followuprowsweekly = jdbcTemplate.queryForList(followupstatusweekly);
			for (Map<String, Object> row : followuprowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setFollowupweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setFollowupweekly("0");
				}
			}
			
			String emailstatusweekly  = "select count(*) as daily_target from azc_client where status_id = '4' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> emailrowsweekly = jdbcTemplate.queryForList(emailstatusweekly);
			for (Map<String, Object> row : emailrowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setEmailsentweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setEmailsentweekly("0");
				}
			}
			
			String responsestatusweekly  = "select count(*) as daily_target from azc_client where status_id = '7' and "
					+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> responserowsweekly = jdbcTemplate.queryForList(responsestatusweekly);
			for (Map<String, Object> row : responserowsweekly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setResponseweekly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setResponseweekly("0");
				}
			}
			
			String followupstatusmonthly  = "select count(*) as daily_target from azc_client where status_id = '3' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> followrowsmonthly = jdbcTemplate.queryForList(followupstatusmonthly);
			for (Map<String, Object> row : followrowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setFollowupmonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setFollowupmonthly("0");
				}
			}
			
			String emailstatusmonthly  = "select count(*) as daily_target from azc_client where status_id = '4' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> emailrowsmonthly = jdbcTemplate.queryForList(emailstatusmonthly);
			for (Map<String, Object> row : emailrowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setEmailsentmonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setEmailsentmonthly("0");
				}
			}
			
			String responsestatusmonthly  = "select count(*) as daily_target from azc_client where status_id = '7' and "
					+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
			
			List<Map<String, Object>> responserowsmonthly = jdbcTemplate.queryForList(responsestatusmonthly);
			for (Map<String, Object> row : responserowsmonthly) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setReponsemonthly(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setReponsemonthly("0");
				}
			}
			
			String targetfollowstatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
					"effective_start_dt <= '"+currentDate+"' and effective_end_dt >= '"+currentDate+"' and status_id='3' and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> targetfollowuprowsdaily= jdbcTemplate.queryForList(targetfollowstatusdaily);
			for (Map<String, Object> row : targetfollowuprowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetfollowupdaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetfollowupdaily("0");
				}
			}
			
			String targetemailstatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
					"effective_start_dt <= '"+currentDate+"' and effective_end_dt >= '"+currentDate+"' and status_id='4' and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> targetemailrowsdaily= jdbcTemplate.queryForList(targetemailstatusdaily);
			for (Map<String, Object> row : targetemailrowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetemaildaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetemaildaily("0");
				}
			}
			
			String targetresponsestatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
					"effective_start_dt <= '"+currentDate+"' and effective_end_dt >= '"+currentDate+"' and status_id='7' and User_id = '"+crmstatus.getUserid()+"'";
			
			List<Map<String, Object>> targetresponserowsdaily= jdbcTemplate.queryForList(targetresponsestatusdaily);
			for (Map<String, Object> row : targetresponserowsdaily) 
			{
				if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
				{				
					crmstatus.setTargetresponsedaily(row.get("daily_target").toString());
				}
				else
				{
					crmstatus.setTargetresponsedaily("0");
				}
			}
			
			int targetfollowstatusweekly  = Integer.valueOf(crmstatus.getTargetfollowupdaily()) * 5 ;		
			crmstatus.setTargetfollowupweekly(String.valueOf(targetfollowstatusweekly));
			
			int targetemailstatusweekly  = Integer.valueOf(crmstatus.getTargetemaildaily()) * 5 ;		
			crmstatus.setTargetemailweekly(String.valueOf(targetemailstatusweekly));
			
			 int targetresponsestatusweekly = Integer.valueOf(crmstatus.getTargetresponsedaily()) *5;
			crmstatus.setTargetresponseweekly(String.valueOf(targetresponsestatusweekly));
			
			
			int targetfollowstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetfollowupdaily());			
			crmstatus.setTargetfollowupmonthly(String.valueOf(targetfollowstatusmonthly));
			
			int targetemailstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetemaildaily());
			crmstatus.setTargetemailmonthly(String.valueOf(targetemailstatusmonthly));
			
			int targetresponsestatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetresponsedaily());
			crmstatus.setTargetresponsemonthly(String.valueOf(targetresponsestatusmonthly));
		
		}
		return crmstatus;
	}*/

	public CRMStatus getDashboardDetailsformanager(CRMStatus crmstatus) throws Exception 
	{
		LocalDate date = LocalDate.now().minusDays(3);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//int year = Calendar.getInstance().get(Calendar.YEAR);
		
		//System.out.println("date 3 days before "+ date.toString());
		
		 String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");
		 Calendar currentdt = Calendar.getInstance();
	     Format f = new SimpleDateFormat("yyyy-MM-dd");
	     currentdt.add(Calendar.YEAR,-1);
	     String endDate = f.format(currentdt.getTime());
		
		String openstatus = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";		
		int opendeals = jdbcTemplate.queryForObject(openstatus, new Object[] {6, endDate,currentDate}, Integer.class);
		crmstatus.setOpendeals(String.valueOf(opendeals));
		
		String followupstatus = "select count(*) from azc_client where Status_Id in ('3') and azc_client.Modified_Dt BETWEEN ? AND ?";			
		int followup = jdbcTemplate.queryForObject(followupstatus, new Object[] { endDate,currentDate}, Integer.class);
		crmstatus.setFollowup(String.valueOf(followup));
			
		String emailstatus = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";			
		int email = jdbcTemplate.queryForObject(emailstatus, new Object[] {4, endDate,currentDate}, Integer.class);
		crmstatus.setEmailsent(String.valueOf(email));
			
		String	overduefollowup = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt <=? and azc_client.Modified_Dt BETWEEN ? AND ? ";			
		int followupoverdue = jdbcTemplate.queryForObject(overduefollowup, new Object[] {4 ,date.toString(),endDate,currentDate}, Integer.class);
		crmstatus.setOverduefollowup(String.valueOf(followupoverdue));
			
		String overdueemail = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt <=? and azc_client.Modified_Dt BETWEEN ? AND ? and e_mail !='' ";			
		int emailoverdue = jdbcTemplate.queryForObject(overdueemail, new Object[] {6, date.toString(),endDate,currentDate}, Integer.class);
		crmstatus.setOverdueemail(String.valueOf(emailoverdue));
		
		String closedstatus = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";		
		int closeddeals = jdbcTemplate.queryForObject(closedstatus, new Object[] {14, endDate,currentDate}, Integer.class);
		crmstatus.setClosedDeals(String.valueOf(closeddeals));
		
		String hotleadsstatus = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";			
		int ihotleads = jdbcTemplate.queryForObject(hotleadsstatus, new Object[] {30, endDate,currentDate}, Integer.class);
		crmstatus.setHotleads(String.valueOf(ihotleads));
		
		String droppedstatus = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt BETWEEN ? AND ?";			
		int idroppedleads = jdbcTemplate.queryForObject(droppedstatus, new Object[] {31, endDate,currentDate}, Integer.class);
		crmstatus.setDroppedleads(String.valueOf(idroppedleads));
		
		/*String overdueDealstatus = "select count(*) from azc_client where Status_Id = ? and azc_client.Modified_Dt <=? ";
		int overduedeals = jdbcTemplate.queryForObject(overdueDealstatus, new Object[] {6, date.toString()}, Integer.class);
		crmstatus.setOverdueDeal(String.valueOf(overduedeals));*/
		
		return crmstatus;
	}

	public List<CRMStatus> getTargetStatusList() throws Exception 
	{
		List<CRMStatus> statuslist = new ArrayList<CRMStatus>();
		
		String sql = "select * from azc_status where status_id in ('3','4','6','9','14') order by status_id ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) 
		{
			CRMStatus status = new CRMStatus();	
			
			
			if(row.get("Status_Desc") != null && !row.get("Status_Desc").equals(""))
			{
				
				String strstatusdesc = row.get("Status_Desc").toString();
				String strstatusid = row.get("status_id").toString();
				
				status.setStatusId(strstatusid);
				if(strstatusdesc.equals("Open"))
				{
					status.setStatusName("Data Collection");
				}
				else
				{
					status.setStatusName(strstatusdesc);
				}

			}
			
			
			statuslist.add(status);
		}

		return statuslist;
	}

	/*@Override
	public CRMStatus getUserDashboardDataList(CRMStatus crmstatus) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd");		
		String weekDate = AllzoneCRMUtil.getDate7DaysBefore("yyyy-MM-dd");		
		String monthDate = AllzoneCRMUtil.getmonthDatetime("yyyy-MM-dd");
		
		Calendar c = Calendar.getInstance(  ); 
		int numOfWeeksInMonth = c.getActualMaximum(Calendar.WEEK_OF_MONTH);
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String openstatusdaily  = "select count(*) as daily_target from azc_client where Status_Id='6' and Created_Dt='"+currentDate+"'"
				+ "and User_id = '"+crmstatus.getUserid()+"'";
		
		List<Map<String, Object>> targetopenrowsdaily= jdbcTemplate.queryForList(openstatusdaily);
		for (Map<String, Object> row : targetopenrowsdaily) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setOpendealsdaily(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setOpendealsdaily("0");
			}
		}
		
		String openstatusweekly  = "select count(*) as daily_target from azc_client where status_id = '6' and "
				+ "Created_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
		
		List<Map<String, Object>> openrowsweekly = jdbcTemplate.queryForList(openstatusweekly);
		for (Map<String, Object> row : openrowsweekly) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setOpendealsweekly(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setOpendealsweekly("0");
			}
		}
		
		String openstatusmonthly  = "select count(*) as daily_target from azc_client where status_id = '6' and "
				+ "Created_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
		
		List<Map<String, Object>> openrowsmonthly = jdbcTemplate.queryForList(openstatusmonthly);
		for (Map<String, Object> row : openrowsmonthly) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setOpendealsmonthly(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setOpendealsmonthly("0");
			}
		}
		
		String targetopenstatusdaily= "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
				"effective_start_dt <= '"+currentDate+"' and effective_end_dt >= '"+currentDate+"' and status_id='6' and User_id = '"+crmstatus.getUserid()+"'";
		
		List<Map<String, Object>> openrows = jdbcTemplate.queryForList(targetopenstatusdaily);
		for (Map<String, Object> row : openrows) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setTargetopendaily(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setTargetopendaily("0");
			}
		}
		
		int targetopenstatusweekly  = Integer.valueOf(crmstatus.getTargetopendaily()) * 5 ;		
		crmstatus.setTargetopenweekly(String.valueOf(targetopenstatusweekly));			
		
		int targetopenstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetopendaily());			
		crmstatus.setTargetopenmonthly(String.valueOf(targetopenstatusmonthly));
		
		String followupstatusdaily = "select count(*) as daily_target from azc_client where status_id = '3' and Modified_Dt='"+currentDate+"'"
				+ "and User_id = '"+crmstatus.getUserid()+"'";
		
		List<Map<String, Object>> followuprows = jdbcTemplate.queryForList(followupstatusdaily);
		for (Map<String, Object> row : followuprows) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{
				crmstatus.setFollowupdaily(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setFollowupdaily("0");
			}
		}
		
		String emailstatusdaily  = "select count(*) as daily_target from azc_client where status_id = '4' and Modified_Dt='"+currentDate+"'"
				+ "and User_id = '"+crmstatus.getUserid()+"'";
		
		List<Map<String, Object>> mailrows = jdbcTemplate.queryForList(emailstatusdaily);
		for (Map<String, Object> row : mailrows) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{ 
				crmstatus.setEmailsentdaily(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setEmailsentdaily("0");
			}
		}
		
		String responsestatusdaily  = "select count(*) as daily_target from azc_client where status_id = '7' and Modified_Dt='"+currentDate+"'"
				+ "and User_id = '"+crmstatus.getUserid()+"' ";
		
		List<Map<String, Object>> responserows = jdbcTemplate.queryForList(responsestatusdaily);
		for (Map<String, Object> row : responserows) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setResponsedaily(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setResponsedaily("0");
			}
		}
		
			
		String followupstatusweekly  = "select count(*) as daily_target from azc_client where status_id = '3' and "
				+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
		
		List<Map<String, Object>> followuprowsweekly = jdbcTemplate.queryForList(followupstatusweekly);
		for (Map<String, Object> row : followuprowsweekly) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setFollowupweekly(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setFollowupweekly("0");
			}
		}
		
		String emailstatusweekly  = "select count(*) as daily_target from azc_client where status_id = '4' and "
				+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
		
		List<Map<String, Object>> emailrowsweekly = jdbcTemplate.queryForList(emailstatusweekly);
		for (Map<String, Object> row : emailrowsweekly) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setEmailsentweekly(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setEmailsentweekly("0");
			}
		}
		
		String responsestatusweekly  = "select count(*) as daily_target from azc_client where status_id = '7' and "
				+ "Modified_Dt BETWEEN '"+weekDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
		
		List<Map<String, Object>> responserowsweekly = jdbcTemplate.queryForList(responsestatusweekly);
		for (Map<String, Object> row : responserowsweekly) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setResponseweekly(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setResponseweekly("0");
			}
		}
		
		String followupstatusmonthly  = "select count(*) as daily_target from azc_client where status_id = '3' and "
				+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
		
		List<Map<String, Object>> followrowsmonthly = jdbcTemplate.queryForList(followupstatusmonthly);
		for (Map<String, Object> row : followrowsmonthly) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setFollowupmonthly(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setFollowupmonthly("0");
			}
		}
		
		String emailstatusmonthly  = "select count(*) as daily_target from azc_client where status_id = '4' and "
				+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
		
		List<Map<String, Object>> emailrowsmonthly = jdbcTemplate.queryForList(emailstatusmonthly);
		for (Map<String, Object> row : emailrowsmonthly) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setEmailsentmonthly(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setEmailsentmonthly("0");
			}
		}
		
		String responsestatusmonthly  = "select count(*) as daily_target from azc_client where status_id = '7' and "
				+ "Modified_Dt BETWEEN '"+monthDate+"' AND  '"+currentDate+"' and User_id = '"+crmstatus.getUserid()+"' ";
		
		List<Map<String, Object>> responserowsmonthly = jdbcTemplate.queryForList(responsestatusmonthly);
		for (Map<String, Object> row : responserowsmonthly) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setReponsemonthly(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setReponsemonthly("0");
			}
		}
		
		String targetfollowstatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
				"effective_start_dt <= '"+currentDate+"' and effective_end_dt >= '"+currentDate+"' and status_id='3' and User_id = '"+crmstatus.getUserid()+"'";
		
		List<Map<String, Object>> targetfollowuprowsdaily= jdbcTemplate.queryForList(targetfollowstatusdaily);
		for (Map<String, Object> row : targetfollowuprowsdaily) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setTargetfollowupdaily(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setTargetfollowupdaily("0");
			}
		}
		
		String targetemailstatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
				"effective_start_dt <= '"+currentDate+"' and effective_end_dt >= '"+currentDate+"' and status_id='4' and User_id = '"+crmstatus.getUserid()+"'";
		
		List<Map<String, Object>> targetemailrowsdaily= jdbcTemplate.queryForList(targetemailstatusdaily);
		for (Map<String, Object> row : targetemailrowsdaily) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setTargetemaildaily(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setTargetemaildaily("0");
			}
		}
		
		String targetresponsestatusdaily  = "SELECT sum(Daily_Target) as daily_target FROM azc_targets where " + 
				"effective_start_dt <= '"+currentDate+"' and effective_end_dt >= '"+currentDate+"' and status_id='7' and User_id = '"+crmstatus.getUserid()+"'";
		
		List<Map<String, Object>> targetresponserowsdaily= jdbcTemplate.queryForList(targetresponsestatusdaily);
		for (Map<String, Object> row : targetresponserowsdaily) 
		{
			if(row.get("daily_target") != null && !row.get("daily_target").equals(""))
			{				
				crmstatus.setTargetresponsedaily(row.get("daily_target").toString());
			}
			else
			{
				crmstatus.setTargetresponsedaily("0");
			}
		}
		
		int targetfollowstatusweekly  = Integer.valueOf(crmstatus.getTargetfollowupdaily()) * 5 ;		
		crmstatus.setTargetfollowupweekly(String.valueOf(targetfollowstatusweekly));
		
		int targetemailstatusweekly  = Integer.valueOf(crmstatus.getTargetemaildaily()) * 5 ;		
		crmstatus.setTargetemailweekly(String.valueOf(targetemailstatusweekly));
		
		int targetresponsestatusweekly = Integer.valueOf(crmstatus.getTargetresponsedaily()) *5;
		crmstatus.setTargetresponseweekly(String.valueOf(targetresponsestatusweekly));
		
		
		int targetfollowstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetfollowupdaily());			
		crmstatus.setTargetfollowupmonthly(String.valueOf(targetfollowstatusmonthly));
		
		int targetemailstatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetemaildaily());
		crmstatus.setTargetemailmonthly(String.valueOf(targetemailstatusmonthly));
		
		int targetresponsestatusmonthly  = numOfWeeksInMonth * 5 * Integer.valueOf(crmstatus.getTargetresponsedaily());
		crmstatus.setTargetresponsemonthly(String.valueOf(targetresponsestatusmonthly));
		
		return crmstatus;
		
	}*/

	public List<CRMStatus> getClientStatusList() throws Exception 
	{
		List<CRMStatus> statuslist = new ArrayList<CRMStatus>();
		
		String sql = "select * from azc_status where status_id in ('3','4','6','9','10','14','18','19') ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		statuslist = jdbcTemplate.query(sql, new CRMStatusRowMapper());

		return statuslist;
	}

	public void inactiveStatus(CRMStatus status) throws Exception
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		 String sqlselect = "SELECT Status FROM azc_status where status_id='"+status.getStatusId()+"' ";
			
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlselect);
		String crmstatus="";
		for (Map<String, Object> row : rows) 
		{
			crmstatus = row.get("Status").toString();
		}
		if(crmstatus.equals("A"))
		{
			crmstatus = "I";
		}
		else
		{
			crmstatus = "A";
		}
		
		String sql = "UPDATE azc_status set Status=?, "
			+" Modified_By = ?, Modified_Dt = ? where status_id = ? ";

		jdbcTemplate.update( sql, new Object[] {crmstatus, status.getLoginName(), currentDate, status.getStatusId()});
		
	}

	public List<CRMStatus> getStatusListwithInactive() throws Exception 
	{
		List<CRMStatus> statuslist = new ArrayList<CRMStatus>();
		
		String sql = "select * from azc_status where Status_Desc!='All'";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		statuslist = jdbcTemplate.query(sql, new CRMStatusRowMapper());

		return statuslist;
	}

	public List<CRMStatus> getStatusListExceptopen(String statusIdOpen) throws Exception 
	{
		List<CRMStatus> statuslist = new ArrayList<CRMStatus>();
		
		String sql = "select * from azc_status where Status_Desc != 'ALL' and Status = 'A' and status_id !="+statusIdOpen;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		statuslist = jdbcTemplate.query(sql, new CRMStatusRowMapper());

		return statuslist;
	}	

}
