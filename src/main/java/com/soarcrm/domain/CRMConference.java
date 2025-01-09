package com.soarcrm.domain;

import java.util.List;

public class CRMConference 
{
	private String eventId;
	private String eventCode;
	private String eventName;
	private String eventMonth;
	private String description;
	private String deptId;
	private String deptname;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String loginName;
	
	private List<CRMDepartment> departmentidlist;
	
	public String getEventId() 
	{
		return eventId;
	}
	public void setEventId(String eventId) 
	{
		this.eventId = eventId;
	}
	public String getEventCode() 
	{
		return eventCode;
	}
	public void setEventCode(String eventCode) 
	{
		this.eventCode = eventCode;
	}
	public String getEventName() 
	{
		return eventName;
	}
	public void setEventName(String eventName) 
	{
		this.eventName = eventName;
	}
	public String getEventMonth() 
	{
		return eventMonth;
	}
	public void setEventMonth(String eventMonth) 
	{
		this.eventMonth = eventMonth;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getDeptId() 
	{
		return deptId;
	}
	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
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
	public List<CRMDepartment> getDepartmentidlist() 
	{
		return departmentidlist;
	}
	public void setDepartmentidlist(List<CRMDepartment> departmentidlist) 
	{
		this.departmentidlist = departmentidlist;
	}
	public String getDeptname()
	{
		return deptname;
	}
	public void setDeptname(String deptname) 
	{
		this.deptname = deptname;
	}
	
	

}
