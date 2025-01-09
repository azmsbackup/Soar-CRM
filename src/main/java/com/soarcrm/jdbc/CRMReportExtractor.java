package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.soarcrm.domain.Client;

public class CRMReportExtractor 
{
	public Client extractData(ResultSet resultSet) throws SQLException
	{
		Client client = new Client();
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		client.setTraceNo(resultSet.getString("TraceNo"));
		client.setClientName(resultSet.getString("Client_Name"));
		if (resultSet.getString("Address") != null && !resultSet.getString("Address").equals("")) 
		{
			client.setAddress(resultSet.getString("Address"));
		}
		else
		{
			client.setAddress("-");
		}
		client.setCitiesId(resultSet.getString("cities_id"));
		client.setStatesId(resultSet.getString("states_id"));
		client.setCountriesId(resultSet.getString("countries_id"));
		client.setZip(resultSet.getString("zip"));
		client.setDeptId(resultSet.getString("Dept_Id"));
		client.setContactPerson(resultSet.getString("Contact_Person"));
		client.setPhoneNumber(resultSet.getString("phone_no"));
		if (resultSet.getString("fax") != null && !resultSet.getString("fax").equals("")) 
		{
			client.setFax(resultSet.getString("fax"));
		}
		else
		{
			client.setFax("-");
		}
		client.setEmail(resultSet.getString("e_mail"));
		client.setTimezone(resultSet.getString("time_zone"));
		client.setStatusId(resultSet.getString("status_id"));
		if (resultSet.getString("website") != null && !resultSet.getString("website").equals("")) 
		{
			client.setWebsite(resultSet.getString("website"));
		}
		else
		{
			client.setWebsite("-");
		}
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
		client.setDesignation(resultSet.getString("designation"));
		client.setServicesId(resultSet.getString("services_id"));
		client.setBucketId(resultSet.getString("bucket_id"));
		//client.setDataSource(resultSet.getString(36));
		
		if(resultSet.getString("name") != null && !resultSet.getString("name").equals(""))
		{
			client.setCountryName(resultSet.getString("name"));
		}
		else
		{
			client.setCountryName("-");
		}
		if(resultSet.getString("State") != null && !resultSet.getString("State").equals(""))
		{
			client.setStateName(resultSet.getString("State"));
		}
		else
		{
			client.setStateName("-");
		}
		if(resultSet.getString("City") != null && !resultSet.getString("City").equals(""))
		{
			client.setCityName(resultSet.getString("City"));
		}
		else
		{
			client.setCityName("-");
		}
		
		client.setDepartmentName(resultSet.getString("Dept_Name"));
		client.setSourceName(resultSet.getString("Source_Desc"));
		client.setStatusName(resultSet.getString("Status_Desc"));
		client.setUsername(resultSet.getString("First_Name"));
		client.setUserId(resultSet.getString("User_id"));
		client.setNotes(resultSet.getString("Notes"));
		  Date notesdt = null; String notesdate = ""; 
		  if(resultSet.getString("NotesDt") != null && !resultSet.getString("NotesDt").equals("")) { 
		     try { 
		    	notesdt = targetFormat.parse(resultSet.getString("NotesDt")); 
		    	} 
		    catch(ParseException e) 
		    { 
		    	e.printStackTrace(); 
		    } 
		      notesdate = originalFormat.format(notesdt);
		      client.setNotesDate(notesdate); 
		  }
		
		if(resultSet.getString("Event_Name") != null && !resultSet.getString("Event_Name").equals(""))
		{
			client.setEventName(resultSet.getString("Event_Name"));
		}
		else
		{
			client.setEventName("-");
		}	
		
		if(resultSet.getString("services_desc") != null && !resultSet.getString("services_desc").equals(""))
		{
			client.setServicesName(resultSet.getString("services_desc"));
		}
		else
		{
			client.setServicesName("-");
		}
		
		if(resultSet.getString("bucket_name") != null && !resultSet.getString("bucket_name").equals(""))
		{
			client.setBucketName(resultSet.getString("bucket_name"));
		}
		else
		{
			client.setBucketName("-");
		}
		
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
		else
		{
			client.setEmail("-");
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
		else
		{
			client.setDesignation("-");
		}
		
	
		return client;
	}

}
