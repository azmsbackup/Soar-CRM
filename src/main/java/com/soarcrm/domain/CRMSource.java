package com.soarcrm.domain;

import java.util.List;

public class CRMSource 
{
	private String sourceId;
	private String sourceDescription;
	private String sourceStatus;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String loginName;
	private int flag;
	private List<CRMSource> sourceList;
	
	public String getSourceId() 
	{
		return sourceId;
	}
	public void setSourceId(String sourceId) 
	{
		this.sourceId = sourceId;
	}
	public String getSourceDescription() 
	{
		return sourceDescription;
	}
	public void setSourceDescription(String sourceDescription) 
	{
		this.sourceDescription = sourceDescription;
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
	public String getSourceStatus() 
	{
		return sourceStatus;
	}
	public void setSourceStatus(String sourceStatus) 
	{
		this.sourceStatus = sourceStatus;
	}
	public String getLoginName() 
	{
		return loginName;
	}
	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public List<CRMSource> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<CRMSource> sourceList) {
		this.sourceList = sourceList;
	}

}
