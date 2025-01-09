package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.soarcrm.domain.CRMTarget;

public class CRMTargetExtractor 
{
	public CRMTarget extractData(ResultSet resultSet) throws SQLException
	{
		CRMTarget target = new CRMTarget();
		target.setTargetsId(resultSet.getString(1));
		target.setUserId(resultSet.getString(2));
		target.setMonth(resultSet.getString(3));
		target.setYear(resultSet.getString(4));
		target.setStatusId(resultSet.getString(5));
		target.setDailyTarget(resultSet.getString(6));
		target.setCreatedBy(resultSet.getString(7));
		target.setCreatedDate(resultSet.getString(8));
		target.setModifiedBy(resultSet.getString(9));
		target.setModifiedDate(resultSet.getString(10));
		target.setName(resultSet.getString(11));
		
		if(target.getStatusId() != null && target.getStatusId().equals("6"))
		{
			target.setActivityDescription("Open");
		}
		if(target.getStatusId() != null && target.getStatusId().equals("4"))
		{
			target.setActivityDescription("Email Sent");
		}
		if(target.getStatusId() != null && target.getStatusId().equals("3"))
		{
			target.setActivityDescription("Follow Up");
		}
		if(target.getStatusId() != null && target.getStatusId().equals("7"))
		{
			target.setActivityDescription("Appt Fixed");
		}
		
		if(target.getMonth() != null && target.getMonth().equals("1"))
		{
			target.setMonthName("January");
		}
		if(target.getMonth() != null && target.getMonth().equals("2"))
		{
			target.setMonthName("February");
		}
		if(target.getMonth() != null && target.getMonth().equals("3"))
		{
			target.setMonthName("March");
		}
		if(target.getMonth() != null && target.getMonth().equals("4"))
		{
			target.setMonthName("April");
		}
		if(target.getMonth() != null && target.getMonth().equals("5"))
		{
			target.setMonthName("May");
		}
		if(target.getMonth() != null && target.getMonth().equals("6"))
		{
			target.setMonthName("June");
		}
		if(target.getMonth() != null && target.getMonth().equals("7"))
		{
			target.setMonthName("July");
		}
		if(target.getMonth() != null && target.getMonth().equals("8"))
		{
			target.setMonthName("August");
		}
		if(target.getMonth() != null && target.getMonth().equals("9"))
		{
			target.setMonthName("September");
		}
		if(target.getMonth() != null && target.getMonth().equals("10"))
		{
			target.setMonthName("October");
		}
		if(target.getMonth() != null && target.getMonth().equals("11"))
		{
			target.setMonthName("November");
		}
		if(target.getMonth() != null && target.getMonth().equals("12"))
		{
			target.setMonthName("December");
		}
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		month = month+1;
		if((Integer.valueOf(target.getYear()) == year) && (Integer.valueOf(target.getMonth()) >= month))
		{
			target.setFlag("1");
		}
		
		return target;
	}

}
