package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMLead;

public class CRMLeadExtractor {
	public CRMLead extractData(ResultSet resultSet) throws SQLException{
		CRMLead lead = new CRMLead();
		lead.setLeadId(resultSet.getString("lead_id"));
		lead.setCompanyName(resultSet.getString("company_name"));
		lead.setConFirstName(resultSet.getString("contact_first_name"));
		lead.setConLastName(resultSet.getString("contact_last_name"));
		lead.setPhoneNumber(resultSet.getString("phone_no"));
		lead.setEmail(resultSet.getString("email_id"));
		lead.setSourceId(resultSet.getString("source_id"));
		lead.setStatusId(resultSet.getString("status"));
		lead.setCreatedBy(resultSet.getString("created_by"));
		lead.setCreatedDate(resultSet.getString("created_by"));	
		lead.setComments(resultSet.getString("comments"));	
		lead.setTraceNo(resultSet.getString("trace_no"));	
		String sourceDesc = resultSet.getString("Source_Desc");
		if(sourceDesc!= null) {lead.setSourceName(sourceDesc);}
		if(sourceDesc!= null) {lead.setStatusName(resultSet.getString("Status_Desc"));}
		return lead;		
	}
}
