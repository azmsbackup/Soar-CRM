package com.soarcrm.domain;

import java.util.List;

public class CRMBucket {
	
	private String bucketId;
	private String bucketDescription;
	private String statusId;
	private String bucketName;
	private String userloginname;
	private String bucketType;
	private String crmbucket;
	private String statusDescription;
	private String alreadyExistMsg;

	public String getAlreadyExistMsg() {
		return alreadyExistMsg;
	}

	public void setAlreadyExistMsg(String alreadyExistMsg) {
		this.alreadyExistMsg = alreadyExistMsg;
	}

	private List<CRMBucket> bucketList;
	private List<CRMStatus> statusList;
	private int flag;


	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getBucketId() {
		return bucketId;
	}

	public void setBucketId(String bucketId) {
		this.bucketId = bucketId;
	}
	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getUserloginname() {
		return userloginname;
	}

	public void setUserloginname(String userloginname) {
		this.userloginname = userloginname;
	}
	public String getBucketType() {
		return bucketType;
	}

	public void setBucketType(String bucketType) {
		this.bucketType = bucketType;
	}
	public String getCrmbucket() {
		return crmbucket;
	}

	public void setCrmbucket(String crmbucket) {
		this.crmbucket = crmbucket;
	}
	public void setBucketList(List<CRMBucket> bucketList) {
		this.bucketList = bucketList;
	}
	public void getBucket(List<CRMBucket> bucketList)
	{
		this.bucketList=bucketList;
	}
	public List<CRMStatus> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<CRMStatus> statusList) {
		this.statusList = statusList;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		
		return flag;
	}
	public void setLoginName(String userloginname) {
		
	this.setUserloginname(userloginname);	
	}
	public void setCrmbucketList(List<CRMBucket> bucketList) {
		this.setBucketList(bucketList);
	}

	public List<CRMBucket> getBucketList() {
		return bucketList;
	}

	public String getBucketDescription() {
		return bucketDescription;
	}

	public void setBucketDescription(String bucketDescription) {
		this.bucketDescription = bucketDescription;
	}	

}
