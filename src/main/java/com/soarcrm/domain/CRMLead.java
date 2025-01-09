package com.soarcrm.domain;

import java.util.List;

public class CRMLead {
	private String leadId;
	private String companyName;
	private String conFirstName;
	private String conLastName;	
	private String phoneNumber;
	private String email;
	private String roleid;
	private String userId;
	private String statusId;
	private String sourceId;
	private String frompage;	
	private String statusName;
	private String sourceName;
	private String comments;
	private String traceNo;
	private String actionFlag;
	private String validFlag;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String logDescription;
	private List<CRMLead> leadList;
	private List<CRMSource> sourceidlist;
	private List<CRMStatus> statusidlist;
	
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getConFirstName() {
		return conFirstName;
	}
	public void setConFirstName(String conFirstName) {
		this.conFirstName = conFirstName;
	}
	public String getConLastName() {
		return conLastName;
	}
	public void setConLastName(String conLastName) {
		this.conLastName = conLastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getActionFlag() {
		return actionFlag;
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	public List<CRMStatus> getStatusidlist() {
		return statusidlist;
	}
	public void setStatusidlist(List<CRMStatus> statusidlist) {
		this.statusidlist = statusidlist;
	}
	public List<CRMSource> getSourceidlist() {
		return sourceidlist;
	}
	public void setSourceidlist(List<CRMSource> sourceidlist) {
		this.sourceidlist = sourceidlist;
	}
	public List<CRMLead> getLeadList() {
		return leadList;
	}
	public void setLeadList(List<CRMLead> leadList) {
		this.leadList = leadList;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLogDescription() {
		return logDescription;
	}
	public void setLogDescription(String logDescription) {
		this.logDescription = logDescription;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getFrompage() {
		return frompage;
	}
	public void setFrompage(String frompage) {
		this.frompage = frompage;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
}
