package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.soarcrm.domain.Client;

public class CRMClientExtractor 
{
	public Client extractData(ResultSet resultSet) throws SQLException
	{
		Client client = new Client();
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		client.setTraceNo(resultSet.getString("TraceNo"));
		client.setManageTraceNo(resultSet.getString("TraceNo"));
		client.setClientName(resultSet.getString("Client_Name"));
		client.setAddress(resultSet.getString("Address"));
		client.setCitiesId(resultSet.getString("cities_id"));
		client.setStatesId(resultSet.getString("states_id"));
		client.setCountriesId(resultSet.getString("countries_id"));
		client.setZip(resultSet.getString("zip"));
		client.setDeptId(resultSet.getString("Dept_Id"));
		client.setContactPerson(resultSet.getString("Contact_Person"));
		client.setPhoneNumber(resultSet.getString("phone_no"));
		client.setExtension(resultSet.getString("extn"));
		client.setMobileNumber(resultSet.getString("mobile_no"));
		client.setAlternateMobileNumber(resultSet.getString("alt_phone_no"));
		client.setFax(resultSet.getString("fax"));
		client.setEmail(resultSet.getString("e_mail"));
		if(resultSet.getString("time_zone") != null && !resultSet.getString("time_zone").equals(""))
		{
			client.setTimezone(resultSet.getString("time_zone"));
		}
		else
		{
			client.setTimezone("-");
		}
		
		client.setStatusId(resultSet.getString("status_id"));
		client.setWebsite(resultSet.getString("website"));		
		client.setSourceId(resultSet.getString("Source_Id"));
		client.setEventId(resultSet.getString("event_id"));
		client.setAnnualRevenue(resultSet.getString("Annual_Revenue"));
		client.setDescription(resultSet.getString("Description"));
		client.setExistingCustomer(resultSet.getString("Is_Existing_Customer"));
		client.setUserId(resultSet.getString("User_id"));
		client.setCreatedBy(resultSet.getString("Created_By"));
		
		Date createddt = null;
		String createddate = "";
		if (resultSet.getString("Created_Dt") != null && !resultSet.getString("Created_Dt").equals("")) 
		{
			try 
			{
				createddt = targetFormat.parse(resultSet.getString("Created_Dt"));
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
			createddate = originalFormat.format(createddt);
			client.setCreatedDate(createddate);
		}
		else
		{
			client.setCreatedDate(resultSet.getString("Created_Dt"));
		}
		
		client.setModifiedBy(resultSet.getString("Modified_By"));
		
		Date modifieddt = null;
		String modifieddate = "";
		if (resultSet.getString("Modified_Dt") != null && !resultSet.getString("Modified_Dt").equals("")) 
		{
			try 
			{
				modifieddt = targetFormat.parse(resultSet.getString("Modified_Dt"));
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
			modifieddate = originalFormat.format(modifieddt);
			client.setModifiedDate(modifieddate);
		}
		else
		{
			client.setModifiedDate(resultSet.getString("Modified_Dt"));
		}
		
		if(resultSet.getString("City") != null && !resultSet.getString("City").equals(""))
		{
			client.setCityName(resultSet.getString("City"));
		}
		else
		{
			client.setCityName("-");
		}
		
		if(resultSet.getString("State") != null && !resultSet.getString("State").equals(""))
		{
			client.setStateName(resultSet.getString("State"));
		}
		else
		{
			client.setStateName("-");
		}
		client.setDesignation(resultSet.getString("designation"));
		client.setServicesId(resultSet.getString("services_id"));
		client.setBucketId(resultSet.getString("bucket_id"));
		//client.setCountryName(resultSet.getString(36));
		
		client.setDepartmentName(resultSet.getString("Dept_Name"));
		client.setSourceName(resultSet.getString("Source_Desc"));
		client.setStatusName(resultSet.getString("Status_Desc"));
		client.setLastnote(resultSet.getString("notes"));
		client.setUsername(resultSet.getString("First_Name"));
		client.setBucketName(resultSet.getString("bucket_name"));
		
		String[] email = null;
		if(resultSet.getString("e_mail") != null && !resultSet.getString("e_mail").equals(""))
		{
			
			email=resultSet.getString("e_mail").split(";");			
			 
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
		if(resultSet.getString("Contact_Person") != null && !resultSet.getString("Contact_Person").equals(""))
		{
			contactPerson = resultSet.getString("Contact_Person").split(";");			
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
		if(resultSet.getString("designation") != null && !resultSet.getString("designation").equals(""))
		{
			designation = resultSet.getString("designation").split(";");			
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
