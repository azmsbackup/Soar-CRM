package com.soarcrm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.soarcrm.domain.CRMBucket;

public class CRMBucketRowMapper  implements RowMapper<CRMBucket> 
{
	public CRMBucket mapRow(ResultSet resultSet, int line) throws SQLException 
	{
		CRMBucketExtractor bucketExtractor = new CRMBucketExtractor();		
		return bucketExtractor.extractData(resultSet);
	}

}
