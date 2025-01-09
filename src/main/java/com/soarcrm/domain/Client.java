package com.soarcrm.domain;

import java.util.List;

public class Client 
{
	private String traceNo;
	private String clientName;
	private String address;
	private String citiesId;
	private String statesId;
	private String leadId;
	private String countriesId;
	private String zip;
	private String deptId;
	private String contactPerson;
	private String phoneNumber;
	private String fax;
	private String email;
	private String statusId;
	private String website;
	private String sourceId;
	private String annualRevenue;
	private String description;
	private String existingCustomer;
	private String userId;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String loginName;
	private int flag;
	private String id;
	private String username;
	private String logDescription;
	private String bucketId;
	private String bucketName;
	private String fullEmail;

	
	private List<CRMDepartment> departmentidlist;
	private List<CRMSource> sourceidlist;
	private List<CRMStatus> statusidlist;
	private List<CRMState> statelist;
	private List<CRMCity> citylist;
	private List<CRMCountry> countrylist;
	private List<CRMUser> userlist;
	private List<CRMServices> serviceslist;
	private List<CRMBucket> bucketList;
	
	
	private String frompage;
	private String logId;
	
	private String hiddenuserid;
	
	private String countryName;
	private String stateName;
	private String cityName;
	private String departmentName;
	private String statusName;
	private String sourceName;
	private String servicesName;
	
	private String fromname;
	private String toname;
	
	private int nameflag;
	
	private int websiteflag;
	private int phoneflag;
	
	private List<Client> logTraceList;
	private List<Client> clientList;
	private String logclientname;
	
	private String roleid;
	
	private String dataCollectedUser;
	private String assignToUser;
	private String fromuserid;
	
	private String lastnote;
	private List<Client> traceList;
	private String status;
	private String fromDate;
	private String toDate;
	
	private List<Client> departmentList;
	
	private String extension;
	private String mobileNumber;
	private String alternateMobileNumber;
	
	private String email3;
	private String email2;
	private String logemail;
	private String logPhoneNo;
	private String logwebsite;
	
	private String name;
	private String aggreement;
	
	private List<Client> openDealsList;
	private List<Client> emailSentList;
	private List<Client> followupList;
	private List<Client> closedList;
	
	private List<Client> overdueDealsList;
	private List<Client> overdueEmailSentList;
	private List<Client> overdueFollowupList;
	private List<Client> allclientsList;
	
	private String manageTraceNo;
	
	private String eventId;
	private List<String> sourceEventList;
	private List<CRMConference> eventList;
	
	private String eventName;
	private String notesDate;
	private String notes;
	private String followUpDate;
	private int rownum;
	
	private String timezone;
	
	private int mobileFlag;
	private int altMobileFlag;
	private String designation;
	
	private String contactPersonTwo;
	private String designationTwo;
	
	private String hiddenClientName;
	private String hiddenTraceNo;
	private String searchbox;
	private String textbox;
	private String uploadClientName;
	
	private String servicesId;
	private String followupWith;
	private String dataSourceId;
	
	
	private List<Client> noteslist;
	
	private int sqlemail;
	private int sqlemail2;
	private int sqlemail3;
	
	private String mailflag;
	private int mailflag2;
	private int mailflag3;
	private String emaillogDescription;
	
	private String statusflag;
	private String followupDate;
	private List<Client> hotleadsList;
	private List<Client> droppedList;
	private String roleId;
	private String traceNos;
	private String fromUserIdstr;
	private String selectedStatusFlag;
	private String alreadyExistsMsg;
	private int clientListSize;
	private String traceNoNew;
	private String searchKey;
	List<String> traceMessages;

	
	
