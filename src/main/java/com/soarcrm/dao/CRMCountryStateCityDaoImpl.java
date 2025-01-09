package com.soarcrm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.soarcrm.domain.CRMCity;
import com.soarcrm.domain.CRMCountry;
import com.soarcrm.domain.CRMNotes;
import com.soarcrm.domain.CRMState;
import com.soarcrm.jdbc.CRMCityRowMapper;
import com.soarcrm.jdbc.CRMCountryRowMapper;
import com.soarcrm.jdbc.CRMStateRowMapper;
import com.soarcrm.util.AllzoneCRMConstants;

public class CRMCountryStateCityDaoImpl implements CRMCountryStateCityDao 
{
	@Autowired
	DataSource dataSource;
	public List<CRMCountry> getCountryList() throws Exception 
	{
		List<CRMCountry> countrylist = new ArrayList<CRMCountry>();
		
		String sql = "select * from azc_countries where id = '231' ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		countrylist = jdbcTemplate.query(sql, new CRMCountryRowMapper());

		return countrylist;
	}
	
	public List<CRMState> getStateList(String id) throws Exception 
	{
		List<CRMState> stateList = new ArrayList<CRMState>();
		
		String sql ="";
		if(id!= null && !id.equals(""))
		{
			sql = "select DISTINCT  State from azc_client where Country ='2' and State not in ('', '-','�', '_' , '.') order by State " ;
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

			for (Map<String, Object> row : rows) 
			{
				CRMState state = new CRMState();
				
				String stateName="";			
				
				if(row.get("State") != null && !row.get("State").equals(""))
				{
					stateName = row.get("State").toString();
				}			
				state.setName(stateName);

				stateList.add(state);
			}
		}
		return stateList;
	}

	public List<CRMCity> getCityList(String id) throws Exception 
	{
		List<CRMCity> cityList = new ArrayList<CRMCity>();
		
		if(id!= null && !id.equals(""))
		{
			String sql = "select DISTINCT City from azc_client where State ='"+id+"' and City not in ('', '-','�', '_' , '.') order by City" ;
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

			for (Map<String, Object> row : rows) 
			{
				CRMCity city = new CRMCity();
				
				String cityName="";			
				
				if(row.get("City") != null && !row.get("City").equals(""))
				{
					cityName = row.get("City").toString();
				}			
				city.setName(cityName);

				cityList.add(city);
			}

			
		}
		

		return cityList;
	}

	@Override
	public List<CRMState> getStateList() throws Exception 
	{
		List<CRMState> statelist = new ArrayList<CRMState>();
		
		String sql = "select * from azc_states" ;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		statelist = jdbcTemplate.query(sql, new CRMStateRowMapper());

		return statelist;
	}

}
