package com.soarcrm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.soarcrm.domain.CRMTarget;
import com.soarcrm.jdbc.CRMTargetRowMapper;
import com.soarcrm.util.AllzoneCRMUtil;

public class CRMTargetDaoImpl implements CRMTargetDao
{
	@Autowired
	DataSource dataSource;
	
	public void insertTargets(CRMTarget target) throws Exception 
	{
		 String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		 
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			

		 String sql = "INSERT INTO azc_targets "
		    + "(Targets_Id, User_id, tmonth, tyear, status_id, Daily_Target, Created_By, Created_Dt ) "
		    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		 jdbcTemplate.update(sql, new Object[] {target.getTargetsId(), target.getUserId(), target.getMonth(), target.getYear(), 
				 target.getStatusId(), target.getDailyTarget(),  target.getLoginName(), currentDate});
		 
		/* DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date efffromdt = null;
			String efffromdate = "";

				if (target.getEffectiveFrom() != null && !target.getEffectiveFrom().equals("")) {
					efffromdt = originalFormat.parse(target.getEffectiveFrom());
					efffromdate = targetFormat.format(efffromdt);
					//additionalsetup.setSoftwaredowndt(softwaredowndate);
				}
			
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
			
			 String enddate  ="9999-12-31";
			 
			 String previousEffectivetoDate =AllzoneCRMUtil.subtractDate(target.getEffectiveFrom(), -1);
			 
			 Date previostodt = null;
				String previousefftodate = "";
					
				if (previousEffectivetoDate != null && !previousEffectivetoDate.equals("")) 
				{
					previostodt = originalFormat.parse(previousEffectivetoDate);
					previousefftodate = targetFormat.format(previostodt);			
				 }
				
				LocalDate todaydate = java.time.LocalDate.now();
				
				if (currentDate.compareTo(previousefftodate) >= 0) 
				{
					previousefftodate = String.valueOf(todaydate);
				}
			 
			 String sql2 = "UPDATE azc_targets set effective_end_dt=?, Modified_By=?, Modified_Dt = ? where User_id = ? and effective_end_dt =?";
				
			jdbcTemplate.update( sql2, new Object[] { previousefftodate, target.getLoginName(), currentDate, target.getUserId(), enddate});*/
	}

	public List<CRMTarget> getTargetList(String userid, String statusid) throws Exception 
	{
		List<CRMTarget> targetlist = new ArrayList<CRMTarget>();
		
		String sql = "SELECT azc_targets.*,azc_user_master.First_Name " + 
				"FROM azc_targets left JOIN azc_user_master " + 
				"ON azc_targets.User_id=azc_user_master.User_id where azc_targets.User_id= '"+userid+"' and  azc_targets.status_id='"+statusid+"' " ;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		targetlist = jdbcTemplate.query(sql, new CRMTargetRowMapper());

		return targetlist;
	}

	public CRMTarget getTargetDetails(String id) throws Exception 
	{
		List<CRMTarget> targetlist = new ArrayList<CRMTarget>();		
		
		String sql = "SELECT azc_targets.*,azc_user_master.First_Name " + 
				"FROM azc_targets left JOIN azc_user_master " + 
				"ON azc_targets.User_id = azc_user_master.User_id where Targets_Id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		targetlist = jdbcTemplate.query(sql, new CRMTargetRowMapper());
	
		return targetlist.get(0);
	}

	public void updateTarget(CRMTarget target) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		/*DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date efffromdt = null;
		String efffromdate = "";

			if (target.getEffectiveFrom() != null && !target.getEffectiveFrom().equals("")) {
				efffromdt = originalFormat.parse(target.getEffectiveFrom());
				efffromdate = targetFormat.format(efffromdt);
				//additionalsetup.setSoftwaredowndt(softwaredowndate);
			}*/
		
		String sql = "UPDATE azc_targets set Daily_Target=?, "
				+ " Modified_By=?, Modified_Dt = ? where Targets_Id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update( sql, new Object[] {target.getDailyTarget(), target.getLoginName(), currentDate, target.getTargetsId()});
		
	}

	public CRMTarget checkTarget(CRMTarget target) throws Exception 
	{
		
		List<CRMTarget> targetList  = new ArrayList<CRMTarget>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql= "SELECT azc_targets.*,azc_user_master.First_Name " + 
				"FROM azc_targets left JOIN azc_user_master " + 
				"ON azc_targets.User_id = azc_user_master.User_id where tmonth = ? and tyear=? and  azc_targets.User_id = ? and azc_targets.status_id = ? ";
		
		targetList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, target.getMonth());
				ps.setString(2, target.getYear());
				ps.setString(3, target.getUserId());
				ps.setString(4, target.getStatusId());
			}
		}, new CRMTargetRowMapper());
		
		if (targetList != null && targetList.size() > 0) 
		{
			for(int i=0; i<targetList.size(); i++)
			{
				CRMTarget tar = (targetList).get(i);
				tar.setFlag("1");
			}
			return targetList.get(0);
		}
		else 
		{
			return null;
		}
	}

	public CRMTarget getUserStatus(String userId) throws Exception 
	{
		CRMTarget target = new CRMTarget();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sqlselect = "SELECT user_status FROM azc_user_master where User_id ="+userId;
			
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlselect);
			String status="";
			for (Map<String, Object> row : rows) 
			{
				status = row.get("user_status").toString();
				target.setUserStatus(status);
			}
			
		return target;
		
	}

}
