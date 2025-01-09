package com.soarcrm.domain;

import java.util.List;

public class Mainmenu 
{
	private String mainMenuId;
	private String mainMenuName;
	private String mainMenuhref;
	private String mainMenuClassName;
	private List<Mainmenu> mainMenuList;
	private String subMenuId;
	private String subMenuName;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String subMenuhref;
	private List<Mainmenu> subMenuList;
	
	
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
	public List<Mainmenu> getMainMenuList() 
	{
		return mainMenuList;
	}
	public void setMainMenuList(List<Mainmenu> mainMenuList) 
	{
		this.mainMenuList = mainMenuList;
	}
	public String getSubMenuId() 
	{
		return subMenuId;
	}
	public void setSubMenuId(String subMenuId) 
	{
		this.subMenuId = subMenuId;
	}
	public String getSubMenuName() 
	{
		return subMenuName;
	}
	public void setSubMenuName(String subMenuName) 
	{
		this.subMenuName = subMenuName;
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
	public String getSubMenuhref() 
	{
		return subMenuhref;
	}
	public void setSubMenuhref(String subMenuhref) 
	{
		this.subMenuhref = subMenuhref;
	}
	public List<Mainmenu> getSubMenuList() 
	{
		return subMenuList;
	}
	public void setSubMenuList(List<Mainmenu> subMenuList) {
		this.subMenuList = subMenuList;
	}

}
