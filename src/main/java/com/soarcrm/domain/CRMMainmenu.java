package com.soarcrm.domain;

import java.util.List;

public class CRMMainmenu 
{
	private String mainMenuId;
	private String mainMenuName;
	private String mainMenuhref;
	private String mainMenuClassName;
	private List<CRMSubmenu> subMenuList;
	
	
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
	public List<CRMSubmenu> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<CRMSubmenu> subMenuList) {
		this.subMenuList = subMenuList;
	}


}
