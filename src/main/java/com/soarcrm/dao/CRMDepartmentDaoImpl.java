package com.soarcrm.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.soarcrm.domain.CRMDepartment;
import com.soarcrm.jdbc.CRMDepartmentRowMapper;
import com.soarcrm.util.AllzoneCRMUtil;

public class CRMDepartmentDaoImpl implements CRMDepartmentDao 
{
	@Autowired
	DataSource dataSource;

	public void insertDepartment(CRMDepartment department) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");			
		String status = "A";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		
		String sql = "INSERT INTO azc_department "
		    + "(Dept_Id, Dept_Code, Dept_Name, Status, Created_By, Created_Dt) "
		    + "VALUES (?, ?, ?, ?, ?, ?)";


		 jdbcTemplate.update(sql, new Object[] { department.getDepartmentId(), department.getDepartmentCode(), department.getDepartmentName(),  
				status, department.getLoginname(), currentDate});
		
	}

	public List<CRMDepartment> getDepartmentList() throws Exception 
	{
		List<CRMDepartment> departmentlist = new ArrayList<CRMDepartment>();
		
		String sql = "select * from azc_department where Status='A' and Dept_Name !='ALL' ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		departmentlist = jdbcTemplate.query(sql, new CRMDepartmentRowMapper());

		return departmentlist;
	}

	public CRMDepartment getDepartment(String id) throws Exception 
	{
		List<CRMDepartment> departmentlist = new ArrayList<CRMDepartment>();		
		
		String sql = "select * from azc_department where Dept_Id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		departmentlist = jdbcTemplate.query(sql, new CRMDepartmentRowMapper());
	
		return departmentlist.get(0);
	}
	
	public void updateDepartment(CRMDepartment department) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");			
		String status = "A";
		
		String sql = "UPDATE azc_department set  Dept_Code = ?, Dept_Name = ?, Status=?, "
				+" Modified_By = ?, Modified_Dt = ? where Dept_Id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update( sql, new Object[] {department.getDepartmentCode(), department.getDepartmentName(), status,
				department.getLoginname(), currentDate, department.getDepartmentId()});
		
	}

	public CRMDepartment checkDepartmentname(CRMDepartment department) throws Exception 
	{
		String departmentname = department.getDepartmentName();
		String departmentcode = department.getDepartmentCode();
	
		List<CRMDepartment> deptList = new ArrayList<CRMDepartment>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from azc_department  where Dept_Code = ? and Dept_Name = ?";

		deptList = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, departmentcode);
				ps.setString(2,departmentname);

			}
		}, new CRMDepartmentRowMapper());
		
		if (deptList != null && deptList.size() > 0) 
		{
			department.setFlag(1);
			return deptList.get(0);
			
		} else {
			return null;
		}
	}

	public CRMDepartment checkEditDepartmentname(CRMDepartment department) throws Exception 
	{	
		List<CRMDepartment> deptList = new ArrayList<CRMDepartment>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from azc_department where Dept_Name = ? and dept_id != ?";
		//System.out.println(""+sql);

		//System.out.println("department.getDepartmentName() "+department.getDepartmentName());
		//System.out.println("department.getDepartmentId() "+department.getDepartmentId());
		

		deptList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, department.getDepartmentName());
				ps.setString(2, department.getDepartmentId());

			}
		}, new CRMDepartmentRowMapper());
		
		//System.out.println("deptList.size() "+deptList.size());
		if (deptList != null && deptList.size() > 0) 
		{
			department.setFlag(1);
			return deptList.get(0);
			
		} else {
			return null;
		}
	}

	public void insertUserDeptMap(CRMDepartment department) throws Exception 
	{
		try 
		{
			String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
			
			String[] deptId = null;			
			if(department.getDepartmentId() != null && !department.getDepartmentId().equals(""))
			{
				 deptId = department.getDepartmentId().split(",");
			}
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String deletesql = "delete from azc_userdept_mapping where User_id = ?";
	
			jdbcTemplate.execute(deletesql,new PreparedStatementCallback<Boolean>(){  
			  
		    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException 
		    {      
		        ps.setString(1,department.getUserId());  
		       	              
		        return ps.execute();  
		              
		    }  
		    });
			
			if (deptId != null && deptId.length > 0) 
			{
				for (int i = 0; i < deptId.length; i++) 
				{
					String departmentid = deptId[i];
				
					String sql = "INSERT INTO azc_userdept_mapping "
						    + "(User_id, dept_id, Created_By, Created_Dt) "
						    + "VALUES (?, ?, ?, ?)";
			
					jdbcTemplate.update(sql, new Object[] {department.getUserId(), departmentid, department.getLoginname(), currentDate});
				}
			}
		}
		 catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}

	public List<CRMDepartment> getUserDepartmentList(String userid) throws Exception 
	{
		List<CRMDepartment> departmentlist = new ArrayList<CRMDepartment>();
		
		String sql = "select * from azc_userdept_mapping left join azc_department on azc_userdept_mapping.dept_id = azc_department.dept_id where "
				+ "azc_userdept_mapping.User_id='"+userid+"' and  azc_department.Status = 'A'";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) 
		{
			CRMDepartment department = new CRMDepartment();
			String departmentame="";
			String departmentId="";
			
			if(row.get("Dept_Name") != null && !row.get("Dept_Name").equals(""))
			{
				departmentame = row.get("Dept_Name").toString();
				department.setDepartmentName(departmentame);
			}
			if(row.get("dept_id") != null && !row.get("dept_id").equals(""))
			{
				departmentId = row.get("dept_id").toString();
				department.setDepartmentId(departmentId);
			}
			departmentlist.add(department);
		}
		return departmentlist;
	}

	public ArrayList<CRMDepartment> getUsermappingList(String id) throws Exception 
	{
		ArrayList<CRMDepartment> usermappingList = new ArrayList<CRMDepartment>();

		String sql = "SELECT p.*  FROM azc_department p  WHERE NOT EXISTS (SELECT s.dept_id " + 
				"FROM  azc_userdept_mapping s  WHERE  s.dept_id = p.dept_id and s.user_id = '"+id+"') and  p.Status = 'A' and p.Dept_Name != 'ALL' ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) 
		{
			CRMDepartment department = new CRMDepartment();
			String departmentame="";
			String departmentId="";
			
			if(row.get("Dept_Name") != null && !row.get("Dept_Name").equals(""))
			{
				departmentame = row.get("Dept_Name").toString();
				department.setDepartmentName(departmentame);
			}
			if(row.get("dept_id") != null && !row.get("dept_id").equals(""))
			{
				departmentId = row.get("dept_id").toString();
				department.setDepartmentId(departmentId);
			}
			usermappingList.add(department);
		}

		return usermappingList;
	}

	public ArrayList<CRMDepartment> getUsermappingListforuser(String id) throws Exception 
	{
		ArrayList<CRMDepartment> usermappingListforuser = new ArrayList<CRMDepartment>();

		String sql = "select a.dept_id, a.User_id, b.Dept_Name from " + 
				"azc_userdept_mapping a, azc_department b " + 
				"where a.dept_id = b.dept_id and a.user_id= '"+id+"' and b.Status = 'A' and b.Dept_Name !='ALL' ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) 
		{
			CRMDepartment department = new CRMDepartment();
			
			if(row.get("Dept_Name") != null && !row.get("Dept_Name").equals(""))
			{
				department.setDepartmentName(row.get("Dept_Name").toString());
			}
			if(row.get("dept_id") != null && !row.get("dept_id").equals(""))
			{
				department.setDepartmentId(row.get("dept_id").toString());
			}
			usermappingListforuser.add(department);
		}

		return usermappingListforuser;
		
	}

	public void inactiveDepartment(CRMDepartment department) throws Exception 
	{
		String currentDate = AllzoneCRMUtil.getcurrentDatetime("yyyy-MM-dd HH:mm:ss");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		 String sqlselect = "SELECT Status FROM azc_department where dept_id='"+department.getDepartmentId()+"' ";
			
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
		
		String sql = "UPDATE azc_department set Status=?, "
			+" Modified_By = ?, Modified_Dt = ? where dept_id = ? ";
	

		jdbcTemplate.update( sql, new Object[] {status, department.getLoginname(), currentDate, department.getDepartmentId()});
		
	}

	public List<CRMDepartment> getDepartmentListwithInactive() throws Exception 
	{
		List<CRMDepartment> departmentlist = new ArrayList<CRMDepartment>();
		
		String sql = "select * from azc_department where Dept_Name !='ALL'";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		departmentlist = jdbcTemplate.query(sql, new CRMDepartmentRowMapper());

		return departmentlist;
	}


}
