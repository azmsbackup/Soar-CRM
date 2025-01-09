package com.soarcrm.domain;

import java.util.List;

public class CRMDepartment 
{
	private String departmentId;
	private String departmentCode;
	private String departmentName;
	private String status;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String loginname;
	private int flag;
	private List<CRMDepartment> departmentList;
	private String userName;
	private String checkbox;
	private String userId;
	private List<CRMDepartment> checkboxList;
	private List<CRMDepartment> usermappingList;
	private String checked;
	private String size;
	
	public String getDepartmentId() 
	{
		return departmentId;
	}
	public void setDepartmentId(String departmentId) 
	{
		this.departmentId = departmentId;
	}
	public String getDepartmentCode() 
	{
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) 
	{
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() 
	{
		return departmentName;
	}
	public void setDepartmentName(String departmentName) 
	{
		this.departmentName = departmentName;
	}
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
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
	public String getLoginname() 
	{
		return loginname;
	}
	public void setLoginname(String loginname) 
	{
		this.loginname = loginname;
	}
	public int getFlag() 
	{
		return flag;
	}
	public void setFlag(int flag) 
	{
		this.flag = flag;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	public List<CRMDepartment> getDepartmentList()
	{
		return departmentList;
	}
	public void setDepartmentList(List<CRMDepartment> departmentList) 
	{
		this.departmentList = departmentList;
	}
	public String getCheckbox() 
	{
		return checkbox;
	}
	public void setCheckbox(String checkbox) 
	{
		this.checkbox = checkbox;
	}
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	public List<CRMDepartment> getCheckboxList() 
	{
		return checkboxList;
	}
	public void setCheckboxList(List<CRMDepartment> checkboxList) 
	{
		this.checkboxList = checkboxList;
	}
	public List<CRMDepartment> getUsermappingList() 
	{
		return usermappingList;
	}
	public void setUsermappingList(List<CRMDepartment> usermappingList) 
	{
		this.usermappingList = usermappingList;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	

}
