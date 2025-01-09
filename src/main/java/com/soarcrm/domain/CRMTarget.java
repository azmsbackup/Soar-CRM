package com.soarcrm.domain;

import java.util.List;

public class CRMTarget 
{
	private String targetsId;
	private String userId;
	private String activityDescription;
	private String dailyTarget;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String loginName;
	private List<CRMUser> userlist;
	private List<CRMTarget> openTargetlist;
	private List<CRMStatus> statusList;
	private String name;
	private String frompage;
	private String backbutton;
	private String add;
	private String statusId;
	private String effectiveFrom;
	private String effectiveTo;
	private String year;
	private String month;
	private String monthName;
	private List<String> yearlist;
	private List<String> monthlist;
	private String flag;
	private String userStatus;
	private String userName;
	
	private List<CRMTarget> followUpTargetlist;
	private List<CRMTarget> emailSentTargetlist;
	private List<CRMTarget> responseTargetlist;
	private List<CRMTarget> closeTargetlist;
	
	private String emailTargetId;
	private String followupTargetId;
	private String responseTargetId;
	private String closeTargetId;
	
	public String getTargetsId()
	{
		return targetsId;
	}
	public void setTargetsId(String targetsId) 
	{
		this.targetsId = targetsId;
	}
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	public String getActivityDescription() 
	{
		return activityDescription;
	}
	public void setActivityDescription(String activityDescription) 
	{
		this.activityDescription = activityDescription;
	}
	public String getDailyTarget() 
	{
		return dailyTarget;
	}
	public void setDailyTarget(String dailyTarget) 
	{
		this.dailyTarget = dailyTarget;
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
	public List<CRMUser> getUserlist() 
	{
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
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public List<CRMTarget> getOpenTargetlist() 
	{
		return openTargetlist;
	}
	public void setOpenTargetlist(List<CRMTarget> openTargetlist) 
	{
		this.openTargetlist = openTargetlist;
	}
	public String getFrompage() 
	{
		return frompage;
	}
	public void setFrompage(String frompage) 
	{
		this.frompage = frompage;
	}
	public String getBackbutton() 
	{
		return backbutton;
	}
	public void setBackbutton(String backbutton) 
	{
		this.backbutton = backbutton;
	}
	public String getAdd() 
	{
		return add;
	}
	public void setAdd(String add) 
	{
		this.add = add;
	}
	public String getStatusId()
	{
		return statusId;
	}
	public void setStatusId(String statusId) 
	{
		this.statusId = statusId;
	}
	public String getEffectiveFrom() 
	{
		return effectiveFrom;
	}
	public void setEffectiveFrom(String effectiveFrom) 
	{
		this.effectiveFrom = effectiveFrom;
	}
	public String getEffectiveTo() 
	{
		return effectiveTo;
	}
	public void setEffectiveTo(String effectiveTo) 
	{
		this.effectiveTo = effectiveTo;
	}
	public List<CRMStatus> getStatusList() 
	{
		return statusList;
	}
	public void setStatusList(List<CRMStatus> statusList) 
	{
		this.statusList = statusList;
	}
	public List<String> getYearlist() 
	{
		return yearlist;
	}
	public void setYearlist(List<String> yearlist) 
	{
		this.yearlist = yearlist;
	}
	public List<String> getMonthlist() 
	{
		return monthlist;
	}
	public void setMonthlist(List<String> monthlist) 
	{
		this.monthlist = monthlist;
	}
	public String getYear() 
	{
		return year;
	}
	public void setYear(String year) 
	{
		this.year = year;
	}
	public String getMonth()
	{
		return month;
	}
	public void setMonth(String month) 
	{
		this.month = month;
	}
	public String getMonthName()
	{
		return monthName;
	}
	public void setMonthName(String monthName) 
	{
		this.monthName = monthName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<CRMTarget> getFollowUpTargetlist() {
		return followUpTargetlist;
	}
	public void setFollowUpTargetlist(List<CRMTarget> followUpTargetlist) {
		this.followUpTargetlist = followUpTargetlist;
	}
	public List<CRMTarget> getEmailSentTargetlist() {
		return emailSentTargetlist;
	}
	public void setEmailSentTargetlist(List<CRMTarget> emailSentTargetlist) {
		this.emailSentTargetlist = emailSentTargetlist;
	}
	public List<CRMTarget> getResponseTargetlist() {
		return responseTargetlist;
	}
	public void setResponseTargetlist(List<CRMTarget> responseTargetlist) {
		this.responseTargetlist = responseTargetlist;
	}
	public String getEmailTargetId() {
		return emailTargetId;
	}
	public void setEmailTargetId(String emailTargetId) {
		this.emailTargetId = emailTargetId;
	}
	public String getFollowupTargetId() {
		return followupTargetId;
	}
	public void setFollowupTargetId(String followupTargetId) {
		this.followupTargetId = followupTargetId;
	}
	public String getResponseTargetId() {
		return responseTargetId;
	}
	public void setResponseTargetId(String responseTargetId) {
		this.responseTargetId = responseTargetId;
	}
	public List<CRMTarget> getCloseTargetlist() {
		return closeTargetlist;
	}
	public void setCloseTargetlist(List<CRMTarget> closeTargetlist) {
		this.closeTargetlist = closeTargetlist;
	}
	public String getCloseTargetId() {
		return closeTargetId;
	}
	public void setCloseTargetId(String closeTargetId) {
		this.closeTargetId = closeTargetId;
	}

}
