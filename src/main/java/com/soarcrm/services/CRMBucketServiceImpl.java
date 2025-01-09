package com.soarcrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMBucketDao;
import com.soarcrm.domain.CRMBucket;

public class CRMBucketServiceImpl implements CRMBucketService {
	
	@Autowired
	CRMBucketDao CRMbucketDao;
	
	public List<CRMBucket> getBucketList() throws Exception	
	{
		return CRMbucketDao.getBucketList();		
	}
	
	public List<CRMBucket> getBucketListwithInactive()
	{			
		return CRMbucketDao.getBucketListwithInactive();
	}

	public void insertbucket(CRMBucket crmbucket)throws Exception		
	{
		CRMbucketDao.insertbucket(crmbucket);
	}

	public CRMBucket getBucket(String id) throws Exception		
	{
		return CRMbucketDao.getBucket(id);
	}
	
	public void updateBucket(CRMBucket crmbucket)throws Exception			
	{
		CRMbucketDao.updateBucket(crmbucket);
	}
	
	public void inactiveBucket(CRMBucket bucket) throws Exception 		
	{
		CRMbucketDao.inactiveBucket(bucket);
		
	}
	
	public CRMBucket checkEditDuplicateBucketName(CRMBucket status) throws Exception		
	{
		return CRMbucketDao.checkEditDuplicateBucketName(status);
	}
	public List<CRMBucket> getbucketstatuswiceList(String statusId)
	{
		return CRMbucketDao.getbucketstatuswiseList(statusId);
	
	}
	
	public List<CRMBucket> getStatusBucketList() throws Exception
	{
		return CRMbucketDao.getStatusBucketList();
	}

	@Override
	public List<CRMBucket> checkAlreadyExist(CRMBucket crmbucket) {
		return CRMbucketDao.checkAlreadyExist(crmbucket);
	}

	@Override
	public List<CRMBucket> getSubstatusByStatusId(String statusId) {
		return CRMbucketDao.getSubstatusByStatusId(statusId);
	}
	
}
