package com.soarcrm.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.soarcrm.domain.CRMBucket;
import com.soarcrm.jdbc.CRMBucketRowMapper;


public class CRMBucketDaoImpl implements CRMBucketDao {
	
	@Autowired
	DataSource dataSource;
	
	
	public List<CRMBucket> getBucketList() throws Exception 
	{
		List<CRMBucket> bucketlist = new ArrayList<CRMBucket>();
		String sql = "select * from azc_buckets ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		bucketlist = jdbcTemplate.query(sql, new CRMBucketRowMapper());
		return bucketlist;
	}
	
	public void updateBucket(CRMBucket crmbucket) throws Exception 
	{
		
		String sql = "UPDATE azc_buckets set  bucket_name = ? where bucket_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update( sql, new Object[] {crmbucket.getBucketName(),crmbucket.getBucketId()});	
	}
	
	public CRMBucket checkEditDuplicateBucketName(CRMBucket status) throws Exception 
	{		
		List<CRMBucket> statusList = new ArrayList<CRMBucket>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from azc_buckets where bucket_name = ? and bucket_id = ?";
		statusList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, status.getBucketName());
				ps.setString(2, status.getBucketId());
			}
		}, new CRMBucketRowMapper());		
		if (statusList != null && statusList.size() == 1) 
		{
			
			status.setFlag(1);
			return statusList.get(0);
		} 
		else 
		{
			
			return null;
		}
	}

	public void insertbucket(CRMBucket crmbucket)throws Exception
	{
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		String bucketType="A";
		String sql = "INSERT INTO azc_buckets "
		    + "(status_id, bucket_name,bucket_Type) "
		    + "VALUES (?, ?,?)";
		
		 jdbcTemplate.update(sql, new Object[] { crmbucket.getStatusId(), crmbucket.getBucketName(),bucketType});		
	}
	
	public List<CRMBucket> getBucketListwithInactive()
	{
		List<CRMBucket> bucketlist = new ArrayList<CRMBucket>();	
		String sql = "select * from azc_buckets where bucket_id != 'ALL'";
		System.out.println("sql"+sql);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		bucketlist = jdbcTemplate.query(sql, new CRMBucketRowMapper());
		return bucketlist;

	}
	
	public void inactiveBucket(CRMBucket bucket) throws Exception
	{
				
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);			
		String sqlselect = "SELECT bucket_Type FROM azc_buckets where bucket_id='"+bucket.getBucketId()+"' ";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlselect);
		String crmbucket="";
		for (Map<String, Object> row : rows) 
		{	
			crmbucket = row.get("bucket_Type").toString();	
		}
		System.out.println("bucket"+crmbucket);
		if(crmbucket.equals("A"))
		{
			crmbucket = "I";
		}
		else
		{
			crmbucket = "A";
		}
		
		String sql = "UPDATE azc_buckets set bucket_Type= ? where bucket_id= ?";
		jdbcTemplate.update( sql, new Object[] {crmbucket, bucket.getBucketId()});
	}
	
	public CRMBucket checkEditDuplicateBucketDescription(CRMBucket bucket)
	{
		List<CRMBucket> bucketList = new ArrayList<CRMBucket>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from azc_buckets where bucket_name = ? and bucket_id != ?";
		bucketList = jdbcTemplate.query(sql, new PreparedStatementSetter() 
		{
			public void setValues(java.sql.PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, bucket.getBucketName());
				ps.setString(2, bucket.getBucketId());
			}
		}, new CRMBucketRowMapper());
		
		if (bucketList != null && bucketList.size() == 1) 
		{
			bucket.setFlag(1);
			return bucketList.get(0);
		} 
		else 
		{
			return null;
		}

	}
	
	public CRMBucket getBucket(String id)throws Exception
	{
		List<CRMBucket> bucketlist = new ArrayList<CRMBucket>();		
		String sql = "select * from azc_buckets where bucket_id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		bucketlist = jdbcTemplate.query(sql, new CRMBucketRowMapper());
		return bucketlist.get(0);
	
	}
	public List<CRMBucket> getbucketstatuswiseList(String statusId)
	{
		List<CRMBucket> bucketlist=new ArrayList<CRMBucket>();
		String sql="select * from azc_buckets where bucket_Type = 'A' and status_id="+statusId;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		bucketlist = jdbcTemplate.query(sql, new CRMBucketRowMapper());
		return bucketlist;
	}
	
	
	public List<CRMBucket> getStatusBucketList() throws Exception 
	{
		List<CRMBucket> bucketlist = new ArrayList<CRMBucket>();
		String sql = "select s.Status_Desc, bucket_id,bucket_name, b.status_id, bucket_Type "
				+ "from azc_buckets b, azc_status s WHERE b.status_id = s.status_id order by b.status_id";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			CRMBucket bucket = new CRMBucket();
		
			bucket.setStatusDescription(row.get("Status_Desc").toString());
			bucket.setBucketId(row.get("bucket_id").toString());
			bucket.setBucketName(row.get("bucket_name").toString());
			bucket.setStatusId(row.get("status_id").toString());
			bucket.setBucketType(row.get("bucket_Type").toString());
			
			
			
			bucketlist.add(bucket);
		}
		return bucketlist;
	}

	@Override
	public List<CRMBucket> checkAlreadyExist(CRMBucket crmbucket) {
		List<CRMBucket> bucketlist=new ArrayList<CRMBucket>();
		
		String sql="select * from azc_buckets where bucket_name = '"+ crmbucket.getBucketName()+  "' and status_id="+crmbucket.getStatusId();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		bucketlist = jdbcTemplate.query(sql, new CRMBucketRowMapper());
		return bucketlist;
	}

	@Override
	public List<CRMBucket> getSubstatusByStatusId(String statusId) {
		List<CRMBucket> bucketlist = new ArrayList<CRMBucket>();
		String sql = "select bucket_id,bucket_name, status_id, bucket_Type "
				+ "from azc_buckets  WHERE status_id = '"+ statusId+  "' order by status_id";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			CRMBucket bucket = new CRMBucket();
			bucket.setBucketId(row.get("bucket_id").toString());
			bucket.setBucketName(row.get("bucket_name").toString());
			bucket.setStatusId(row.get("status_id").toString());
			bucket.setBucketType(row.get("bucket_Type").toString());
			bucketlist.add(bucket);
		}
		return bucketlist;
	}
	
	

	
	
}
