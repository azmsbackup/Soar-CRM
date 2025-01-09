package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.soarcrm.domain.CRMBucket;

public class CRMBucketExtractor {
	
	public CRMBucket extractData(ResultSet resultSet) throws SQLException
	{
		CRMBucket bucket = new CRMBucket();
		bucket.setBucketId(resultSet.getString(1));
		bucket.setStatusId(resultSet.getString(2));
		bucket.setBucketName(resultSet.getString(3));
		bucket.setBucketType(resultSet.getString(4));

		return bucket;
	}
	
}
