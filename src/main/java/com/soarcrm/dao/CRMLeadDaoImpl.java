package com.soarcrm.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.soarcrm.domain.CRMLead;
import com.soarcrm.jdbc.CRMLeadRowMapper;
import com.soarcrm.util.AllzoneCRMUtil;

public class CRMLeadDaoImpl implements CRMLeadDao{
	@Autowired
	DataSource dataSource;

	@Transactional(rollbackFor = Exception.class)
	public void insertLeadData(CRMLead lead) throws Exception {
		lead.setStatusId("A");
		String sql = "INSERT INTO azc_lead (company_name, contact_first_name, contact_last_name, "
		        + "phone_no, email_id, source_id,status,comments,created_by, created_dt) "
		        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String currentDate = AllzoneCRMUtil.getCurrentDBDate();

		jdbcTemplate.update(sql,lead.getCompanyName(), lead.getConFirstName(), lead.getConLastName(),
				lead.getPhoneNumber(), lead.getEmail(), lead.getSourceId(), lead.getStatusId(),lead.getComments(),
		        lead.getCreatedBy(), currentDate);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void updateLeadData(CRMLead lead) throws Exception {
		String sql = "UPDATE azc_lead SET company_name = ?, contact_first_name = ?, contact_last_name = ?, "
		        + "phone_no = ?, email_id = ?, source_id = ?,comments = ?, modified_by = ?, modified_dt = ? "
		        + "WHERE lead_id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String currentDate = AllzoneCRMUtil.getCurrentDBDate();

		jdbcTemplate.update(sql,
		        lead.getCompanyName(), lead.getConFirstName(), lead.getConLastName(),
		        lead.getPhoneNumber(), lead.getEmail(), lead.getSourceId(),lead.getComments(),
		        lead.getModifiedBy(), currentDate, lead.getLeadId());

	}
	
	public List<CRMLead> getLeadList() throws Exception {
		List<CRMLead>leadList = new ArrayList<CRMLead>();

		String sql = "select *,Source_Desc,Status_Desc from allzone_crm.azc_lead " + 
				     "left join azc_source on azc_lead.source_id=azc_source.source_id " + 
				     "left join azc_status  on azc_lead.status=azc_status.status_id  ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		leadList = jdbcTemplate.query(sql, new CRMLeadRowMapper());
		return leadList;
	}
	
	public List<CRMLead> geteditLead(String id) throws Exception {
		List<CRMLead>leadList = new ArrayList<CRMLead>();

		String sql = "select *,Source_Desc,Status_Desc from allzone_crm.azc_lead " + 
			     "left join azc_source on azc_lead.source_id=azc_source.source_id " + 
			     "left join azc_status  on azc_lead.status=azc_status.status_id where  lead_id = " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		leadList = jdbcTemplate.query(sql, new CRMLeadRowMapper());
		return leadList;
	}
	
	public void updateLeadStatus(String id,String traceNo) throws Exception {
		
		String sql = "UPDATE azc_lead SET status = 'I',trace_no = ? WHERE lead_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql,traceNo, id);
	}
	

	public List<CRMLead> getLeadReport(CRMLead leads) throws Exception{
		List<CRMLead>leadList = new ArrayList<CRMLead>();
		leads.setCreatedDate(AllzoneCRMUtil.changeBEDateFormat(leads.getCreatedDate()));
		leads.setModifiedDate(AllzoneCRMUtil.changeBEDateFormat(leads.getModifiedDate()));
		
		String sql = "select *,Source_Desc,Status_Desc from allzone_crm.azc_lead " + 
				     "left join azc_source on azc_lead.source_id=azc_source.source_id " + 
				     "left join azc_status  on azc_lead.status=azc_status.status_id where azc_lead.created_dt between ? and ?";
		if(!leads.getSourceId().equals("All")){
			sql =sql+" and azc_lead.source_id ='"+leads.getSourceId()+"'";
		}
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		leadList = jdbcTemplate.query(sql, new CRMLeadRowMapper(),new Object[] {
				leads.getCreatedDate(),leads.getModifiedDate()});
		return leadList;
	}
	
	public int checkLead(CRMLead lead) throws Exception {		
		String sql = "select phone_no,email_id from azc_lead where ";
		if(lead.getValidFlag().equals("C")) {
			sql=sql+" company_name='"+lead.getCompanyName()+"'";
		}
		if(lead.getValidFlag().equals("P")) {
			sql=sql+" phone_no='"+lead.getPhoneNumber()+"'";
		}
		if(lead.getValidFlag().equals("E")) {
			sql=sql+" email_id='"+lead.getEmail()+"'";
		}
		if(lead.getActionFlag().equals("E")) {
			sql=sql+" and lead_id != " + lead.getLeadId();
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<java.util.Map<String, Object>> rowj = jdbcTemplate.queryForList(sql);
		return rowj.size();
		}
}
