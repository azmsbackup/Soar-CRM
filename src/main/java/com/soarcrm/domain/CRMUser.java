package com.soarcrm.domain;

import java.util.List;

public class CRMUser 
{
	private String userId;
	private String firstName;
	private String lastName;
	private String loginName;
	private String password;
	private String password2;
	private String confirmPassword;
	private String emailId;
	private String userType;
	private String employeeId;
	private String userStatus;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String userLoginName;
	
	private String mainMenuId;
	private String mainMenuName;
	private String mainMenuhref;
	private String mainMenuClassName;
	
	private List <String> userTypeList;
	private List<CRMMainmenu> mainMenuList;
	private List<CRMMainmenu> subMenuList;
	
	private String roleId;
	private String roleName;
	private List<CRMRolemaster> rolelist;
	private String hiddenroleid;
	private int flag;
	
	private String userpassword;
	private String highSecurityPassword;
	
	private List<CRMDepartment> departmentList;
	private String departmentId;
	
	private List<CRMUser> userList;
	
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}	
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	public String getLoginName() 
	{
		return loginName;
	}
	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getConfirmPassword() 
	{
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) 
	{
		this.confirmPassword = confirmPassword;
	}
	public String getEmailId() 
	{
		return emailId;
	}
	public void setEmailId(String emailId) 
	{
		this.emailId = emailId;
	}
	public String getUserType() 
	{
		return userType;
	}
	public void setUserType(String userType) 
	{
		this.userType = userType;
	}
	public String getEmployeeId() 
	{
		return employeeId;
	}
	public void setEmployeeId(String employeeId) 
	{
		this.employeeId = employeeId;
	}
	public String getUserStatus() 
	{
		return userStatus;
	}
	public void setUserStatus(String userStatus) 
	{
		this.userStatus = userStatus;
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
	public String getUserLoginName() 
	{
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) 
	{
		this.userLoginName = userLoginName;
	}
	public List <String> getUserTypeList() 
	{
		return userTypeList;
	}
	public void setUserTypeList(List <String> userTypeList) 
	{
		this.userTypeList = userTypeList;
	}
	public List<CRMMainmenu> getMainMenuList() 
	{
		return mainMenuList;
	}
	public void setMainMenuList(List<CRMMainmenu> mainMenuList) 
	{
		this.mainMenuList = mainMenuList;
	}
	public List<CRMMainmenu> getSubMenuList() 
	{
		return subMenuList;
	}
	public void setSubMenuList(List<CRMMainmenu> subMenuList) 
	{
		this.subMenuList = subMenuList;
	}
	public String getMainMenuId() 
	{
		return mainMenuId;
	}
	public void setMainMenuId(String mainMenuId) 
	{
		this.mainMenuId = mainMenuId;
	}
	public String getMainMenuName() 
	{
		return mainMenuName;
	}
	public void setMainMenuName(String mainMenuName) 
	{
		this.mainMenuName = mainMenuName;
	}
	public String getMainMenuhref() 
	{
		return mainMenuhref;
	}
	public void setMainMenuhref(String mainMenuhref) 
	{
		this.mainMenuhref = mainMenuhref;
	}
	public String getMainMenuClassName() 
	{
		return mainMenuClassName;
	}
	public void setMainMenuClassName(String mainMenuClassName) 
	{
		this.mainMenuClassName = mainMenuClassName;
	}
	public String getRoleId() 
	{
		return roleId;
	}
	public void setRoleId(String roleId) 
	{
		this.roleId = roleId;
	}
	public String getRoleName() 
	{
		return roleName;
	}
	public void setRoleName(String roleName) 
	{
		this.roleName = roleName;
	}
	public List<CRMRolemaster> getRolelist() 
	{
		return rolelist;
	}
	public void setRolelist(List<CRMRolemaster> rolelist) 
	{
		this.rolelist = rolelist;
	}
	public String getHiddenroleid() 
	{
		return hiddenroleid;
	}
	public void setHiddenroleid(String hiddenroleid) 
	{
		this.hiddenroleid = hiddenroleid;
	}
	public int getFlag() 
	{
		return flag;
	}
	public void setFlag(int flag) 
	{
		this.flag = flag;
	}
	public String getPassword2() 
	{
		return password2;
	}
	public void setPassword2(String password2) 
	{
		this.password2 = password2;
	}
	public String getUserpassword()
	{
		return userpassword;
	}
	public void setUserpassword(String userpassword) 
	{
		this.userpassword = userpassword;
	}
	public String getHighSecurityPassword() 
	{
		return highSecurityPassword;
	}
	public void setHighSecurityPassword(String highSecurityPassword) 
	{
		this.highSecurityPassword = highSecurityPassword;
	}
	public List<CRMDepartment> getDepartmentList() 
	{
		return departmentList;
	}
	public void setDepartmentList(List<CRMDepartment> departmentList) 
	{
		this.departmentList = departmentList;
	}
	public String getDepartmentId() 
	{
		return departmentId;
	}
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}
	public List<CRMUser> getUserList() {
		return userList;
	}
	public void setUserList(List<CRMUser> userList) {
		this.userList = userList;
	}
	
	
	
}
