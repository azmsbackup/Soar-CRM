package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.soarcrm.domain.CRMNotes;

public class CRMNotesExtractor 
{
	public CRMNotes extractData(ResultSet resultSet) throws SQLException
	{
		CRMNotes notes = new CRMNotes();
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		notes.setNotesId(resultSet.getString("Notes_Id"));
		notes.setTraceNo(resultSet.getString("TraceNo"));
		notes.setUserId(resultSet.getString("user_id"));
		notes.setNotes(resultSet.getString("notes"));
		
		Date notesdt = null;
		String notesdate = "";
		if (resultSet.getString("Notes_Dt") != null && !resultSet.getString("Notes_Dt").equals("")) 
		{
			try 
			{
				notesdt = targetFormat.parse(resultSet.getString("Notes_Dt"));
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
			notesdate = originalFormat.format(notesdt);
			notes.setNotesDate(notesdate);
		}
		
		notes.setStatusId(resultSet.getString("Status_Id"));
		
		Date follouwpdt = null;
		String followupdate = "";
		if (resultSet.getString("Followup_Dt") != null && !resultSet.getString("Followup_Dt").equals("")) 
		{
			try 
			{
				follouwpdt = targetFormat.parse(resultSet.getString("Followup_Dt"));
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
			followupdate = originalFormat.format(follouwpdt);
			notes.setFollowUpDate(followupdate);
		}
		
		Date appointmentdt = null;
		String appointmentdate = "";
		if (resultSet.getString("Appointment_Dt") != null && !resultSet.getString("Appointment_Dt").equals("")) 
		{
			try 
			{
				appointmentdt = targetFormat.parse(resultSet.getString("Appointment_Dt"));
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}
			appointmentdate = originalFormat.format(appointmentdt);
			notes.setAppointmentDate(appointmentdate);
		}
		notes.setAppointmentTime(resultSet.getString("Appointment_Time"));
		notes.setAppointmentWith(resultSet.getString("Appointment_With"));
		notes.setAppointmentStatus(resultSet.getString("Appointment_Status"));
		notes.setTimezone(resultSet.getString("time_zone"));
		notes.setCreatedBy(resultSet.getString("Created_By"));
		notes.setCreatedDate(resultSet.getString("Created_Dt"));
		notes.setModifiedBy(resultSet.getString("Modified_By"));
		notes.setModifiedDate(resultSet.getString("Modified_Dt"));
		notes.setBucketId(resultSet.getString("bucket_id"));
		notes.setClientName(resultSet.getString("Client_Name"));
		notes.setStatusName(resultSet.getString("Status_Desc"));
		notes.setBucketName(resultSet.getString("bucket_name"));
		notes.setUserName(resultSet.getString("First_Name"));
	
		return notes;
	}

}
