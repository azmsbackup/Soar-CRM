package com.soarcrm.domain;

import java.util.List;

public class CRMNotes 
{
	private String notesId;
	private String traceNo;
	private String notes;
	private String notesDate;
	private String statusId;
	private String bucketId;
	
	private String followUpDate;
	private String appointmentDate;
	private String appointmentTime;
	private String appointmentWith;
	private String appointmentStatus;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String loginName;
	private String roleId;
	private String userId;
	private String clientName;
	private String statusName;
	private String bucketName;
	private String timezone;	
	private String calendarTime;
	private String calendarDate;
	private String ampm;
	private String hiddenUserId;
	private String fullTime;
	private String frompage;
	private String userName;
	private Client client;
	private List<Client> clientList;
	private String traceNos;
	private String alreadyExistsMsg;
	List<String> traceMessages;
	
	private int clientListSize;
	
	
	public int getClientListSize() {
		return clientListSize;
	}
	public void setClientListSize(int clientListSize) {
		this.clientListSize = clientListSize;
	}
	public String getAlreadyExistsMsg() {
		return alreadyExistsMsg;
	}
	public void setAlreadyExistsMsg(String alreadyExistsMsg) {
		this.alreadyExistsMsg = alreadyExistsMsg;
	}
	public String getTraceNos() {
		return traceNos;
	}
	public void setTraceNos(String traceNos) {
		this.traceNos = traceNos;
	}
	public List<Client> getClientList() {
		return clientList;
	}
	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFrompage() {
		return frompage;
	}
	public void setFrompage(String frompage) {
		this.frompage = frompage;
	}
	private List<CRMStatus> statusidlist;
	private List<Client> notesList;
	private List<CRMBucket> bucketList;
	
	public List<CRMBucket> getBucketList() {
		return bucketList;
	}
	public void setBucketList(List<CRMBucket> bucketlist) {
		this.bucketList = bucketlist;
	}
	public void setBucketId(String bucketId) {
		this.bucketId = bucketId;
	}
	public String getBucketId() {
		return bucketId;
	}
	/*public void setBucketid(String bucketid) {
		this.bucketId = bucketid;
	}*/
	/*public List<CRMBucket> getBucketList() {
		
		return bucketlist;
	}
	public void setBucketList(List<CRMBucket> bucketlist) {
		this.bucketlist = bucketlist;
	}*/
	
	
	public String getFullTime() {
		return fullTime;
	}
	public void setFullTime(String fullTime) {
		this.fullTime = fullTime;
	}
	public String getNotesId() 
	{
		return notesId;
	}
	public void setNotesId(String notesId) 
	{
		this.notesId = notesId;
	}
	public String getTraceNo() 
	{
		return traceNo;
	}
	public void setTraceNo(String traceNo) 
	{
		this.traceNo = traceNo;
	}
	public String getNotes() 
	{
		return notes;
	}
	public void setNotes(String notes) 
	{
		this.notes = notes;
	}
	public String getNotesDate() 
	{
		return notesDate;
	}
	public void setNotesDate(String notesDate) 
	{
		this.notesDate = notesDate;
	}
	public String getStatusId() 
	{
		return statusId;
	}
	public void setStatusId(String statusId) 
	{
		this.statusId = statusId;
	}
	public String getFollowUpDate() 
	{
		return followUpDate;
	}
	public void setFollowUpDate(String followUpDate) 
	{
		this.followUpDate = followUpDate;
	}
	public String getAppointmentDate() 
	{
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) 
	{
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() 
	{
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) 
	{
		this.appointmentTime = appointmentTime;
	}
	public String getAppointmentWith() 
	{
		return appointmentWith;
	}
	public void setAppointmentWith(String appointmentWith) 
	{
		this.appointmentWith = appointmentWith;
	}
	public String getAppointmentStatus() 
	{
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) 
	{
		this.appointmentStatus = appointmentStatus;
	}
	public String getCreatedBy() 
	{
		return createdBy;
	}
	public void setCreatedBy(String createdBy) 
	{
		this.createdBy = createdBy;
	}
	public String getCreatedDate() 
	{
		return createdDate;
	}
	public void setCreatedDate(String createdDate) 
	{
		this.createdDate = createdDate;
	}
	public String getModifiedBy() 
	{
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) 
	{
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedDate() 
	{
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) 
	{
		this.modifiedDate = modifiedDate;
	}
	public List<CRMStatus> getStatusidlist()
	{
		return statusidlist;
	}
	public void setStatusidlist(List<CRMStatus> statusidlist) 
	{
		this.statusidlist = statusidlist;
	}
	public String getLoginName()
	{
		return loginName;
	}
	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}
	public String getRoleId() 
	{
		return roleId;
	}
	public void setRoleId(String roleId) 
	{
		this.roleId = roleId;
	}
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	public String getClientName() 
	{
		return clientName;
	}
	public void setClientName(String clientName) 
	{
		this.clientName = clientName;
	}
	public List<Client> getNotesList()
	{
		return notesList;
	}
	public void setNotesList(List<Client> notesList)
	{
		this.notesList = notesList;
	}
	public String getStatusName() 
	{
		return statusName;
	}
	public void setStatusName(String statusName) 
	{
		this.statusName = statusName;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getCalendarTime() {
		return calendarTime;
	}
	public void setCalendarTime(String calendarTime) {
		this.calendarTime = calendarTime;
	}
	public String getCalendarDate() {
		return calendarDate;
	}
	public void setCalendarDate(String calendarDate) {
		this.calendarDate = calendarDate;
	}
	public String getAmpm() {
		return ampm;
	}
	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
	public String getHiddenUserId() {
		return hiddenUserId;
	}
	public void setHiddenUserId(String hiddenUserId) {
		this.hiddenUserId = hiddenUserId;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public List<String> getTraceMessages() {
		return traceMessages;
	}
	public void setTraceMessages(List<String> traceMessages) {
		this.traceMessages = traceMessages;
	}
	
	
	

}
