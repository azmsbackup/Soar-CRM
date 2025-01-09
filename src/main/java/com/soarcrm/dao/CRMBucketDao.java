package com.soarcrm.dao;
import java.util.List;

import com.soarcrm.domain.CRMBucket;


public interface CRMBucketDao {
	public List<CRMBucket> getBucketList() throws Exception; 
	public void insertbucket(CRMBucket crmbucket)throws Exception;
	public void updateBucket(CRMBucket crmbucket)throws Exception;
	public List<CRMBucket> getBucketListwithInactive();
	public void inactiveBucket(CRMBucket bucket) throws Exception;
	public CRMBucket checkEditDuplicateBucketName(CRMBucket status) throws Exception;
	public CRMBucket getBucket(String id)throws Exception;
	public List<CRMBucket> getbucketstatuswiseList(String statusId);
	public List<CRMBucket> getStatusBucketList() throws Exception;
	public List<CRMBucket> checkAlreadyExist(CRMBucket crmbucket);
	public List<CRMBucket> getSubstatusByStatusId(String statusId);
}


