package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.soarcrm.domain.Client;

public class CheckClientExtractor 
{
	public Client extractData(ResultSet resultSet) throws SQLException
	{
		Client client = new Client();
		
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		client.setTraceNo(resultSet.getString(1));
		client.setClientName(resultSet.getString(2));
		client.setAddress(resultSet.getString(3));
		client.setCitiesId(resultSet.getString(4));
		client.setStatesId(resultSet.getString(5));
		client.setCountriesId(resultSet.getString(6));
		client.setZip(resultSet.getString(7));
		client.setDeptId(resultSet.getString(8));
		client.setContactPerson(resultSet.getString(9));
		client.setPhoneNumber(resultSet.getString(10));
		client.setMobileNumber(resultSet.getString(12));
		client.setAlternateMobileNumber(resultSet.getString(13));
		client.setFax(resultSet.getString(14));
		client.setEmail(resultSet.getString(15));
		client.setTimezone(resultSet.getString(16));
		client.setStatusId(resultSet.getString(17));
		client.setWebsite(resultSet.getString(18));
		client.setSourceId(resultSet.getString(19));
		client.setEventId(resultSet.getString(20));
		client.setAnnualRevenue(resultSet.getString(21));
		client.setDescription(resultSet.getString(22));
		client.setExistingCustomer(resultSet.getString(23));
		client.setUserId(resultSet.getString(24));
		client.setCreatedBy(resultSet.getString(25));
		
		Date createddt = null;
		String createddate = "";
		if (resultSet.getString(26) != null && !resultSet.getString(26).equals("")) 
		{
			try 
			{
				createddt = targetFormat.parse(resultSet.getString(26));
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
			createddate = originalFormat.format(createddt);
			client.setCreatedDate(createddate);
		}
		else
		{
			client.setCreatedDate(resultSet.getString(26));
		}
		
		client.setModifiedBy(resultSet.getString(27));
		
		Date modifieddt = null;
		String modifieddate = "";
		if (resultSet.getString(28) != null && !resultSet.getString(28).equals("")) 
		{
			try 
			{
				modifieddt = targetFormat.parse(resultSet.getString(28));
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
			modifieddate = originalFormat.format(modifieddt);
			client.setModifiedDate(modifieddate);
		}
		else
		{
			client.setModifiedDate(resultSet.getString(28));
		}
		
		String[] email = null;
		if(resultSet.getString(15) != null && !resultSet.getString(15).equals(""))
		{
			email=resultSet.getString(15).split(";");			
			 
			 for (int i=0; i < email.length; i++)
			 {
				 if(i == 0)
				 {
					 client.setEmail(email[0]);
				 }
				 if(i == 1)
				 {
					 client.setEmail2(email[1]);
				 }
				 if(i == 2)
				 {
					 client.setEmail3(email[2]);
				 }
			 }
		}
		
		String[] contactPerson = null;
		if(resultSet.getString(9) != null && !resultSet.getString(9).equals(""))
		{
			contactPerson = resultSet.getString(9).split(";");			
			for(int i=0; i < contactPerson.length; i++)
			{
				if(i == 0)
				{
					client.setContactPerson(contactPerson[0]);
				}
				if(i == 1)
				{
					client.setContactPersonTwo(contactPerson[1]);
				}
				
			}
		}
		
		String[] designation = null;
		if(resultSet.getString(33) != null && !resultSet.getString(33).equals(""))
		{
			designation = resultSet.getString(33).split(";");			
			for(int i=0; i < designation.length; i++)
			{
				if(i == 0)
				{
					client.setDesignation(designation[0]);
				}
				if(i == 1)
				{
					client.setDesignationTwo(designation[1]);
				}
				
			}
		}
	
		return client;
	}

}
