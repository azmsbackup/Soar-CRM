package com.soarcrm.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class CRMStatus 
{
	private String statusId;
	private String statusDescription;
	private String crmstatus;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String loginName;
	private String opendeals;
	private String closedDeals;
	private String followup;
	private String emailsent;
	private String overdueDeal;
	
	private String opendealsdaily;
	private String followupdaily;
	private String emailsentdaily;
	private String responsedaily;
	private String closedaily;
	
	private String opendealsweekly;
	private String followupweekly;
	private String emailsentweekly;
	private String responseweekly;
	private String closeweekly;
	
	private String opendealsmonthly;
	private String followupmonthly;
	private String emailsentmonthly;
	private String reponsemonthly;
	private String closemonthly;
	
	private String targetopendaily;
	private String targetfollowupdaily;
	private String targetemaildaily;
	private String targetresponsedaily;
	private String targetcloseedaily;
	
	private String targetopenweekly;
	private String targetfollowupweekly;
	private String targetemailweekly;
	private String targetresponseweekly;
	private String targetcloseeweekly;
	
	private String targetopenmonthly;
	private String targetfollowupmonthly;
	private String targetemailmonthly;
	private String targetresponsemonthly;
	private String targetcloseemonthly;
	
	private String yearlyDeals;
	private String firsthalfYearlyDeals;
	private String secondhalfYearlyDeals;
	private String firstQuarterlyDeals;
	private String secondQuarterlyDeals;
	private String thirdQuarterlyDeals;
	private String fourthQuarterlyDeals;
	
	private String targetYearlyDeals;
	private String targetFirstHalfYearlyDeals;
	private String targetSecondHalfYearlyDeals;
	private String targetFirstQuarterlyDeals;
	private String targetSecondQuarterlyDeals;
	private String targetThirdQuarterlyDeals;
	private String targetFourthQuarterlyDeals;
	
	private int flag;
	private String roleid;
	private String userid;
	
	private String overduefollowup;
	private String overdueemail;
	
	private String won;
	private String cold;
	private String inProgress;
	private String lost;
	
	private String coding_open;
	private String coding_email;
	private String coding_followup;
	
	private String billing_open;
	private String billing_email;
	private String billing_followup;
	
	private String response_year;
	private String response_month;
	private String closed_year;
	private String closed_month;
	
	private String fromDate;
	private String toDate;
	
	 private List<CRMStatus> openList;
	 private List<CRMUser> userlist;
	 private List<CRMStatus> managerDashboardList;
	 
	 private List<String> statusList;
	 private List<CRMStatus> productivityList;
	 private String frompage;
	 private String userId;
	 
	 private HashMap<String, List<CRMStatus>> statushashmap;
	 
	 private List<CRMStatus> todayList;
	 private List<CRMStatus> weeklyList;
	 private List<CRMStatus> monthlyList;
	 
	 private String userName;
	 
	 private String userView;
	 private String managerView;
	 
	 private String month;
	 
	 private String actualDateuser;
	 private String targetDateuser;
	 
	 private List<CRMStatus> crmstatusList;
	 private List<CRMStatus> departmentList;
	 private String statusName;
	 
	 private String count;
	 private String departmentName;
	 private String hotleads;
	 private String droppedleads;
	 private String partialdata;
	 
	 private LinkedHashMap<String, LinkedHashMap<String, String>> summaryhashmap;
	 private LinkedHashMap<String, String> statusallhashmap;
	 private HashMap<String, LinkedHashMap<String, List<CRMStatus>>> allusermap;
	 

	
	public String getPartialdata() {
		return partialdata;
	}
	public void setPartialdata(String partialdata) {
		this.partialdata = partialdata;
	}
	public HashMap<String, LinkedHashMap<String, List<CRMStatus>>> getAllusermap() {
		return allusermap;
	}
	public void setAllusermap(HashMap<String, LinkedHashMap<String, List<CRMStatus>>> allusermap) {
		this.allusermap = allusermap;
	}
	public String getHotleads() {
		return hotleads;
	}
	public void setHotleads(String hotleads) {
		this.hotleads = hotleads;
	}
	public String getDroppedleads() {
		return droppedleads;
	}
	public void setDroppedleads(String droppedleads) {
		this.droppedleads = droppedleads;
	}
	public String getStatusId() 
	{
		return statusId;
	}
	public void setStatusId(String statusId) 
	{
		this.statusId = statusId;
	}
	public String getStatusDescription() 
	{
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) 
	{
		this.statusDescription = statusDescription;
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
	public String getLoginName() 
	{
		return loginName;
	}
	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}
	public String getCrmstatus() 
	{
		return crmstatus;
	}
	public void setCrmstatus(String crmstatus) 
	{
		this.crmstatus = crmstatus;
	}
	public String getOpendeals() 
	{
		return opendeals;
	}
	public void setOpendeals(String opendeals) 
	{
		this.opendeals = opendeals;
	}
	public String getFollowup() 
	{
		return followup;
	}
	public void setFollowup(String followup) 
	{
		this.followup = followup;
	}
	public String getEmailsent() 
	{
		return emailsent;
	}
	public void setEmailsent(String emailsent) 
	{
		this.emailsent = emailsent;
	}
	public String getOpendealsdaily()
	{
		return opendealsdaily;
	}
	public void setOpendealsdaily(String opendealsdaily) 
	{
		this.opendealsdaily = opendealsdaily;
	}
	public String getFollowupdaily() 
	{
		return followupdaily;
	}
	public void setFollowupdaily(String followupdaily) 
	{
		this.followupdaily = followupdaily;
	}
	public String getEmailsentdaily() 
	{
		return emailsentdaily;
	}
	public void setEmailsentdaily(String emailsentdaily) 
	{
		this.emailsentdaily = emailsentdaily;
	}
	public String getOpendealsweekly() 
	{
		return opendealsweekly;
	}
	public void setOpendealsweekly(String opendealsweekly) 
	{
		this.opendealsweekly = opendealsweekly;
	}
	public String getFollowupweekly() 
	{
		return followupweekly;
	}
	public void setFollowupweekly(String followupweekly) 
	{
		this.followupweekly = followupweekly;
	}
	public String getEmailsentweekly() 
	{
		return emailsentweekly;
	}
	public void setEmailsentweekly(String emailsentweekly) 
	{
		this.emailsentweekly = emailsentweekly;
	}
	public String getOpendealsmonthly() 
	{
		return opendealsmonthly;
	}
	public void setOpendealsmonthly(String opendealsmonthly) 
	{
		this.opendealsmonthly = opendealsmonthly;
	}
	public String getFollowupmonthly() 
	{
		return followupmonthly;
	}
	public void setFollowupmonthly(String followupmonthly) 
	{
		this.followupmonthly = followupmonthly;
	}
	public String getEmailsentmonthly() 
	{
		return emailsentmonthly;
	}
	public void setEmailsentmonthly(String emailsentmonthly) 
	{
		this.emailsentmonthly = emailsentmonthly;
	}
	public String getTargetopendaily() 
	{
		return targetopendaily;
	}
	public void setTargetopendaily(String targetopendaily) 
	{
		this.targetopendaily = targetopendaily;
	}
	public String getTargetfollowupdaily() 
	{
		return targetfollowupdaily;
	}
	public void setTargetfollowupdaily(String targetfollowupdaily) 
	{
		this.targetfollowupdaily = targetfollowupdaily;
	}
	public String getTargetemaildaily() 
	{
		return targetemaildaily;
	}
	public void setTargetemaildaily(String targetemaildaily) 
	{
		this.targetemaildaily = targetemaildaily;
	}
	public String getTargetopenweekly() 
	{
		return targetopenweekly;
	}
	public void setTargetopenweekly(String targetopenweekly) 
	{
		this.targetopenweekly = targetopenweekly;
	}
	public String getTargetfollowupweekly() 
	{
		return targetfollowupweekly;
	}
	public void setTargetfollowupweekly(String targetfollowupweekly) 
	{
		this.targetfollowupweekly = targetfollowupweekly;
	}
	public String getTargetemailweekly() 
	{
		return targetemailweekly;
	}
	public void setTargetemailweekly(String targetemailweekly) 
	{
		this.targetemailweekly = targetemailweekly;
	}
	public String getTargetopenmonthly() 
	{
		return targetopenmonthly;
	}
	public void setTargetopenmonthly(String targetopenmonthly) 
	{
		this.targetopenmonthly = targetopenmonthly;
	}
	public String getTargetfollowupmonthly() 
	{
		return targetfollowupmonthly;
	}
	public void setTargetfollowupmonthly(String targetfollowupmonthly) 
	{
		this.targetfollowupmonthly = targetfollowupmonthly;
	}
	public String getTargetemailmonthly() 
	{
		return targetemailmonthly;
	}
	public void setTargetemailmonthly(String targetemailmonthly) 
	{
		this.targetemailmonthly = targetemailmonthly;
	}
	public String getOverdueDeal() 
	{
		return overdueDeal;
	}
	public void setOverdueDeal(String overdueDeal) 
	{
		this.overdueDeal = overdueDeal;
	}
	public String getResponsedaily() 
	{
		return responsedaily;
	}
	public void setResponsedaily(String responsedaily) 
	{
		this.responsedaily = responsedaily;
	}
	public String getResponseweekly() 
	{
		return responseweekly;
	}
	public void setResponseweekly(String responseweekly) 
	{
		this.responseweekly = responseweekly;
	}
	public String getReponsemonthly() 
	{
		return reponsemonthly;
	}
	public void setReponsemonthly(String reponsemonthly)
	{
		this.reponsemonthly = reponsemonthly;
	}
	public String getTargetresponsedaily() 
	{
		return targetresponsedaily;
	}
	public void setTargetresponsedaily(String targetresponsedaily) 
	{
		this.targetresponsedaily = targetresponsedaily;
	}
	public String getTargetresponseweekly()
	{
		return targetresponseweekly;
	}
	public void setTargetresponseweekly(String targetresponseweekly)
	{
		this.targetresponseweekly = targetresponseweekly;
	}
	public String getTargetresponsemonthly() 
	{
		return targetresponsemonthly;
	}
	public void setTargetresponsemonthly(String targetresponsemonthly) 
	{
		this.targetresponsemonthly = targetresponsemonthly;
	}
	public int getFlag() 
	{
		return flag;
	}
	public void setFlag(int flag) 
	{
		this.flag = flag;
	}
	public String getRoleid() 
	{
		return roleid;
	}
	public void setRoleid(String roleid) 
	{
		this.roleid = roleid;
	}
	public String getUserid() 
	{
		return userid;
	}
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}
	public String getOverduefollowup() 
	{
		return overduefollowup;
	}
	public void setOverduefollowup(String overduefollowup) 
	{
		this.overduefollowup = overduefollowup;
	}
	public String getOverdueemail() 
	{
		return overdueemail;
	}
	public void setOverdueemail(String overdueemail) 
	{
		this.overdueemail = overdueemail;
	}
	public String getWon() 
	{
		return won;
	}
	public void setWon(String won) 
	{
		this.won = won;
	}
	public String getCold() 
	{
		return cold;
	}
	public void setCold(String cold) 
	{
		this.cold = cold;
	}
	public String getInProgress() 
	{
		return inProgress;
	}
	public void setInProgress(String inProgress) 
	{
		this.inProgress = inProgress;
	}
	public String getLost() 
	{
		return lost;
	}
	public void setLost(String lost) 
	{
		this.lost = lost;
	}
	public String getCoding_open() 
	{
		return coding_open;
	}
	public void setCoding_open(String coding_open) 
	{
		this.coding_open = coding_open;
	}
	public String getCoding_email() 
	{
		return coding_email;
	}
	public void setCoding_email(String coding_email) 
	{
		this.coding_email = coding_email;
	}
	public String getCoding_followup() 
	{
		return coding_followup;
	}
	public void setCoding_followup(String coding_followup) 
	{
		this.coding_followup = coding_followup;
	}
	public String getBilling_open() 
	{
		return billing_open;
	}
	public void setBilling_open(String billing_open) 
	{
		this.billing_open = billing_open;
	}
	public String getBilling_email() 
	{
		return billing_email;
	}
	public void setBilling_email(String billing_email) 
	{
		this.billing_email = billing_email;
	}
	public String getBilling_followup() 
	{
		return billing_followup;
	}
	public void setBilling_followup(String billing_followup) 
	{
		this.billing_followup = billing_followup;
	}
	public String getResponse_year() 
	{
		return response_year;
	}
	public void setResponse_year(String response_year) 
	{
		this.response_year = response_year;
	}
	public String getResponse_month() {
		return response_month;
	}
	public void setResponse_month(String response_month) 
	{
		this.response_month = response_month;
	}
	public String getClosed_year() 
	{
		return closed_year;
	}
	public void setClosed_year(String closed_year) 
	{
		this.closed_year = closed_year;
	}
	public String getClosed_month() 
	{
		return closed_month;
	}
	public void setClosed_month(String closed_month) 
	{
		this.closed_month = closed_month;
	}
	public List<CRMStatus> getOpenList() 
	{
		return openList;
	}
	public void setOpenList(List<CRMStatus> openList) 
	{
		this.openList = openList;
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
	public List<CRMUser> getUserlist() 
	{
		return userlist;
	}
	public void setUserlist(List<CRMUser> userlist) 
	{
		this.userlist = userlist;
	}
	public List<CRMStatus> getManagerDashboardList() 
	{
		return managerDashboardList;
	}
	public void setManagerDashboardList(List<CRMStatus> managerDashboardList) 
	{
		this.managerDashboardList = managerDashboardList;
	}
	public List<String> getStatusList() 
	{
		return statusList;
	}
	public void setStatusList(List<String> statusList) 
	{
		this.statusList = statusList;
	}
	public List<CRMStatus> getProductivityList() 
	{
		return productivityList;
	}
	public void setProductivityList(List<CRMStatus> productivityList) 
	{
		this.productivityList = productivityList;
	}
	public String getFrompage() 
	{
		return frompage;
	}
	public void setFrompage(String frompage) 
	{
		this.frompage = frompage;
	}
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	public HashMap<String, List<CRMStatus>> getStatushashmap() 
	{
		return statushashmap;
	}
	public void setStatushashmap(HashMap<String, List<CRMStatus>> statushashmap) 
	{
		this.statushashmap = statushashmap;
	}
	public List<CRMStatus> getTodayList()
	{
		return todayList;
	}
	public void setTodayList(List<CRMStatus> todayList) 
	{
		this.todayList = todayList;
	}
	public List<CRMStatus> getWeeklyList()
	{
		return weeklyList;
	}
	public void setWeeklyList(List<CRMStatus> weeklyList) 
	{
		this.weeklyList = weeklyList;
	}
	public List<CRMStatus> getMonthlyList() 
	{
		return monthlyList;
	}
	public void setMonthlyList(List<CRMStatus> monthlyList) 
	{
		this.monthlyList = monthlyList;
	}
	public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	public String getYearlyDeals()
	{
		return yearlyDeals;
	}
	public void setYearlyDeals(String yearlyDeals) 
	{
		this.yearlyDeals = yearlyDeals;
	}
	public String getTargetYearlyDeals() 
	{
		return targetYearlyDeals;
	}
	public void setTargetYearlyDeals(String targetYearlyDeals) 
	{
		this.targetYearlyDeals = targetYearlyDeals;
	}
	public String getUserView() 
	{
		return userView;
	}
	public void setUserView(String userView) 
	{
		this.userView = userView;
	}
	public String getManagerView() 
	{
		return managerView;
	}
	public void setManagerView(String managerView) 
	{
		this.managerView = managerView;
	}
	public String getFirsthalfYearlyDeals() 
	{
		return firsthalfYearlyDeals;
	}
	public void setFirsthalfYearlyDeals(String firsthalfYearlyDeals) 
	{
		this.firsthalfYearlyDeals = firsthalfYearlyDeals;
	}
	public String getSecondhalfYearlyDeals() 
	{
		return secondhalfYearlyDeals;
	}
	public void setSecondhalfYearlyDeals(String secondhalfYearlyDeals)
	{
		this.secondhalfYearlyDeals = secondhalfYearlyDeals;
	}
	public String getTargetFirstHalfYearlyDeals() 
	{
		return targetFirstHalfYearlyDeals;
	}
	public void setTargetFirstHalfYearlyDeals(String targetFirstHalfYearlyDeals) 
	{
		this.targetFirstHalfYearlyDeals = targetFirstHalfYearlyDeals;
	}
	public String getTargetSecondHalfYearlyDeals() 
	{
		return targetSecondHalfYearlyDeals;
	}
	public void setTargetSecondHalfYearlyDeals(String targetSecondHalfYearlyDeals)
	{
		this.targetSecondHalfYearlyDeals = targetSecondHalfYearlyDeals;
	}
	public String getFirstQuarterlyDeals() 
	{
		return firstQuarterlyDeals;
	}
	public void setFirstQuarterlyDeals(String firstQuarterlyDeals) 
	{
		this.firstQuarterlyDeals = firstQuarterlyDeals;
	}
	public String getSecondQuarterlyDeals() 
	{
		return secondQuarterlyDeals;
	}
	public void setSecondQuarterlyDeals(String secondQuarterlyDeals) 
	{
		this.secondQuarterlyDeals = secondQuarterlyDeals;
	}
	public String getFourthQuarterlyDeals() 
	{
		return fourthQuarterlyDeals;
	}
	public void setFourthQuarterlyDeals(String fourthQuarterlyDeals)
	{
		this.fourthQuarterlyDeals = fourthQuarterlyDeals;
	}
	public String getTargetFirstQuarterlyDeals() 
	{
		return targetFirstQuarterlyDeals;
	}
	public void setTargetFirstQuarterlyDeals(String targetFirstQuarterlyDeals) 
	{
		this.targetFirstQuarterlyDeals = targetFirstQuarterlyDeals;
	}
	public String getTargetFourthQuarterlyDeals() 
	{
		return targetFourthQuarterlyDeals;
	}
	public void setTargetFourthQuarterlyDeals(String targetFourthQuarterlyDeals) 
	{
		this.targetFourthQuarterlyDeals = targetFourthQuarterlyDeals;
	}
	public String getTargetSecondQuarterlyDeals() 
	{
		return targetSecondQuarterlyDeals;
	}
	public void setTargetSecondQuarterlyDeals(String targetSecondQuarterlyDeals) 
	{
		this.targetSecondQuarterlyDeals = targetSecondQuarterlyDeals;
	}
	public String getThirdQuarterlyDeals() 
	{
		return thirdQuarterlyDeals;
	}
	public void setThirdQuarterlyDeals(String thirdQuarterlyDeals) 
	{
		this.thirdQuarterlyDeals = thirdQuarterlyDeals;
	}
	public String getTargetThirdQuarterlyDeals() 
	{
		return targetThirdQuarterlyDeals;
	}
	public void setTargetThirdQuarterlyDeals(String targetThirdQuarterlyDeals) 
	{
		this.targetThirdQuarterlyDeals = targetThirdQuarterlyDeals;
	}
	public String getMonth() 
	{
		return month;
	}
	public void setMonth(String month) 
	{
		this.month = month;
	}
	public String getActualDateuser() {
		return actualDateuser;
	}
	public void setActualDateuser(String actualDateuser) {
		this.actualDateuser = actualDateuser;
	}
	public String getTargetDateuser() {
		return targetDateuser;
	}
	public void setTargetDateuser(String targetDateuser) {
		this.targetDateuser = targetDateuser;
	}
	public List<CRMStatus> getCrmstatusList() {
		return crmstatusList;
	}
	public void setCrmstatusList(List<CRMStatus> crmstatusList) {
		this.crmstatusList = crmstatusList;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<CRMStatus> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<CRMStatus> departmentList) {
		this.departmentList = departmentList;
	}
	public LinkedHashMap<String, LinkedHashMap<String, String>> getSummaryhashmap() {
		return summaryhashmap;
	}
	public void setSummaryhashmap(LinkedHashMap<String, LinkedHashMap<String, String>> summaryhashmap) {
		this.summaryhashmap = summaryhashmap;
	}
	public LinkedHashMap<String, String> getStatusallhashmap() {
		return statusallhashmap;
	}
	public void setStatusallhashmap(LinkedHashMap<String, String> statusallhashmap) {
		this.statusallhashmap = statusallhashmap;
	}
	public String getClosedDeals() {
		return closedDeals;
	}
	public void setClosedDeals(String closedDeals) {
		this.closedDeals = closedDeals;
	}
	public String getClosedaily() {
		return closedaily;
	}
	public void setClosedaily(String closedaily) {
		this.closedaily = closedaily;
	}
	public String getCloseweekly() {
		return closeweekly;
	}
	public void setCloseweekly(String closeweekly) {
		this.closeweekly = closeweekly;
	}
	public String getClosemonthly() {
		return closemonthly;
	}
	public void setClosemonthly(String closemonthly) {
		this.closemonthly = closemonthly;
	}
	public String getTargetcloseedaily() {
		return targetcloseedaily;
	}
	public void setTargetcloseedaily(String targetcloseedaily) {
		this.targetcloseedaily = targetcloseedaily;
	}
	public String getTargetcloseeweekly() {
		return targetcloseeweekly;
	}
	public void setTargetcloseeweekly(String targetcloseeweekly) {
		this.targetcloseeweekly = targetcloseeweekly;
	}
	public String getTargetcloseemonthly() {
		return targetcloseemonthly;
	}
	public void setTargetcloseemonthly(String targetcloseemonthly) {
		this.targetcloseemonthly = targetcloseemonthly;
	}
	
	

}
