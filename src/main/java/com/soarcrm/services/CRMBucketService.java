package com.soarcrm.services;

import java.util.List;

import com.soarcrm.domain.CRMBucket;

public interface CRMBucketService {
	
	public List<CRMBucket> getBucketList() throws Exception; 
	public List<CRMBucket> getBucketListwithInactive();
	public void insertbucket(CRMBucket crmbucket)throws Exception;
	public CRMBucket getBucket(String id)throws Exception;
	public void updateBucket(CRMBucket crmbucket)throws Exception;
	public void inactiveBucket(CRMBucket bucket) throws Exception;
	public CRMBucket checkEditDuplicateBucketName(CRMBucket status) throws Exception;
	public List<CRMBucket> getbucketstatuswiceList(String statusId)throws Exception;
	public List<CRMBucket> getStatusBucketList() throws Exception;
	public List<CRMBucket> checkAlreadyExist(CRMBucket crmbucket);
	public List<CRMBucket> getSubstatusByStatusId(String statusId);


}
