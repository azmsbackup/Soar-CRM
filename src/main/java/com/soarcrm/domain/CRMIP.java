package com.soarcrm.domain;

import java.util.List;

public class CRMIP 
{
	private String ipId;
	private String IP;
	private String IPstatus;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String loginName;
	private int flag;
	
	private List<CRMIP> IPList;
	
	public String getIpId() 
	{
		return ipId;
	}
	public void setIpId(String ipId) 
	{
		this.ipId = ipId;
	}
	public String getIP() 
	{
		return IP;
	}
	public void setIP(String iP)
	{
		IP = iP;
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
	public String getIPstatus() 
	{
		return IPstatus;
	}
	public void setIPstatus(String iPstatus) 
	{
		IPstatus = iPstatus;
	}
	public String getLoginName() 
	{
		return loginName;
	}
	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}
	public int getFlag() 
	{
		return flag;
	}
	public void setFlag(int flag) 
	{
		this.flag = flag;
	}
	public List<CRMIP> getIPList() {
		return IPList;
	}
	public void setIPList(List<CRMIP> iPList) {
		IPList = iPList;
	}

}