	public List<String> getTraceMessages() {
		return traceMessages;
	}
	public void setTraceMessages(List<String> traceMessages) {
		this.traceMessages = traceMessages;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getTraceNoNew() {
		return traceNoNew;
	}
	public void setTraceNoNew(String traceNoNew) {
		this.traceNoNew = traceNoNew;
	}
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
	public String getSelectedStatusFlag() {
		return selectedStatusFlag;
	}
	public void setSelectedStatusFlag(String selectedStatusFlag) {
		this.selectedStatusFlag = selectedStatusFlag;
	}
	public String getFromUserIdstr() {
		return fromUserIdstr;
	}
	public void setFromUserIdstr(String fromUserIdstr) {
		this.fromUserIdstr = fromUserIdstr;
	}
	public String getTraceNos() {
		return traceNos;
	}
	public void setTraceNos(String traceNos) {
		this.traceNos = traceNos;
	}
	
		
	public List<Client> getHotleadsList() {
		return hotleadsList;
	}
	public void setHotleadsList(List<Client> hotleadsList) {
		this.hotleadsList = hotleadsList;
	}
	public List<Client> getDroppedList() {
		return droppedList;
	}
	public void setDroppedList(List<Client> droppedList) {
		this.droppedList = droppedList;
	}
	public String getFollowupDate() {
		return followupDate;
	}
	public void setFollowupDate(String followupDate) {
		this.followupDate = followupDate;
	}
	public String getStatusflag() {
		return statusflag;
	}
	public void setStatusflag(String statusflag) {
		this.statusflag = statusflag;
	}
	public String getEmaillogDescription() {
		return emaillogDescription;
	}
	public void setEmaillogDescription(String emaillogDescription) {
		this.emaillogDescription = emaillogDescription;
	}
	public String getFullEmail() {
		return fullEmail;
	}
	public void setFullEmail(String fullEmail) {
		this.fullEmail = fullEmail;
	}
	public int getMailflag2() {
		return mailflag2;
	}
	public void setMailflag2(int mailflag2) {
		this.mailflag2 = mailflag2;
	}
	public int getMailflag3() {
		return mailflag3;
	}
	public void setMailflag3(int mailflag3) {
		this.mailflag3 = mailflag3;
	}
	public int getSqlemail2() {
		return sqlemail2;
	}
	public void setSqlemail2(int sqlemail2) {
		this.sqlemail2 = sqlemail2;
	}
	public int getSqlemail3() {
		return sqlemail3;
	}
	public void setSqlemail3(int sqlemail3) {
		this.sqlemail3 = sqlemail3;
	}
	public int getSqlemail() {
		return sqlemail;
	}
	public void setSqlemail(int sqlemail) {
		this.sqlemail = sqlemail;
	}
	public List<Client> getNoteslist() {
		return noteslist;
	}
	public void setNoteslist(List<Client> noteslist) {
		this.noteslist = noteslist;
	}
	public List<Client> getAllclientsList() {
		return allclientsList;
	}
	public void setAllclientsList(List<Client> allclientsList) {
		this.allclientsList = allclientsList;
	}
	public String getDataSourceId() {
		return dataSourceId;
	}
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
	public String getFollowupWith() {
		return followupWith;
	}
	public void setFollowupWith(String followupWith) {
		this.followupWith = followupWith;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public String getTraceNo() 
	{
		return traceNo;
	}
	public void setTraceNo(String traceNo) 
	{
		this.traceNo = traceNo;
	}
	public String getClientName() 
	{
		return clientName;
	}
	public void setClientName(String clientName) 
	{
		this.clientName = clientName;
	}
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public String getCitiesId() 
	{
		return citiesId;
	}
	public void setCitiesId(String citiesId) 
	{
		this.citiesId = citiesId;
	}
	public String getStatesId() 
	{
		return statesId;
	}
	public void setStatesId(String statesId) 
	{
		this.statesId = statesId;
	}
	public String getCountriesId() 
	{
		return countriesId;
	}
	public void setCountriesId(String countriesId) 
	{
		this.countriesId = countriesId;
	}
	public String getZip() 
	{
		return zip;
	}
	public void setZip(String zip) 
	{
		this.zip = zip;
	}
	public String getDeptId() 
	{
		return deptId;
	}
	public void setDeptId(String deptId) 
	{
		this.deptId = deptId;
	}
	public String getContactPerson() 
	{
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) 
	{
		this.contactPerson = contactPerson;
	}
	public String getPhoneNumber() 
	{
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}
	public String getFax() 
	{
		return fax;
	}
	public void setFax(String fax) 
	{
		this.fax = fax;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getStatusId() 
	{
		return statusId;
	}
	public void setStatusId(String statusId) 
	{
		this.statusId = statusId;
	}
	public String getWebsite() 
	{
		return website;
	}
	public void setWebsite(String website) 
	{
		this.website = website;
	}
	public String getSourceId() 
	{
		return sourceId;
	}
	public void setSourceId(String sourceId) 
	{
		this.sourceId = sourceId;
	}
	public String getAnnualRevenue() 
	{
		return annualRevenue;
	}
	public void setAnnualRevenue(String annualRevenue) 
	{
		this.annualRevenue = annualRevenue;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getExistingCustomer() 
	{
		return existingCustomer;
	}
	public void setExistingCustomer(String existingCustomer) 
	{
		this.existingCustomer = existingCustomer;
	}
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
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
	public List<CRMDepartment> getDepartmentidlist() 
	{
		return departmentidlist;
	}
	public void setDepartmentidlist(List<CRMDepartment> departmentidlist) 
	{
		this.departmentidlist = departmentidlist;
	}
	public List<CRMState> getStatelist() 
	{
		return statelist;
	}
	public void setStatelist(List<CRMState> statelist) 
	{
		this.statelist = statelist;
	}
	public List<CRMCity> getCitylist() 
	{
		return citylist;
	}
	public void setCitylist(List<CRMCity> citylist) 
	{
		this.citylist = citylist;
	}
	public List<CRMCountry> getCountrylist() 
	{
		return countrylist;
	}
	public void setCountrylist(List<CRMCountry> countrylist) 
	{
		this.countrylist = countrylist;
	}
	public List<CRMStatus> getStatusidlist() 
	{
		return statusidlist;
	}
	public void setStatusidlist(List<CRMStatus> statusidlist) 
	{
		this.statusidlist = statusidlist;
	}
	public List<CRMSource> getSourceidlist() 
	{
		return sourceidlist;
	}
	public void setSourceidlist(List<CRMSource> sourceidlist) 
	{
		this.sourceidlist = sourceidlist;
	}
	public List<CRMUser> getUserlist() {
		
		return userlist;
	}
	public void setUserlist(List<CRMUser> userlist) 
	{
		this.userlist = userlist;
	}
	public String getLoginName() 
	{
		return loginName;
	}
	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}
	public String getFrompage() 
	{
		return frompage;
	}
	public void setFrompage(String frompage) 
	{
		this.frompage = frompage;
	}
	public int getFlag() 
	{
		return flag;
	}
	public void setFlag(int flag) 
	{
		this.flag = flag;
	}
	public String getLogId() 
	{
		return logId;
	}
	public void setLogId(String logId) 
	{
		this.logId = logId;
	}
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	public String getHiddenuserid() 
	{
		return hiddenuserid;
	}
	public void setHiddenuserid(String hiddenuserid) 
	{
		this.hiddenuserid = hiddenuserid;
	}
	public String getCountryName() 
	{
		return countryName;
	}
	public void setCountryName(String countryName) 
	{
		this.countryName = countryName;
	}
	public String getStateName() 
	{
		return stateName;
	}
	public void setStateName(String stateName) 
	{
		this.stateName = stateName;
	}
	public String getCityName() 
	{
		return cityName;
	}
	public void setCityName(String cityName) 
	{
		this.cityName = cityName;
	}
	public String getDepartmentName() 
	{
		return departmentName;
	}
	public void setDepartmentName(String departmentName) 
	{
		this.departmentName = departmentName;
	}
	public String getStatusName() 
	{
		return statusName;
	}
	public void setStatusName(String statusName) 
	{
		this.statusName = statusName;
	}
	public String getSourceName() 
	{
		return sourceName;
	}
	public void setSourceName(String sourceName) 
	{
		this.sourceName = sourceName;
	}
	public String getFromname() 
	{
		return fromname;
	}
	public void setFromname(String fromname) 
	{
		this.fromname = fromname;
	}
	public String getToname() 
	{
		return toname;
	}
	public void setToname(String toname) 
	{
		this.toname = toname;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public int getNameflag() 
	{
		return nameflag;
	}
	public void setNameflag(int nameflag) 
	{
		this.nameflag = nameflag;
	}
	
	public int getWebsiteflag() 
	{
		return websiteflag;
	}
	public void setWebsiteflag(int websiteflag) 
	{
		this.websiteflag = websiteflag;
	}
	public int getPhoneflag() 
	{
		return phoneflag;
	}
	public void setPhoneflag(int phoneflag)
	{
		this.phoneflag = phoneflag;
	}
	public String getLogDescription() 
	{
		return logDescription;
	}
	public void setLogDescription(String logDescription) 
	{
		this.logDescription = logDescription;
	}
	public String getLogclientname() 
	{
		return logclientname;
	}
	public void setLogclientname(String logclientname)
	{
		this.logclientname = logclientname;
	}
	public List<Client> getLogTraceList() 
	{
		return logTraceList;
	}
	public void setLogTraceList(List<Client> logTraceList) 
	{
		this.logTraceList = logTraceList;
	}
	public List<Client> getClientList() 
	{
		return clientList;
	}
	public void setClientList(List<Client> clientList) 
	{
		this.clientList = clientList;
	}
	public String getRoleid() 
	{
		return roleid;
	}
	public void setRoleid(String roleid) 
	{
		this.roleid = roleid;
	}
	public String getDataCollectedUser() 
	{
		return dataCollectedUser;
	}
	public void setDataCollectedUser(String dataCollectedUser) 
	{
		this.dataCollectedUser = dataCollectedUser;
	}
	public String getAssignToUser() 
	{
		return assignToUser;
	}
	public void setAssignToUser(String assignToUser) 
	{
		this.assignToUser = assignToUser;
	}
	public String getFromuserid() 
	{
		return fromuserid;
	}
	public void setFromuserid(String fromuserid) 
	{
		this.fromuserid = fromuserid;
	}
	public String getLastnote() 
	{
		return lastnote;
	}
	public void setLastnote(String lastnote)
	{
		this.lastnote = lastnote;
	}
	public List<Client> getTraceList()
	{
		return traceList;
	}
	public void setTraceList(List<Client> traceList) 
	{
		this.traceList = traceList;
	}
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	public String getFromDate() 
	{
		return fromDate;
	}
	public void setFromDate(String fromDate) 
	{
		this.fromDate = fromDate;
	}
	public String getToDate() 
	{
		return toDate;
	}
	public void setToDate(String toDate) 
	{
		this.toDate = toDate;
	}
	public List<Client> getDepartmentList() 
	{
		return departmentList;
	}
	public void setDepartmentList(List<Client> departmentList) 
	{
		this.departmentList = departmentList;
	}
	public String getExtension() 
	{
		return extension;
	}
	public void setExtension(String extension) 
	{
		this.extension = extension;
	}
	public String getMobileNumber() 
	{
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}
	public String getAlternateMobileNumber() 
	{
		return alternateMobileNumber;
	}
	public void setAlternateMobileNumber(String alternateMobileNumber)
	{
		this.alternateMobileNumber = alternateMobileNumber;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getEmail3() {
		return email3;
	}
	public void setEmail3(String email3) {
		this.email3 = email3;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogemail() {
		return logemail;
	}
	public void setLogemail(String logemail) {
		this.logemail = logemail;
	}
	public String getLogPhoneNo() {
		return logPhoneNo;
	}
	public void setLogPhoneNo(String logPhoneNo) {
		this.logPhoneNo = logPhoneNo;
	}
	public String getLogwebsite() {
		return logwebsite;
	}
	public void setLogwebsite(String logwebsite) {
		this.logwebsite = logwebsite;
	}
	public List<Client> getOpenDealsList() {
		return openDealsList;
	}
	public void setOpenDealsList(List<Client> openDealsList) {
		this.openDealsList = openDealsList;
	}
	public List<Client> getEmailSentList() {
		return emailSentList;
	}
	public void setEmailSentList(List<Client> emailSentList) {
		this.emailSentList = emailSentList;
	}
	public List<Client> getFollowupList() {
		return followupList;
	}
	public void setFollowupList(List<Client> followupList) {
		this.followupList = followupList;
	}
	public List<Client> getOverdueDealsList() {
		return overdueDealsList;
	}
	public void setOverdueDealsList(List<Client> overdueDealsList) {
		this.overdueDealsList = overdueDealsList;
	}
	public List<Client> getOverdueEmailSentList() {
		return overdueEmailSentList;
	}
	public void setOverdueEmailSentList(List<Client> overdueEmailSentList) {
		this.overdueEmailSentList = overdueEmailSentList;
	}
	public List<Client> getOverdueFollowupList() {
		return overdueFollowupList;
	}
	public void setOverdueFollowupList(List<Client> overdueFollowupList) {
		this.overdueFollowupList = overdueFollowupList;
	}
	public String getManageTraceNo() {
		return manageTraceNo;
	}
	public void setManageTraceNo(String manageTraceNo) {
		this.manageTraceNo = manageTraceNo;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public List<String> getSourceEventList() {
		return sourceEventList;
	}
	public void setSourceEventList(List<String> sourceEventList) {
		this.sourceEventList = sourceEventList;
	}
	public List<CRMConference> getEventList() 
	{
		return eventList;
	}
	public void setEventList(List<CRMConference> eventList)
	{
		this.eventList = eventList;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getNotesDate() {
		return notesDate;
	}
	public void setNotesDate(String notesDate) {
		this.notesDate = notesDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getFollowUpDate() {
		return followUpDate;
	}
	public void setFollowUpDate(String followUpDate) {
		this.followUpDate = followUpDate;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public int getMobileFlag() {
		return mobileFlag;
	}
	public void setMobileFlag(int mobileFlag) {
		this.mobileFlag = mobileFlag;
	}
	public int getAltMobileFlag() {
		return altMobileFlag;
	}
	public void setAltMobileFlag(int altMobileFlag) {
		this.altMobileFlag = altMobileFlag;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getContactPersonTwo() {
		return contactPersonTwo;
	}
	public void setContactPersonTwo(String contactPersonTwo) {
		this.contactPersonTwo = contactPersonTwo;
	}
	public String getDesignationTwo() {
		return designationTwo;
	}
	public void setDesignationTwo(String designationTwo) {
		this.designationTwo = designationTwo;
	}
	public String getHiddenClientName() {
		return hiddenClientName;
	}
	public void setHiddenClientName(String hiddenClientName) {
		this.hiddenClientName = hiddenClientName;
	}
	public String getHiddenTraceNo() {
		return hiddenTraceNo;
	}
	public void setHiddenTraceNo(String hiddenTraceNo) {
		this.hiddenTraceNo = hiddenTraceNo;
	}
	public String getSearchbox() {
		return searchbox;
	}
	public void setSearchbox(String searchbox) {
		this.searchbox = searchbox;
	}
	public String getTextbox() {
		return textbox;
	}
	public void setTextbox(String textbox) {
		this.textbox = textbox;
	}
	public String getUploadClientName() {
		return uploadClientName;
	}
	public void setUploadClientName(String uploadClientName) {
		this.uploadClientName = uploadClientName;
	}
	public List<Client> getClosedList() {
		return closedList;
	}
	public void setClosedList(List<Client> closedList) {
		this.closedList = closedList;
	}
	public String getServicesId() {
		return servicesId;
	}
	public void setServicesId(String servicesId) {
		this.servicesId = servicesId;
	}
	public List<CRMServices> getServiceslist() {
		return serviceslist;
	}
	public void setServiceslist(List<CRMServices> serviceslist) {
		this.serviceslist = serviceslist;
	}
	public String getServicesName() {
		return servicesName;
	}
	public void setServicesName(String servicesName) {
		this.servicesName = servicesName;
	}
	public String getBucketId() {
		return bucketId;
	}
	public void setBucketId(String bucketId) {
		this.bucketId = bucketId;
	}
	public List<CRMBucket> getBucketList() {
		return bucketList;
	}
	public void setBucketList(List<CRMBucket> bucketList) {
		this.bucketList = bucketList;
	}
	public String getMailflag() {
		return mailflag;
	}
	public void setMailflag(String mailflag) {
		this.mailflag = mailflag;
	}
	public String getAggreement() {
		return aggreement;
	}
	public void setAggreement(String aggreement) {
		this.aggreement = aggreement;
	}
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	

}
