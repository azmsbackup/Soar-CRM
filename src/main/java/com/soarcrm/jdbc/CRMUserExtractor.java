package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMUser;

public class CRMUserExtractor 
{
	public CRMUser extractData(ResultSet resultSet) throws SQLException
	{
		CRMUser user = new CRMUser();
		
		user.setUserId(resultSet.getString(1));
		user.setRoleId(resultSet.getString(2));
		user.setFirstName(resultSet.getString(3));
		user.setLastName(resultSet.getString(4));
		user.setLoginName(resultSet.getString(5));
		user.setUserpassword(resultSet.getString(6));
		user.setHighSecurityPassword(resultSet.getString(7));
		user.setEmailId(resultSet.getString(8));
		user.setUserType(resultSet.getString(9));
		user.setEmployeeId(resultSet.getString(10));
		user.setUserStatus(resultSet.getString(11));
		user.setCreatedBy(resultSet.getString(12));
		user.setCreatedDate(resultSet.getString(13));
		user.setModifiedBy(resultSet.getString(14));
		user.setModifiedDate(resultSet.getString(15));
		
		/*if(user.getUserType().equals("A"))
		{
			user.setUserType("Admin");
		}
		if(user.getUserType().equals("E"))
		{
			user.setUserType("Employee");
		}*/
		

		return user;
	}
}
